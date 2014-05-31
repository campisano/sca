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
import br.cefetrj.sca.dominio.SemestreLetivo.EnumPeriodo;
import br.cefetrj.sca.dominio.Turma;

public class CarregarInformacoes20141 {

	private HashMap<String, String> disciplinas;
	private HashMap<String, SemestreLetivo> turmas;
	private HashMap<String, String> turmasdisciplinas;

	public CarregarInformacoes20141() {
		disciplinas = new HashMap<>();
		turmas = new HashMap<>();
		turmasdisciplinas = new HashMap<>();
	}

	public static void main(String[] args) {
		CarregarInformacoes20141 test = new CarregarInformacoes20141();
		test.read("./Matrícula-DEPIN-2014-1.xls");
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
				Cell cell_code_d = sheet.getCell(4, i);
				Cell cell_name_d = sheet.getCell(5, i);

				disciplinas.put(cell_code_d.getContents(),
						cell_name_d.getContents());

				Cell cell_code_t = sheet.getCell(11, i);
				Cell cell_ano_t = sheet.getCell(15, i);
				Cell cell_periodo_t = sheet.getCell(16, i);
				
				int ano = Integer.parseInt(cell_ano_t.getContents());
				SemestreLetivo.EnumPeriodo periodo;
				
				if(cell_periodo_t.getContents().equals("1º Semestre"))
				{
					periodo = EnumPeriodo.PRIMEIRO;
				}
				else
				{
					periodo = EnumPeriodo.SEGUNDO;
				}
				
				
				SemestreLetivo sl = new SemestreLetivo(ano, periodo);
				turmas.put(cell_code_t.getContents(), sl);
				
				turmasdisciplinas.put(cell_code_t.getContents(), cell_code_d.getContents());
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

		Set<String> keySetD = disciplinas.keySet();Criar caso de uso para registrar avaliação do aluno para uma turma. a resposta é entre 4 possíveis tipos de resposta.

deve se visualizar a lista de turmas / disciplinas

o formulário terá várias questões, e um radio-box de resposta com os 4 tipos

criar uma classe de serviço por cada caso de uso (1)


usuário seleciona funcionalidade
sistema apresenta lista de turma e disciplinas do periodo letivo que ele acabou de cursar



- importar os objetos na base de dados
cada linha do excel é um objeto partecipação
ficar de olho na coluna situação, so aquelas aceitas

- o que vai ser avaliado é a organização arquitetural
fazer as operações de sistemas conforme explicado
não misturar responsabilidade das camadas

- relatório das alterações de 2/3 páginas
descriver as 3/4 operações de sistema

- entrega 06/06/2014 ou 09/06/2014
comparecer entre 18:30 e 19
somente após disso, subir o material no moodle (projeto e relatório).


		for (String codeD : keySetD) {
			Disciplina d = new Disciplina(disciplinas.get(codeD), codeD, 4);
			em.getTransaction().begin();
			em.persist(d);
			em.getTransaction().commit();
		}
		
		Set<String> keySetT = turmas.keySet();
		
		for (String codeT : keySetT)
		{
			SemestreLetivo sl = turmas.get(codeT);
			String codD = turmasdisciplinas.get(codeT);
			
			String hql = "from Disciplina d where d.codigo = :code";
			Query q = em.createQuery(hql);
			q.setParameter("code", codD);
			Disciplina d = (Disciplina) q.getSingleResult();
			
			Turma t = new Turma(d, codeT, 40, sl);
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		}

	}
}
