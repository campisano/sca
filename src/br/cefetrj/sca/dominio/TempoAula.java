package br.cefetrj.sca.dominio;

public class TempoAula {
	private final static long TEMPO_AULA_EM_MIN = 50;
	private final static long TEMPO_AULA_EM_MILIS = TEMPO_AULA_EM_MIN * 60 * 1000;

	private final Intervalo intervalo;

	public TempoAula(String strInicio, String strFim) {

		this.intervalo = new Intervalo(strInicio, strFim);

		Long duracao = this.intervalo.duracaoEmMilis();
		if (TEMPO_AULA_EM_MILIS != duracao) {
			throw new IllegalArgumentException(
					"Duração de um tempo de aula é de " + TEMPO_AULA_EM_MIN
							+ " minutos.");
		}

	}

	public boolean haColisao(TempoAula outro) {
		return this.intervalo.colide(outro.intervalo);
	}

	public String getInstanteInicio() {
		return intervalo.getInicio();
	}

	public String getInstanteTermino() {
		return intervalo.getFim();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((intervalo == null) ? 0 : intervalo.hashCode());
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
		TempoAula other = (TempoAula) obj;
		if (intervalo == null) {
			if (other.intervalo != null)
				return false;
		} else if (!intervalo.equals(other.intervalo))
			return false;
		return true;
	}

}
