package br.cefetrj.sca.service;

import java.util.ArrayList;
import java.util.List;

import br.cefetrj.sca.dominio.AvaliacaoRepositorio;
import br.cefetrj.sca.dominio.SemestreLetivo;
import br.cefetrj.sca.dominio.Turma;
import br.cefetrj.sca.dominio.TurmaRepositorio;

public class AvaliacaoService {

	private TurmaRepositorio turmaRepo;
	private AvaliacaoRepositorio avaliacaoRepo;

	public void setTurmaRepositorio(TurmaRepositorio turmaRepo) {
		this.turmaRepo = turmaRepo;
	}

	public void setAvaliacaoRepositorio(AvaliacaoRepositorio avaliacaoRepo) {
		this.avaliacaoRepo = avaliacaoRepo;
	}

	public List<String[]> solicitaAvaliacao(String matricula) {
		if (matricula == null || matricula.trim().equals("")) {
			throw new IllegalArgumentException("Matricula inválida");
		}

		List<Turma> turmas = turmaRepo.getTurmasCursadas(matricula,
				SemestreLetivo.SEMESTRE_LETIVO_CORRENTE);

		List<String[]> response = new ArrayList<String[]>();
		String[] item;

		for (Turma turma : turmas) {
			item = new String[2];
			item[0] = turma.getCodigo();
			item[1] = turma.getDisciplina().getNome();
			response.add(item);
		}

		return response;
	}

}
