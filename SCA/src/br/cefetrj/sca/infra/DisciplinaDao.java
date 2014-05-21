package br.cefetrj.sca.infra;

import java.util.List;

import br.cefetrj.sca.dominio.Disciplina;

public interface DisciplinaDao {
	public void gravar(Disciplina d);

	public void gravar(List<Disciplina> lista);

	public Disciplina getByNome(String nome);

	public List<Disciplina> getDisciplinas();

	void excluir(Disciplina disciplina);
}
