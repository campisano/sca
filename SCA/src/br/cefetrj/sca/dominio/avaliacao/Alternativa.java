package br.cefetrj.sca.dominio.avaliacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alternativa {
	@Id
	@GeneratedValue
	Long id;

	private String descritor;

	private Alternativa() {
	}

	public Alternativa(String descritor) {
		this.descritor = descritor;
	}

	public Long getId() {
		return id;
	}

	public String getDescritor() {
		return descritor;
	}
}
