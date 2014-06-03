package br.cefetrj.sca.infra.jpa;

import org.springframework.stereotype.Component;

import br.cefetrj.sca.dominio.avaliacao.AvaliacaoTurma;
import br.cefetrj.sca.infra.AvaliacaoDao;

@Component
public class AvaliacaoDaoJpa extends GenericDaoJpa<AvaliacaoTurma> implements
		AvaliacaoDao {

	@Override
	public AvaliacaoTurma getAvaliacaoTurma(String codigoTurma) {
		String consulta = "SELECT a from AvaliacaoTurma a WHERE a.turmaAvaliada.codigo = ?";
		Object array[] = { codigoTurma };
		return super.tentaObterEntidade(consulta, array);
	}
}
