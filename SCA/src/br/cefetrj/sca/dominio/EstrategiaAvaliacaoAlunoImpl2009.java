package br.cefetrj.sca.dominio;

import java.math.BigDecimal;

public class EstrategiaAvaliacaoAlunoImpl2009 implements
		EstrategiaAvaliacaoAluno {

	public EstrategiaAvaliacaoAlunoImpl2009() {
	}

	/**
	 * Determina a nota final do aluno na turma correspondente, obtida pela
	 * média simples entre as três notas obtidas.
	 */
	public BigDecimal getNotaFinal(Aproveitamento avaliacao) {
		double notaFinal = 0.0;
		notaFinal = (avaliacao.getNotaP1().doubleValue()
				+ avaliacao.getNotaP2().doubleValue() + avaliacao.getNotaP3()
				.doubleValue()) / 3;
		return new BigDecimal(notaFinal);
	}

	/**
	 * Determina o conceito do aluno na turma correspondente. Os resultados
	 * possíveis são "A", "B", "C" e "I".
	 */
	@Override
	public String getConceito(Aproveitamento avaliacao) {
		String grau;
		if (avaliacao.getFrequencia().doubleValue() < 0.75)
			grau = "I";
		else {
			BigDecimal notaFinal = getNotaFinal(avaliacao);
			if (notaFinal.doubleValue() >= 9.0)
				grau = "A";
			else if (notaFinal.doubleValue() >= 7.0)
				grau = "B";
			else if (notaFinal.doubleValue() >= 5.0)
				grau = "C";
			else
				grau = "I";
		}
		return grau;
	}

	/**
	 * Determina a situação final da avaliação do aluno na turma correspondente.
	 * Os resultados possíveis são os seguintes: RF (reprovado por faltas), AP
	 * (aprovado) ou RM (reprovado por média).
	 */
	@Override
	public EnumSituacaoFinalAvaliacao getSituacaoFinal(Aproveitamento avaliacao) {
		String grau = this.getConceito(avaliacao);
		if (avaliacao == null) {
			return EnumSituacaoFinalAvaliacao.INDEFINIDA;
		} else if (avaliacao.getFrequencia().doubleValue() < 0.75) {
			return EnumSituacaoFinalAvaliacao.RF;
		} else if (grau.equals("A") || grau.equals("B") || grau.equals("C")) {
			return EnumSituacaoFinalAvaliacao.AP;
		} else {
			return EnumSituacaoFinalAvaliacao.RM;
		}
	}
}
