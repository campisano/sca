package br.cefetrj.sca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.cefetrj.sca.dominio.SemestreLetivo;
import br.cefetrj.sca.dominio.Turma;
import br.cefetrj.sca.dominio.repositorio.AvaliacaoRepositorio;
import br.cefetrj.sca.dominio.repositorio.TurmaRepositorio;

@Component
public class AvaliacaoService {

	@Autowired
	private TurmaRepositorio turmaRepo;

	@Autowired
	private AvaliacaoRepositorio avaliacaoRepo;

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
