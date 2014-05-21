package br.cefetrj.sca.dominio.contas;

import javax.persistence.Embeddable;


@Embeddable
public class Email {
	private static ValidadorEmail validador = new ValidadorEmail();

	String endereco;

	private Email() {
	}

	public Email(String endereco) {
		if(endereco == null || endereco.isEmpty()){
			throw new IllegalArgumentException("Endere�o do email � obrigat�rio.");
		}
		if (!validador.validar(endereco)) {
			throw new IllegalArgumentException("Endere�o fornecido n�o � v�lido: " + endereco);
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
