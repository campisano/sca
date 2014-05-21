package br.cefetrj.sca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.cefetrj.sca.dominio.Disciplina;
import br.cefetrj.sca.dominio.Professor;
import br.cefetrj.sca.dominio.ProfessorRepositorio;

public class RegistrarHabilitacoeService {

	ProfessorRepositorio repo = new ProfessorRepositorio();
	private Professor professor;

	List<Professor> obterProfessores() {
		return repo.obterProfessores();
	}

	public List<String> obterHabilitacoes(String matricula) {
		professor = repo.obterProfessor(matricula);
		List<String> nomesHabilitacoes = new ArrayList<String>();
		Set<Disciplina> disciplinas = professor.getHabilitacoes();
		for (Disciplina disciplina : disciplinas) {
			nomesHabilitacoes.add(disciplina.getNome());
		}
		return nomesHabilitacoes;
	}

	public void adicionarHabilitacao(String nome) {
		// TODO
	}

	public void removerHabilitacao(String nome) {
		// TODO
	}

	public void adicionarHabilitacoes(List<String> nomes) {
		// TODO
	}

	public void removerHabilitacoes(List<String> nomes) {
		// TODO
	}

	public void registrarHabilitacoes() {
		repo.atualizar(professor);
	}
}
