package br.cefetrj.sca.dominio.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Representa um dos quesitos usados para avaliação de uma turma por um aluno.
 * Um quesito é composto por um enunciado e diversas respostas alternativas.
 * 
 * @author Eduardo
 * 
 */
@Entity
public class Quesito {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	private String enunciado;

	@ManyToMany
	@JoinTable(name = "QUESITO_ALTERNATIVA", joinColumns = { @JoinColumn(name = "QUESITO_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "ALTERNATIVA_ID", referencedColumnName = "ID") })
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();

	@SuppressWarnings("unused")
	private Quesito() {

	}

	public Quesito(String enunciado) {
		this.enunciado = enunciado;
	}

	public void adicionarAlternativa(Alternativa alternativa) {
		this.alternativas.add(alternativa);
	}

	public String getEnunciado() {
		return enunciado;
	}
}
