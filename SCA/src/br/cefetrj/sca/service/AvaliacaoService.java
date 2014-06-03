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
			String codigoTurma) {

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

}
