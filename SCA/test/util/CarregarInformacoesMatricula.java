package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import br.cefetrj.sca.dominio.Disciplina;
import br.cefetrj.sca.dominio.SemestreLetivo;
import br.cefetrj.sca.dominio.Turma;

public class CarregarInformacoesMatricula {
	public static void main(String[] args) {
		carregarDisciplinas();
		carregarTurmas();
	}

	private static void carregarTurmas() {
		HashMap<String, SemestreLetivo> turmas = new HashMap<String, SemestreLetivo>();

		HashMap<String, String> disciplinas = new HashMap<String, String>();

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SCAPU");

		EntityManager em = emf.createEntityManager();

		File inputWorkbook = new File("./Matrícula-DEPIN-2014-1.xls");
		Workbook w;
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("Cp1252");

			w = Workbook.getWorkbook(inputWorkbook, ws);

			// Obtém a primeira aba da planilha.
			Sheet sheet = w.getSheet(0);

			// Percorre as 10 primeiras linhas das 10 primeiras colunas.
			for (int j = 1; j < sheet.getRows(); j++) {
				Cell cell1 = sheet.getCell(11, j);
				Cell cell2 = sheet.getCell(15, j);
				Cell cell3 = sheet.getCell(16, j);
				Cell cell4 = sheet.getCell(4, j);

				int ano = Integer.parseInt(cell2.getContents());

				SemestreLetivo.EnumPeriodo periodo;
				if ("1º Semestre".equals(cell3.getContents())) {
					periodo = SemestreLetivo.EnumPeriodo.PRIMEIRO;
				} else {
					periodo = SemestreLetivo.EnumPeriodo.SEGUNDO;
				}
				SemestreLetivo sl = new SemestreLetivo(ano, periodo);

				turmas.put(cell1.getContents(), sl);
				disciplinas.put(cell1.getContents(), cell4.getContents());

			}
			System.out.println("fim: " + turmas.size());
			Set<String> keySet = turmas.keySet();
			for (String codigoTurma : keySet) {
				SemestreLetivo sl = turmas.get(codigoTurma);

				String codigoDisciplina = disciplinas.get(codigoTurma);

				String hql = "from Disciplina d where d.codigo = :cod";
				Query q = em.createQuery(hql);
				q.setParameter("cod", codigoDisciplina);
				Disciplina d = (Disciplina) q.getSingleResult();

				Turma t = new Turma(d, codigoTurma, 40, sl);
				em.getTransaction().begin();
				em.persist(t);
				em.getTransaction().commit();
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void carregarDisciplinas() {
		HashMap<String, String> disciplinas = new HashMap<String, String>();

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SCAPU");

		EntityManager em = emf.createEntityManager();

		File inputWorkbook = new File("./Matrícula-DEPIN-2014-1.xls");
		Workbook w;
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("Cp1252");

			w = Workbook.getWorkbook(inputWorkbook, ws);

			// Obtém a primeira aba da planilha.
			Sheet sheet = w.getSheet(0);

			// Percorre as 10 primeiras linhas das 10 primeiras colunas.
			for (int j = 1; j < sheet.getRows(); j++) {
				Cell cell1 = sheet.getCell(4, j);
				Cell cell2 = sheet.getCell(5, j);

				disciplinas.put(cell1.getContents(), cell2.getContents());

			}
			System.out.println("fim: " + disciplinas.size());

			Set<String> keySet = disciplinas.keySet();
			for (String codigo : keySet) {
				String nome = disciplinas.get(codigo);
				System.out.println(codigo + "\t\t" + nome);
				Disciplina d = new Disciplina(nome, codigo, 4);
				em.getTransaction().begin();
				em.persist(d);
				em.getTransaction().commit();
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}