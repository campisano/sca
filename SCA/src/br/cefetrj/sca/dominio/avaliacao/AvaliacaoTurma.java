package br.cefetrj.sca.dominio.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.cefetrj.sca.dominio.Aluno;
import br.cefetrj.sca.dominio.Turma;

public class AvaliacaoTurma {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany
	private List<Resposta> respostas;

	@ManyToOne
	private Turma turmaAvaliada;

	@ManyToOne
	private Aluno alunoAvaliador;

	@SuppressWarnings("unused")
	private AvaliacaoTurma() {
		respostas = new ArrayList<Resposta>();
	}

	public AvaliacaoTurma(Aluno aluno, Turma turma, List<Resposta> respostas) {
		// TODO [BZR] restrições para manter a consistência e as invariantes!
		// ex um dos test é verificar que tenha de 1 até 8 respostas
	}

	public Long getId() {
		return id;
	}
}
