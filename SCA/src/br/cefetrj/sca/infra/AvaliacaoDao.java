package br.cefetrj.sca.infra;

import br.cefetrj.sca.dominio.avaliacao.AvaliacaoTurma;

public interface AvaliacaoDao {

	AvaliacaoTurma getAvaliacaoTurma(String codigoTurma);

}
