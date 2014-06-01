package br.cefetrj.sca.infra;

import java.util.List;

import br.cefetrj.sca.dominio.Turma;

public class TurmaDaoJpa implements TurmaDao {
	private GenericDaoJpa<Turma> genericDAO = new GenericDaoJpa<Turma>();

	@Override
	public Boolean gravar(Turma t) {
		return genericDAO.incluir(t);
	}

	@Override
	public List<Turma> recuperarTodos() {
		return genericDAO.obterTodos(Turma.class);
	}

	@Override
	public Turma getByCodigo(String codigo) {
		String consulta = "SELECT a from Turma a WHERE a.codigo = ?";
		Object array[] = { codigo };
		return genericDAO.obterEntidade(consulta, array);
	}
}
