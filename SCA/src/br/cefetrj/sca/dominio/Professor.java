package br.cefetrj.sca.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Professor {
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 * Nome completo do professor.
	 */
	String nome;

	/**
	 * Matrícula do professor, composta de 7 dígitos.
	 */
	String matricula;

	private Professor() {
	}

	public Professor(String matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public String getMatricula() {
		return matricula;
	}
}
