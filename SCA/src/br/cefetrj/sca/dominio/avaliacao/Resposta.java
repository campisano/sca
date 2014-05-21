package br.cefetrj.sca.dominio.avaliacao;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Resposta {
	@Id
	@GeneratedValue
	private Long id;

	// insuficiente
	// ruim
	//
	private String descritor;

	@SuppressWarnings("unused")
	private Resposta() {
	}

	public Resposta(String descritor) {
		// TODO [BZR] não faz sentido resposta con descritor nulo ou vazío, ou
		// duplicado
		this.descritor = descritor;
	}

	public String getDescritor() {
		return descritor;
	}

}
