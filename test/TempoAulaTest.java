import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TempoAulaTest {

	@Test
	public void inicioDeveSerDepoisFim() {
		TempoAula tempo = new TempoAula("12:40", "13:30");
	}

	@Test(expected=IllegalArgumentException.class)
	public void duracaoTempoAulaDeveSer50Minutos() {
		TempoAula tempo = new TempoAula("12:40", "13:40");
	}

	@Test(expected = IllegalArgumentException.class)
	public void inicioNaoDeveSerDepoisFim() {
		TempoAula tempo = new TempoAula("14:40", "13:30");
	}

	@Test
	public void haColisaoTest() {
		TempoAula tempo1 = new TempoAula("12:40", "13:30");
		TempoAula tempo2 = new TempoAula("12:40", "13:30");
		assertEquals(tempo1.haColisao(tempo2), true);
	}
}
