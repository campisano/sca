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

@Entity
public class AvaliacaoTurma {
	@Id
	@GeneratedValue
	Long id;

	public Long getId() {
		return id;
	}

	private AvaliacaoTurma() {
	}

	public AvaliacaoTurma(Aluno aluno, Turma turma, List<Alternativa> respostas) {
		// restrições para manter a consistência e as invariantes!
	}

	@ManyToMany
	List<Alternativa> respostas = new ArrayList<Alternativa>();

	@ManyToOne
	Turma turmaAvaliada;

	@ManyToOne
	Aluno alunoAvaliador;
}
