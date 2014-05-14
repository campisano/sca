package br.cefetrj.sca.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.cefetrj.sca.dominio.Professor;
import br.cefetrj.sca.dominio.ProfessorRepo;

public class FornecerGradeService {

	@Autowired
	private ProfessorRepo professorRepo;

	public Professor validarProfessor(String matriculaProfessor) {
		return professorRepo.obterProfessor(matriculaProfessor);
	}
}
