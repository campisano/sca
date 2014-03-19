package br.cefetrj.sca.dominio;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TempoAulaTest {

	@Test
	public void inicioDeveSerDepoisFim() {
		TempoAula tempo = new TempoAula("12:40", "13:30");
	}

	@Test(expected = IllegalArgumentException.class)
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

	@Test
	public void devemSerIguais() {
		TempoAula tempo1 = new TempoAula("12:40", "13:30");
		TempoAula tempo2 = new TempoAula("12:40", "13:30");
		assertEquals(tempo1, tempo2);
		assertEquals(tempo1.equals(tempo2), true);
	}

	@Test
	public void devemSerDiferentes() {
		TempoAula tempo1 = new TempoAula("18:20", "19:10");
		TempoAula tempo2 = new TempoAula("12:40", "13:30");
		assertEquals(tempo1.equals(tempo2), false);
	}

	@Test
	public void testandoGetInstanceTermino() {
		TempoAula tempo = new TempoAula("19:10", "20:00");
		assertEquals(tempo.getInstanteTermino(), "20:00");
		assertEquals("20:00", tempo.getInstanteTermino());
	}

	@Test
	public void testandoGetInstanceInicio() {
		TempoAula tempo = new TempoAula("18:20", "19:10");
		assertEquals(tempo.getInstanteInicio(), "18:20");
		assertEquals("18:20", tempo.getInstanteInicio());
	}

}
