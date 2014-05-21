package br.cefetrj.sca.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.cefetrj.sca.infra.AlunoDao;

public class AlunoRepositorio {

	@Autowired
	AlunoDao dao;

	private AlunoRepositorio() {
		super();
	}

	public void atualizar(Aluno aluno) {
		dao.alterar(aluno);
	}

	public List<Aluno> recuperarTodos() {
		return dao.obterTodos();
	}

	public Aluno getByMatricula(String matriculaAluno) {
		return dao.getAlunoPorMatricula(matriculaAluno);
	}
}
