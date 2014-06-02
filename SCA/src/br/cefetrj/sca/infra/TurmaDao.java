package br.cefetrj.sca.infra;

import java.util.List;

import br.cefetrj.sca.dominio.SemestreLetivo;
import br.cefetrj.sca.dominio.Turma;

public interface TurmaDao {

	public Boolean gravar(Turma t);

	public List<Turma> recuperarTodos();

	public Turma getByCodigo(String codigo);

	public List<Turma> getTurmasAbertas(SemestreLetivo semestreLetivoCorrente);

	public List<Turma> getTurmasCursadas(String matricula,
			SemestreLetivo semestreLetivoCorrente);
}
