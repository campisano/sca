package br.cefetrj.sca.infra.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import br.cefetrj.sca.dominio.Aluno;
import br.cefetrj.sca.infra.AlunoDao;

@Component
public class AlunoDaoJpa extends GenericDaoJpa<Aluno> implements AlunoDao {

	@Override
	public Aluno getAlunoPorMatricula(String matricula) {
		String consulta = "SELECT a from Aluno a WHERE a.matricula = ?";
		Object array[] = { matricula };
		return super.obterEntidade(consulta, array);
	}

	@Override
	public boolean excluir(Aluno p) {
		return super.excluir(Aluno.class, p.getId());
	}

	@Override
	public List<Aluno> obterTodos() {
		return super.obterTodos(Aluno.class);
	}
}
