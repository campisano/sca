import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TempoAulaTest {

	@Test
	public void inicioDeveSerDepoisFim() {
		new TempoAula("12:40", "13:30");
	}

	@Test(expected = IllegalArgumentException.class)
	public void duracaoTempoAulaDeveSer50Minutos() {
		new TempoAula("12:40", "13:40");
	}

	@Test(expected = IllegalArgumentException.class)
	public void inicioNaoDeveSerDepoisFim() {
		new TempoAula("14:40", "13:30");
	}

	@Test
	public void haColisaoTest() {
		TempoAula tempo1 = new TempoAula("12:40", "13:30");
		TempoAula tempo2 = new TempoAula("12:40", "13:30");
		assertEquals(tempo1.haColisao(tempo2), true);
	}

	@Test
	public void getInstanteInicioTest() {
		TempoAula tempo = new TempoAula("12:40", "13:30");
		assertEquals(tempo.getInstanteInicio(), "12:40");
	}

	@Test
	public void getInstanteTerminoTest() {
		TempoAula tempo = new TempoAula("12:40", "13:30");
		assertEquals(tempo.getInstanteTermino(), "13:30");
	}

	@Test
	public void equalsTest() {
		TempoAula tempo1 = new TempoAula("12:40", "13:30");
		TempoAula tempo2 = new TempoAula("12:40", "13:30");
		TempoAula tempo3 = new TempoAula("12:45", "13:35");
		assertEquals(tempo1.equals(tempo2), true);
		assertEquals(tempo1.equals(tempo3), false);
		assertEquals(tempo2.equals(tempo3), false);
	}
}
