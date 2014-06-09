package br.cefetrj.sca.dominio.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.cefetrj.sca.dominio.Aluno;
import br.cefetrj.sca.dominio.Turma;

/**
 * Representa a avaliação de uma turma por um aluno que a cursou. Uma avaliação
 * é composta por diversos quesitos. Para cada um deles, o aluno seleciona uma
 * resposta objetiva correspondente.
 * 
 * @see br.cefetrj.sca.dominio.avaliacao.Quesito
 * @see br.cefetrj.sca.dominio.avaliacao.Alternativa
 * 
 * @author Eduardo
 * 
 */
@Entity
public class AvaliacaoTurma {
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Alternativas selecionadas pelo aluno para cada um dos quesitos de
	 * avaliação.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "RESPOSTA", joinColumns = { @JoinColumn(name = "AVALIACAOTURMA_ID", referencedColumnName = "ID", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ALTERNATIVA_ID", referencedColumnName = "ID", nullable = false) })
	private List<Alternativa> respostas = new ArrayList<Alternativa>();

	/**
	 * A turma avaliada.
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Turma turmaAvaliada;

	/**
	 * A aluno avaliador.
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Aluno alunoAvaliador;

	@SuppressWarnings("unused")
	private AvaliacaoTurma() {
	}

	public AvaliacaoTurma(Aluno aluno, Turma turma, List<Alternativa> respostas) {
		if (aluno == null || turma == null || respostas == null) {
			throw new IllegalArgumentException(
					"Erro: argumentos inválidos para AvaliacaoTurma().");
		}

		this.alunoAvaliador = aluno;
		this.turmaAvaliada = turma;
		this.respostas.addAll(respostas);
	}

	public Long getId() {
		return id;
	}

	public List<Alternativa> getRespostas() {
		return respostas;
	}
}
