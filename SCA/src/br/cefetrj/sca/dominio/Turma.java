package br.cefetrj.sca.dominio;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Turma {
	/**
	 * Quantidade de caracteres necessários no código de uma turma.
	 */
	private static final int TAM_MAX_CODIGO = 7;

	private static final int CAPACIDADE_PRESUMIDA = 40;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * O código da turma. Deve necessariamente possuir sete caracteres (e.g.,
	 * 6100009)
	 */
	private String codigo;

	/**
	 * Capacidade máxima da turma. Se não for definido pelo construtor, seu
	 * valor presumido é <code>CAPACIDADE_PRESUMIDA</code>.
	 */
	private int capacidadeMaxima = CAPACIDADE_PRESUMIDA;

	/**
	 * Disciplina para a qual esta turma foi aberta.
	 */
	@ManyToOne
	Disciplina disciplina;

	/**
	 * Professor responsável por esta turma.
	 */
	@ManyToOne
	Professor professor;

	/**
	 * Informações sobre locais e horários de aulas dessa turma.
	 */
	@OneToMany
	@JoinColumn(name = "TURMA_ID", referencedColumnName = "ID")
	private List<Aula> aulas;

	/**
	 * Inscrições realizadas nesta turma.
	 */
	@Transient
	private Set<Inscricao> inscricoes;

	/**
	 * Semestre letivo em que esta turma é ofertada.
	 */
	@Embedded
	SemestreLetivo semestreLetivo;

	/**
	 * Cria uma turma com disciplina e código fornecidos como parâmetros. A
	 * capacidade máxima da turma criada é igual a
	 * <code>CAPACIDADE_PRESUMIDA</code>. O semestre letvo da turma criada é
	 * <code>SemestreLetivo.SEMESTRE_LETIVO_CORRENTE</code>.
	 * 
	 */
	public Turma(Disciplina disciplina, String codigo) {

		if (disciplina == null) {
			throw new IllegalArgumentException("Disciplina não fornecida!");
		}
		this.disciplina = disciplina;

		if (codigo == null || codigo.isEmpty()) {
			throw new IllegalArgumentException("Código da turma é obrigatório.");
		}
		// if (codigo.length() != TAM_MAX_CODIGO) {
		// throw new IllegalArgumentException("Código da turma deve ter "
		// + TAM_MAX_CODIGO + " caracteres necessariamente.");
		// }
		this.codigo = codigo;

		this.semestreLetivo = SemestreLetivo.SEMESTRE_LETIVO_CORRENTE;
		this.capacidadeMaxima = CAPACIDADE_PRESUMIDA;
	}

	/**
	 * Cria uma turma com disciplina, código, número de vagas e semestre letivo
	 * fornecidos como parâmetros. A capacidade máxima da turma criada é igual a
	 */
	public Turma(Disciplina disciplina, String codigo, Integer numeroVagas,
			SemestreLetivo periodo) {

		this(disciplina, codigo);

		if (numeroVagas == null) {
			throw new IllegalArgumentException("Número de vagas indefinido!");
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

	/**
	 * Inscreve o aluno passado como parâmetro nesta turma.
	 * 
	 * RN02: Uma turma não pode ter mais alunos inscritos do que a capacidade
	 * máxima definida para ela.
	 */
	public void inscreverAluno(Aluno aluno) {
		if (inscricoes.size() + 1 > capacidadeMaxima) {
			throw new IllegalStateException("Limite de vagas já alcançado.");
		} else {
			Inscricao inscricao = new Inscricao(aluno);
			for (Inscricao umaInscricao : inscricoes) {
				if (umaInscricao.getAluno().getMatricula()
						.equals(aluno.getMatricula())) {
					throw new IllegalArgumentException(
							"Aluno já inscrito na turma.");
				}
			}
			inscricoes.add(inscricao);
		}
	}

	/**
	 * Altera a capacidade máxima de inscrições para esta turma.
	 * 
	 * @param capacidadeMaxima
	 *            nova capacidade máxima de inscrições.
	 * 
	 * @throws IllegalArgumentException
	 *             se <code>capacidadeMaxima</code> é nulo ou se não é um número
	 *             positivo, ou se for um valor menor do que a quantidade atual
	 *             de inscrições.
	 */
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
