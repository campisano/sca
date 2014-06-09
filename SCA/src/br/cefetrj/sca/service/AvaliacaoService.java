package br.cefetrj.sca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.cefetrj.sca.dominio.Aluno;
import br.cefetrj.sca.dominio.SemestreLetivo;
import br.cefetrj.sca.dominio.Turma;
import br.cefetrj.sca.dominio.avaliacao.Alternativa;
import br.cefetrj.sca.dominio.avaliacao.AvaliacaoTurma;
import br.cefetrj.sca.dominio.avaliacao.Quesito;
import br.cefetrj.sca.dominio.repositorio.AlunoRepositorio;
import br.cefetrj.sca.dominio.repositorio.AvaliacaoRepositorio;
import br.cefetrj.sca.dominio.repositorio.QuesitoRepositorio;
import br.cefetrj.sca.dominio.repositorio.TurmaRepositorio;

@Component
public class AvaliacaoService {

	@Autowired
	private AlunoRepositorio alunoRepo;

	@Autowired
	private TurmaRepositorio turmaRepo;

	@Autowired
	private AvaliacaoRepositorio avaliacaoRepo;

	@Autowired
	private QuesitoRepositorio quesitoRepo;

	public SolicitaAvaliacaoResponse solicitaAvaliacao(String matricula) {
		validaAluno(matricula);

		List<Turma> turmas = turmaRepo.getTurmasCursadas(matricula,
				SemestreLetivo.SEMESTRE_LETIVO_CORRENTE);

		SolicitaAvaliacaoResponse response = new SolicitaAvaliacaoResponse();
		AvaliacaoTurma turmaAvaliada;

		for (Turma turma : turmas) {

			turmaAvaliada = avaliacaoRepo.getAvaliacaoTurma(turma.getCodigo());

			response.add(response.new Item(turma.getCodigo(), turma
					.getDisciplina().getNome(), turmaAvaliada != null));
		}

		return response;
	}

	public SolicitaAvaliacaoTurmaResponse solicitaAvaliacaoTurma(
			String matricula, String codigoTurma) {
		Aluno aluno = validaAluno(matricula);
		Turma turma = validaTurma(codigoTurma);

		if (!turma.isAlunoInscrito(aluno)) {
			throw new IllegalArgumentException(
					"Erro: aluno não inscrito na turma informada.");
		}

		List<Quesito> quesitos = quesitoRepo.obterTodos();

		SolicitaAvaliacaoTurmaResponse response = new SolicitaAvaliacaoTurmaResponse(
				codigoTurma);
		List<String> alternativas;

		for (Quesito quesito : quesitos) {
			alternativas = new ArrayList<String>();

			for (Alternativa alternativa : quesito.getAlternativas()) {
				alternativas.add(alternativa.getDescritor());
			}

			response.add(response.new Item(quesito.getEnunciado(), alternativas));
		}

		return response;
	}

	public void avaliaTurma(String matricula, String codigoTurma,
			List<Integer> respostas) {
		Aluno aluno = validaAluno(matricula);
		Turma turma = validaTurma(codigoTurma);

		List<Quesito> quesitos = quesitoRepo.obterTodos();
		int numRespostas = quesitos.size();

		if (respostas == null || respostas.size() != numRespostas) {
			throw new IllegalArgumentException(
					"Erro: número de respostas inválido, recebido: "
							+ respostas.size() + ", esperado: " + numRespostas
							+ ".");
		}

		List<Alternativa> alternativas = new ArrayList<Alternativa>();
		Quesito quesito;
		Integer resposta;

		for (int i = 0; i < quesitos.size(); ++i) {
			quesito = quesitos.get(i);
			resposta = respostas.get(i);

			if (resposta < 0 || resposta > quesito.getAlternativas().size()) {
				throw new IllegalArgumentException(
						"Erro: código de resposta inválido: " + resposta + ".");
			}

			alternativas.add(quesito.getAlternativas().get(resposta));
		}

		// TODO [CMP] criar esse método dentro Turma ou dentro Aluno só
		// aumentaria o acoplamento destas classes, me parece que tenha que
		// estar em outro lugar
		// public void avaliarTurma(Aluno aluno, List<Alternativa> alternativas)
		// {
		// if (aluno == null) {
		// throw new IllegalArgumentException("Erro: Aluno não pode ser nulo.");
		// }
		//
		// if (alternativas == null) {
		// throw new IllegalArgumentException(
		// "Erro: Alternativas não podem ser nulas.");
		// }
		// ...
		// }

		AvaliacaoTurma avaliacao = new AvaliacaoTurma(aluno, turma,
				alternativas);

		avaliacaoRepo.adicionar(avaliacao);

	}

	// TODO [CMP] a lógica de validaAluno e validaTurma não poderia ficar em
	// repositório? A mensagem de erro faz parte do domínio de qualquer forma

	private Aluno validaAluno(String matricula) {
		if (matricula == null || matricula.trim().equals("")) {
			throw new IllegalArgumentException("Erro: matricula inválida.");
		}

		Aluno aluno;

		try {
			aluno = alunoRepo.getByMatricula(matricula);
		} catch (Exception exc) {
			aluno = null;
		}

		if (aluno == null) {
			throw new IllegalArgumentException("Erro: aluno inexistente.");
		}

		return aluno;
	}

	private Turma validaTurma(String codigoTurma) {
		if (codigoTurma == null || codigoTurma.trim().equals("")) {
			throw new IllegalArgumentException("Erro: código turma inválido.");
		}

		Turma turma;

		try {
			turma = turmaRepo.getByCodigo(codigoTurma);
		} catch (Exception exc) {
			turma = null;
		}

		if (turma == null) {
			throw new IllegalArgumentException("Erro: turma inexistente.");
		}

		return turma;
	}
}
