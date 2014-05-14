package br.cefetrj.sca.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import br.cefetrj.sca.infra.ProfessorDao;

public class ProfessorRepo {

	@Autowired
	ProfessorDao dao;

	public void setProfessorDao(ProfessorDao dao) {
		this.dao = dao;
	}

	public Professor obterProfessor(String matricula) {
		return dao.getProfessorPorMatricula(matricula);
	}

}
