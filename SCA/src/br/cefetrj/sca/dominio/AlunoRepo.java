package br.cefetrj.sca.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import br.cefetrj.sca.infra.AlunoDao;

public class AlunoRepo {

	@Autowired
	AlunoDao dao;

	public void setAlunoDao(AlunoDao dao) {
		this.dao = dao;
	}

	public Aluno obterAluno(String matricula) {
		return dao.getAlunoPorMatricula(matricula);
	}
}
