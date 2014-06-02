package br.cefetrj.sca.apresentacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.cefetrj.sca.service.AvaliacaoService;

@Component
@RequestMapping("/avaliacao")
public class AvaliacaoView {

	@Autowired
	private AvaliacaoService service;

	@RequestMapping(value = "/solicitaAvaliacao", method = RequestMethod.POST)
	public String solicitaAvaliacao(@RequestParam String matricula, ModelMap model) {

		model.addAttribute("turmas", service.solicitaAvaliacao(matricula));
		model.addAttribute("msg", "done");

		return "/avaliacao.jsp";
	}
}
