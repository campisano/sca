package br.cefetrj.sca.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import br.cefetrj.sca.dominio.Aluno;
import br.cefetrj.sca.dominio.AlunoRepositorio;
import br.cefetrj.sca.dominio.Disciplina;
import br.cefetrj.sca.dominio.DisciplinaRepositorio;
import br.cefetrj.sca.dominio.EnumSituacaoFinalAvaliacao;
import br.cefetrj.sca.dominio.Inscricao;
import br.cefetrj.sca.dominio.SemestreLetivo;
import br.cefetrj.sca.dominio.Turma;
import br.cefetrj.sca.dominio.TurmaRepositorio;

public class TurmasCursadasService {

	@Autowired
	private AlunoRepositorio ra;

	@Autowired
	private TurmaRepositorio rt;

	@Autowired
	private DisciplinaRepositorio dr;

	/**
	 * @return coleção de turmas cujas disciplinas da grade curricular estão
	 *         disponíveis para o aluno cursar.
	 */
	public Set<Turma> getTurmasPossiveis(Aluno a) {

		Set<Turma> possiveis = new HashSet<Turma>();

		Set<Disciplina> cursadas = getDisciplinasCursadasComAprovacao(a);
		Set<Disciplina> indisponiveis = getDisciplinasIndisponiveis(a);

		Set<Turma> abertas = rt
				.getTurmasAbertas(SemestreLetivo.SEMESTRE_LETIVO_CORRENTE);
		for (Turma turma : abertas) {
			Disciplina disciplina = turma.getDisciplina();
			if (!cursadas.contains(turma.getDisciplina())
					&& !indisponiveis.contains(disciplina)) {
				possiveis.add(turma);
			}
		}
		return possiveis;
	}

	/**
	 * @return coleção de disciplinas da grade curricular que não estão
	 *         disponíveis para o aluno cursar.
	 */
	public Set<Disciplina> getDisciplinasIndisponiveis(Aluno a) {
		Set<Disciplina> cursadas = getDisciplinasCursadasComAprovacao(a);
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
		List<Disciplina> todas = dr.getDisciplinas();
		for (Disciplina disciplina : todas) {
			Set<Disciplina> preReqs = disciplina.getPreRequisitos();
			if (!dr.estaContidaEm(preReqs, cursadas)) {
				disciplinas.add(disciplina);
			}
		}
		return disciplinas;
	}

	/**
	 * Dada a matrícula de um aluno, retorna a lista de turmas que o aluno já
	 * cursou com aprovação.
	 * 
	 * @param matriculaAluno
	 *            matrícula do aluno.
	 * 
	 * @return lista de turmas que o aluno de matrícula
	 *         <code>matriculaAluno</code> cursou com aprovação.
	 */
	public List<Turma> obterTurmasCursadasComAprovacao(String matriculaAluno) {
		Aluno aluno = ra.getByMatricula(matriculaAluno);
		List<Turma> turmas = rt.obterTodos();
		for (Turma turma : turmas) {
			Set<Inscricao> inscricoes = turma.getInscricoes();
			for (Inscricao inscricao : inscricoes) {
				if (aluno.getMatricula().equals(
						inscricao.getAluno().getMatricula())) {
					EnumSituacaoFinalAvaliacao avaliacao = inscricao
							.getAvaliacao();
					if (avaliacao != EnumSituacaoFinalAvaliacao.AP) {
						break;
					}
					turmas.add(turma);
				}
			}
		}
		return turmas;
	}

	/**
	 * @return a coleção de disciplinas que o aluno já cursou com sucesso.
	 */
	public Set<Disciplina> getDisciplinasCursadasComAprovacao(Aluno a) {
		List<Turma> turmas = obterTurmasCursadasComAprovacao(a.getMatricula());
		Set<Disciplina> disciplinas = new HashSet<Disciplina>();
		for (Turma turma : turmas) {
			if (turma.aprovado(a)) {
				disciplinas.add(turma.getDisciplina());
			}
		}
		return disciplinas;
	}

}
