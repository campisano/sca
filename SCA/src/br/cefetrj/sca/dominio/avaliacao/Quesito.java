package br.cefetrj.sca.dominio.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class Quesito {

	@Id
	@GeneratedValue
	private Long id;

	private String questao;

	@OneToMany
	// por padrão cria uma inítil tabela de associação, @JoinColumn resolve isso
	@JoinColumn(name = "QUESITO_ID", referencedColumnName = "ID")
	private List<Resposta> alternativas;

	private Quesito() {
		alternativas = new ArrayList<Resposta>();
	}

	public String getQuestao() {
		return questao;
	}

	public void adicionarAlternativa(String descritor) {
		this.alternativas.add(new Resposta(descritor));
	}
}
