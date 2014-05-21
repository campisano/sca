package br.cefetrj.sca.dominio;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Turma {
	private static final int TAM_MAX_CODIGO = 7;

	@Id
	@GeneratedValue
	private Long id;

	private String codigo;

	private int capacidadeMaxima;

	@ManyToOne
	Disciplina disciplina;

	Professor professor;

	private List<Aula> aulas;

	private Set<Inscricao> inscricoes;

	@Embedded
	SemestreLetivo semestreLetivo;

	/**
	 * RN02: Uma turma não pode ter mais alunos inscritos do que a capacidade
	 * máxima definida para ela.
	 * 
	 * O c�digo da turma possui sete caracteres (e.g., 6100009)
	 * 
	 */
	public Turma(String codigo, Integer numeroVagas, SemestreLetivo periodo,
			Disciplina disciplina) {

		this.disciplina = disciplina;

		if (codigo == null || codigo.isEmpty()) {
			throw new IllegalArgumentException("Código da turma é obrigatório.");
		}
		if (codigo.length() != TAM_MAX_CODIGO) {
			throw new IllegalArgumentException("Código da turma deve ter "
					+ TAM_MAX_CODIGO + " caracteres necessariamente.");
		}
		this.codigo = codigo;

		if (numeroVagas == null) {
			throw new IllegalArgumentException("Número de vagas é obrigatório.");
		}
		if (numeroVagas <= 0) {
			throw new IllegalArgumentException(
					"Número de vagas deve ser positivo.");
		}
		this.capacidadeMaxima = numeroVagas;

		if (periodo == null) {
			throw new IllegalArgumentException("Período é obrigatório.");
		}
		this.semestreLetivo = periodo;
	}

	public Long getId() {
		return id;
	}

	public SemestreLetivo getPeriodo() {
		return this.semestreLetivo;
	}

	public int getCapacidadeMaxima() {
		return capacidadeMaxima;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public String getCodigo() {
		return codigo;
	}

	public Set<Inscricao> getInscricoes() {
		return Collections.unmodifiableSet(this.inscricoes);
	}

	public void adicionarAula(EnumDiaSemana dia, String inicio, String fim) {
		this.aulas.add(new Aula(dia, inicio, fim));
	}

	public void lancarAvaliacao(Aluno aluno, Aproveitamento avaliacao) {
		Inscricao inscricao = getInscricao(aluno);
		inscricao.registrarAvaliacao(avaliacao);
	}

	private Inscricao getInscricao(Aluno aluno) {
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getAluno() == aluno) {
				return inscricao;
			}
		}
		return null;
	}

	public boolean inscreverAluno(Aluno aluno) {
		if (inscricoes.size() + 1 > capacidadeMaxima) {
			throw new IllegalStateException("Limite de vagas já alcançado.");
		}
		if (inscricoes.size() < capacidadeMaxima) {
			Inscricao inscricao = new Inscricao(aluno);
			for (Inscricao umaInscricao : inscricoes) {
				if (umaInscricao.getAluno().getMatricula()
						.equals(aluno.getMatricula())) {
					throw new IllegalArgumentException(
							"Aluno já inscrito na turma.");
				}
			}
			inscricoes.add(inscricao);
			return true;
		}
		return false;
	}

	public void setCapacidadeMaxima(Integer capacidadeMaxima) {
		if (capacidadeMaxima == null) {
			throw new IllegalArgumentException("Número de vagas é obrigatório.");
		}
		if (capacidadeMaxima <= 0) {
			throw new IllegalArgumentException(
					"Número de vagas deve ser positivo.");
		}
		if (capacidadeMaxima < inscricoes.size()) {
			throw new IllegalArgumentException(
					"Há mais inscritos do que a capacidade máxima fornecida.");
		}
		this.capacidadeMaxima = capacidadeMaxima;
	}

	public void inscrever(Aluno aluno) {
		if (this.inscricoes.size() <= capacidadeMaxima) {
			Inscricao e = new Inscricao(aluno);
			this.inscricoes.add(e);
		} else {
			throw new IllegalStateException(
					"Não há vagas para uma nova inscrição.");
		}
	}

	/**
	 * Verifica se aluno foi aprovado nesta turma.
	 * 
	 * @param a
	 *            referência para objeto aluno
	 * @return <code>true</code> se aluno passado como parâmetro foi aprovado
	 *         nessa turma; <code>false</code> em caso contrário.
	 */
	public boolean aprovado(Aluno a) {
		// TODO Auto-generated method stub
		return false;
	}
}
