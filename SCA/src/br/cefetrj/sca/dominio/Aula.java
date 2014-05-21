package br.cefetrj.sca.dominio;

public class Aula {
	private EnumDiaSemana dia;
	private Intervalo intervalo;

	public Aula(EnumDiaSemana dia, String strInicio, String strFim) {
		super();
		this.dia = dia;
		this.intervalo = new Intervalo(strInicio, strFim);
	}

	public EnumDiaSemana getDia() {
		return dia;
	}

	public String getHoraInicio() {
		return intervalo.getInicio();
	}

	public String getHoraTermino() {
		return intervalo.getFim();
	}

}