package br.cefetrj.sca.apresentacao.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.cefetrj.sca.service.AvaliacaoService;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService service;

	@RequestMapping(value = "/solicitaAvaliacao", method = RequestMethod.POST)
	public String solicitaAvaliacao(@RequestParam String matricula,
			ModelMap model) {

		try {
			model.addAttribute("turmas", service.solicitaAvaliacao(matricula));

			return "avaliacao";
		} catch (Exception exc) {
			model.addAttribute("msg", exc.getMessage());
			return "/home";
		}
	}

	@RequestMapping(value = "/solicitaAvaliacaoTurma", method = RequestMethod.POST)
	public String solicitaAvaliacaoTurma(@RequestParam String codigoTurma,
			ModelMap model) {

		try {
			model.addAttribute("questoes",
					service.solicitaAvaliacaoTurma(codigoTurma));

			return "avalia";
		} catch (Exception exc) {
			model.addAttribute("msg", exc.getMessage());
			return "/home";
		}
	}
}
