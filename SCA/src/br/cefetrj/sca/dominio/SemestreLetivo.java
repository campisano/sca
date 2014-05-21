package br.cefetrj.sca.dominio;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Embeddable
public class SemestreLetivo {
	public enum Periodo {
		PRIMEIRO, SEGUNDO
	};

	private int ano;

	@Enumerated(EnumType.ORDINAL)
	private Periodo periodo;

	@Transient
	public static final SemestreLetivo SEMESTRE_LETIVO_CORRENTE;

	/**
	 * Lógica para obtenção do valor do semestre letivo corrente (atual).
	 */
	static {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Periodo periodo;
		if (calendar.get(Calendar.MONTH) <= Calendar.JUNE)
			periodo = Periodo.PRIMEIRO;
		else
			periodo = Periodo.SEGUNDO;
		SEMESTRE_LETIVO_CORRENTE = new SemestreLetivo(
				calendar.get(Calendar.YEAR), periodo);
	}

	public int getAno() {
		return ano;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	private SemestreLetivo() {
	}

	public SemestreLetivo(int ano, Periodo periodo) {
		super();
		this.ano = ano;
		this.periodo = periodo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		int temp = periodo == Periodo.PRIMEIRO ? 1 : 2;
		result = prime * result + temp;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SemestreLetivo other = (SemestreLetivo) obj;
		if (ano != other.ano)
			return false;
		if (periodo != other.periodo)
			return false;
		return true;
	}

	@Override
	protected Object clone() {
		return new SemestreLetivo(ano, periodo);
	}

	@Override
	public String toString() {
		return "" + ano + "/" + (periodo.ordinal() + 1);
	}

	public SemestreLetivo proximo() {
		SemestreLetivo outro = new SemestreLetivo(ano, periodo);
		if (outro.periodo == Periodo.PRIMEIRO) {
			outro.periodo = Periodo.SEGUNDO;
		} else {
			outro.periodo = Periodo.PRIMEIRO;
			outro.ano++;
		}
		return outro;
	}

	public static void main(String args[]) {
		System.out.println(SemestreLetivo.SEMESTRE_LETIVO_CORRENTE);
		System.out.println(SemestreLetivo.SEMESTRE_LETIVO_CORRENTE.proximo());
	}
}
