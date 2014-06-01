package br.cefetrj.sca.dominio.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@ManyToMany
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();

	/**
	 * A turma avaliada.
	 */
	@ManyToOne
	private Turma turmaAvaliada;

	/**
	 * A aluno avaliador.
	 */
	@ManyToOne
	private Aluno alunoAvaliador;

	@SuppressWarnings("unused")
	private AvaliacaoTurma() {
	}

	public AvaliacaoTurma(Aluno aluno, Turma turma,
			List<Alternativa> alternativas) {
		// TODO: restrições para manter a consistência e as invariantes!
	}

	public Long getId() {
		return id;
	}
}
