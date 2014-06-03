package br.cefetrj.sca.service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SolicitaAvaliacaoTurmaResponse extends
		ArrayList<SolicitaAvaliacaoTurmaResponse.Item> {

	public class Item {
		private String quesito;
		private List<String> alternativas;

		public Item(String quesito, List<String> alternativas) {
			this.quesito = quesito;
			this.alternativas = alternativas;
		}

		public String getQuesito() {
			return quesito;
		}

		public List<String> getAlternativas() {
			return alternativas;
		}
	}

	private String codigoTurma;

	public SolicitaAvaliacaoTurmaResponse(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}
}
