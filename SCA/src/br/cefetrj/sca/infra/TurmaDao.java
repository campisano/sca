package br.cefetrj.sca.infra;

import java.util.List;

import br.cefetrj.sca.dominio.Turma;

public interface TurmaDao {

	public Boolean gravar(Turma t);

	public Boolean gravar(List<Turma> lista);

	public List<Turma> recuperarTodos();

	public Turma getByCodigo(String codigo);
}
