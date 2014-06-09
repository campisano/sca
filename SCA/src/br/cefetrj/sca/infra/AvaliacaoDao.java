package br.cefetrj.sca.infra;

import br.cefetrj.sca.dominio.avaliacaoturma.AvaliacaoTurma;

public interface AvaliacaoDao {

	boolean incluir(AvaliacaoTurma avaliacao);

	AvaliacaoTurma getAvaliacaoTurma(String codigoTurma);

}
