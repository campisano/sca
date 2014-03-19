import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TempoAula {
	private final static long TEMPO_AULA_EM_MIN = 50;
	private final static long TEMPO_AULA_EM_MILIS = TEMPO_AULA_EM_MIN * 60 * 1000;

	/**
	 * Formatador usado para trasformar as strings passadas na constru��o do
	 * objeto em objeto da classe {@link Date} .
	 */
	private static DateFormat formatador = new SimpleDateFormat("HH:mm");

	private Date instanteInicio;
	private Date instanteTermino;

	public TempoAula(String strInicio, String strFim) {
		try {
			this.instanteInicio = (Date) formatador.parse(strInicio);
			this.instanteTermino = (Date) formatador.parse(strFim);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Argumentos inv�lidos: ("
					+ strInicio + ", " + strFim + ")", e);
		}
		if (instanteInicio.after(instanteTermino)) {
			throw new IllegalArgumentException(
					"In�cio deve ser anterior ao fim.");
		}

		Long a = instanteInicio.getTime();
		Long b = instanteTermino.getTime();
		if (TEMPO_AULA_EM_MILIS != b - a) {
			throw new IllegalArgumentException(
					"Dura��o de um tempo de aula � de " + TEMPO_AULA_EM_MIN
							+ " minutos.");
		}
	}

	public boolean haColisao(TempoAula outro) {
		// TO-DO
		return true;
	}

	public String getInstanteInicio() {
		return formatador.format(instanteInicio);
	}

	public String getInstanteTermino() {
		return formatador.format(instanteTermino);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instanteInicio == null) ? 0 : instanteInicio.hashCode());
		result = prime * result
				+ ((instanteTermino == null) ? 0 : instanteTermino.hashCode());
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
		if (instanteInicio == null) {
			if (other.instanteInicio != null)
				return false;
		} else if (!instanteInicio.equals(other.instanteInicio))
			return false;
		if (instanteTermino == null) {
			if (other.instanteTermino != null)
				return false;
		} else if (!instanteTermino.equals(other.instanteTermino))
			return false;
		return true;
	}
}
