package br.cefetrj.sca.dominio;

import java.util.List;
import java.util.Set;

import br.cefetrj.sca.infra.DisciplinaDao;

public class DisciplinaRepositorio {
	private DisciplinaDao dao;

	public void adicionar(Disciplina d) {
		dao.gravar(d);
	}

	/**
	 * 
	 * @param nome
	 *            nome da disciplina
	 * @return a disciplina cujo nome foi passado como parâmetro, se existir;
	 *         null em caso contrário.
	 */
	public Disciplina getDisciplina(String nome) {
		return dao.getByNome(nome);
	}

	public List<Disciplina> getDisciplinas() {
		return dao.getDisciplinas();
	}

	public boolean estaContidaEm(Set<Disciplina> preReqs,
			Set<Disciplina> cursadas) {
		return cursadas.containsAll(preReqs);
	}
}
