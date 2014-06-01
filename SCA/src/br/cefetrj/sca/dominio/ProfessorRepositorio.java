package br.cefetrj.sca.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.cefetrj.sca.infra.ProfessorDao;

public class ProfessorRepositorio {

	@Autowired
	private ProfessorDao dao;

	private ProfessorRepositorio() {
	}

	public Professor obterProfessor(String matricula) {
		return dao.getProfessorPorMatricula(matricula);
	}

	public void adicionar(String matricula, String nome) {
		Professor prof = new Professor(matricula, nome);
		dao.incluir(prof);
	}

	public List<Professor> obterProfessores() {
		return dao.obterTodos();
	}

	public void atualizar(Professor professor) {
		dao.alterar(professor);
	}

}
