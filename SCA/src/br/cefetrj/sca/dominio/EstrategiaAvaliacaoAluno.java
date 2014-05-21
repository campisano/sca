package br.cefetrj.sca.dominio;

import java.math.BigDecimal;

public interface EstrategiaAvaliacaoAluno {
	public String getGrau(Aproveitamento avaliacao);
	public String getAvaliacao(Aproveitamento avaliacao);
	public BigDecimal getNotaFinal(Aproveitamento avaliacao);
}
