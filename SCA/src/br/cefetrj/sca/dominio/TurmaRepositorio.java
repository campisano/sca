package br.cefetrj.sca.dominio;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import br.cefetrj.sca.infra.TurmaDao;

public class TurmaRepositorio {

	@Autowired
	private TurmaDao turmaDAO;

	private TurmaRepositorio() {
	}

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
	}

	public List<Turma> getTurmasCursadas(String matricula,
			SemestreLetivo semestreLetivoCorrente) {
	}

}
