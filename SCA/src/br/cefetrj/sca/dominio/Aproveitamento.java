package br.cefetrj.sca.dominio;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Aproveitamento {

	@Id
	@GeneratedValue
	private Long id;

	private BigDecimal notaP1;
	private BigDecimal notaP2;
	private BigDecimal notaP3;
	private BigDecimal frequencia;

	@Transient
	@Autowired
	private EstrategiaAvaliacaoAluno estrategia;

	private Aproveitamento() {
	}

	public Long getId() {
		return id;
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

	public EnumSituacaoFinalAvaliacao getSituacaoFinal() {
		return estrategia.getSituacaoFinal(this);
	}

	public String getConceito() {
		return estrategia.getConceito(this);
	}

	public BigDecimal getNotaFinal() {
		return estrategia.getNotaFinal(this);
	}
}
