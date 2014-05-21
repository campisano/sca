package br.cefetrj.sca.infra;

import java.util.List;

import br.cefetrj.sca.dominio.Professor;

public class ProfessorDaoJpa extends GenericDaoJpa<Professor> implements
		ProfessorDao {

	@Override
	public void excluir(Professor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Professor getProfessorPorMatricula(String matricula) {
		String consulta = "SELECT a from Professor a WHERE a.matricula = ?";
		Object array[] = { matricula };
		return super.obterEntidade(consulta, array);
	}

	@Override
	public List<Professor> obterTodos() {
		return super.obterTodos(Professor.class);
	}
}
