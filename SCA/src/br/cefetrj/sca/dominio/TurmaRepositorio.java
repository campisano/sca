package br.cefetrj.sca.dominio;

import java.util.List;
import java.util.Set;

import br.cefetrj.sca.infra.TurmaDao;

public class TurmaRepositorio {
	TurmaDao turmaDAO;

	public void setTurmaDao(TurmaDao turmaDAO) {
		this.turmaDAO = turmaDAO;
	}

	public List<Turma> obterTodos() {
		return turmaDAO.recuperarTodos();
	}

	public void gravar(Turma turma) {
		turmaDAO.gravar(turma);
	}

	public Set<Turma> getTurmasAbertas(SemestreLetivo semestre) {
		// TODO Auto-generated method stub
		return null;
	}

}
