package br.cefetrj.sca.infra;

import br.cefetrj.sca.dominio.Professor;

public interface ProfessorDao {

	public abstract boolean incluir(Professor p);

	public abstract boolean alterar(Professor p);

	public abstract void excluir(Professor p);

	public abstract Professor getProfessorPorMatricula(String matricula);

}