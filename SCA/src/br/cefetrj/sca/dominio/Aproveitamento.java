package br.cefetrj.sca.dominio;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

public class Aproveitamento {
	@Id
	@GeneratedValue
	private Long id;
	
	private BigDecimal notaP1;
	private BigDecimal notaP2;
	private BigDecimal notaP3;
	private BigDecimal frequencia;

	@Autowired
	EstrategiaAvaliacaoAluno estrategia;
	
	public void setEstrategia(EstrategiaAvaliacaoAluno estrategia) {
		this.estrategia = estrategia;
	}

	public BigDecimal getNotaP1() {
		return notaP1;
	}

	public void setNotaP1(BigDecimal notaP1) {
		if (notaP1 == null)
			throw new IllegalArgumentException("Nota de P1 não pode ser nula.");
		if (notaP1.doubleValue() < 0.0 || notaP1.doubleValue() > 10.0)
			throw new IllegalArgumentException(
					"Valor inválido para nota de P1.");
		this.notaP1 = notaP1;
	}

	public BigDecimal getNotaP2() {
		return notaP2;
	}

	public void setNotaP2(BigDecimal notaP2) {
		this.notaP2 = notaP2;
	}

	public BigDecimal getNotaP3() {
		return notaP3;
	}

	public void setNotaP3(BigDecimal notaP3) {
		this.notaP3 = notaP3;
	}

	public BigDecimal getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(BigDecimal frequencia) {
		this.frequencia = frequencia;
	}
	
	public String getGrau() {
		return estrategia.getGrau(this);
	}
}
