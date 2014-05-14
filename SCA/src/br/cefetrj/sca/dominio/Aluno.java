package br.cefetrj.sca.dominio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Aluno {
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 * Nome completo do aluno.
	 */
	String nome;

	/**
	 * Matrícula do aluno, composta de 7 dígitos.
	 */
	String matricula;

	public Aluno(String matricula, String nome) {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public Long getId() {
		return id;
	}
}
