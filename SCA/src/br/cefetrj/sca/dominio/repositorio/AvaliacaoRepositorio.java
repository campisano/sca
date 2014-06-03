package br.cefetrj.sca.dominio.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.cefetrj.sca.dominio.avaliacao.AvaliacaoTurma;
import br.cefetrj.sca.infra.AvaliacaoDao;

@Component
public class AvaliacaoRepositorio {

	@Autowired
	private AvaliacaoDao avaliacaoDAO;

	private AvaliacaoRepositorio() {
	}

	public AvaliacaoTurma getAvaliacaoTurma(String codigoTurma) {
		return avaliacaoDAO.getAvaliacaoTurma(codigoTurma);
	}
}
