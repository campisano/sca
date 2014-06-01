package br.cefetrj.sca.dominio.contas;

import javax.persistence.Embeddable;

@Embeddable
public class Email {
	private static ValidadorEmail validador = new ValidadorEmail();

	private String endereco;

	@SuppressWarnings("unused")
	private Email() {
	}

	public Email(String endereco) {
		if (endereco == null || endereco.isEmpty()) {
			throw new IllegalArgumentException(
					"Endereço do email é obrigat�rio.");
		}
		if (!validador.validar(endereco)) {
			throw new IllegalArgumentException(
					"Endereço fornecido não é válido: " + endereco);
		}
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return endereco;
	}
}
