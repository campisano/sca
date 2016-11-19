package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import br.cefetrj.sca.dominio.Aluno;
import br.cefetrj.sca.dominio.Disciplina;
import br.cefetrj.sca.dominio.SemestreLetivo;
import br.cefetrj.sca.dominio.SemestreLetivo.EnumPeriodo;
import br.cefetrj.sca.dominio.Turma;

public class CarregarInformacoesMatricula {

	// matrícula, nome
	private HashMap<String, String> alunos;

	// código, nome
	private HashMap<String, String> disciplinas;

	// código, semestre letivo { ano, período }
	private HashMap<String, SemestreLetivo> turmas;

	private HashMap<String, String> turmas_disciplinas;
	private HashMap<String, Set<String>> turmas_alunos;

	public CarregarInformacoesMatricula() {
		alunos = new HashMap<>();
		disciplinas = new HashMap<>();
		turmas = new HashMap<>();
		turmas_disciplinas = new HashMap<>();
		turmas_alunos = new HashMap<>();
	}

	public static void main(String[] args) {
		CarregarInformacoesMatricula test = new CarregarInformacoesMatricula();
		test.read("./data/Matrícula-DEPIN-2014-1.xls");
		test.writeDB("SCAPU");
	}

	public void read(String inputFile) {
		File inputWorkbook = new File(inputFile);
		Workbook w;

		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("Cp1252");
			w = Workbook.getWorkbook(inputWorkbook, ws);
			Sheet sheet = w.getSheet(0);

			for (int i = 1; i < sheet.getRows(); i++) {

				String aluno_matricula = sheet.getCell(13, i).getContents();
				String aluno_nome = sheet.getCell(1, i).getContents();
				alunos.put(aluno_matricula, aluno_nome);

				String disciplina_codigo = sheet.getCell(4, i).getContents();
				String disciplina_nome = sheet.getCell(5, i).getContents();
				disciplinas.put(disciplina_codigo, disciplina_nome);

				String turma_codigo = sheet.getCell(11, i).getContents();
				String semestre_ano = sheet.getCell(15, i).getContents();
				String semestre_periodo = sheet.getCell(16, i).getContents();

				int ano = Integer.parseInt(semestre_ano);
				SemestreLetivo.EnumPeriodo periodo;

				if (semestre_periodo.equals("1º Semestre")) {
					periodo = EnumPeriodo.PRIMEIRO;
				} else {
					periodo = EnumPeriodo.SEGUNDO;
				}

				SemestreLetivo semestre = new SemestreLetivo(ano, periodo);
				turmas.put(turma_codigo, semestre);
				turmas_disciplinas.put(turma_codigo, disciplina_codigo);

				String situacao = sheet.getCell(10, i).getContents();

				if (situacao.equals("Aceita/Matriculada")) {
					if (!turmas_alunos.containsKey(turma_codigo)) {
						turmas_alunos.put(turma_codigo, new HashSet<String>());
					}

					turmas_alunos.get(turma_codigo).add(aluno_matricula);
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeDB(String managerName) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(managerName);
		EntityManager em = emf.createEntityManager();

		Set<String> alunosIt = alunos.keySet();

		for (String matricula : alunosIt) {
			em.getTransaction().begin();
			em.persist(new Aluno(alunos.get(matricula), matricula));
			em.getTransaction().commit();
		}

		Set<String> disciplinasIt = disciplinas.keySet();

		for (String codigoDisciplina : disciplinasIt) {
			em.getTransaction().begin();
			em.persist(new Disciplina(disciplinas.get(codigoDisciplina),
					codigoDisciplina, 4));
			em.getTransaction().commit();
		}

		Set<String> turmasIt = turmas.keySet();
		Query query;

		for (String codigoTurma : turmasIt) {
			SemestreLetivo semestre = turmas.get(codigoTurma);
			String codigoDisciplina = turmas_disciplinas.get(codigoTurma);
			Set<String> matriculasInscritas = turmas_alunos.get(codigoTurma);

			query = em.createQuery("from Disciplina d where d.codigo = :code");
			query.setParameter("code", codigoDisciplina);
			Disciplina disciplina = (Disciplina) query.getSingleResult();

			// 40 vagas por turma não são suficientes
			Turma turma = new Turma(disciplina, codigoTurma, 80, semestre);
			em.getTransaction().begin();
			em.persist(turma);

			if (matriculasInscritas != null) {
				for (String matricula : matriculasInscritas) {
					query = em
							.createQuery("from Aluno a where a.matricula = :matricula");
					query.setParameter("matricula", matricula);
					Aluno aluno = (Aluno) query.getSingleResult();
					turma.inscreverAluno(aluno);
				}
			}

			em.getTransaction().commit();
		}

		em.close();
		emf.close();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
