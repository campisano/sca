package br.cefetrj.sca.infra;

import br.cefetrj.sca.dominio.avaliacao.AvaliacaoTurma;

public interface AvaliacaoDao {

	boolean incluir(AvaliacaoTurma avaliacao);

	AvaliacaoTurma getAvaliacaoTurma(String codigoTurma);

}
