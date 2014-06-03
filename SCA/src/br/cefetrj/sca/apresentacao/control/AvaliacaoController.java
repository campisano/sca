package br.cefetrj.sca.apresentacao.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.cefetrj.sca.service.AvaliacaoService;

@Controller
@SessionAttributes("matricula")
@RequestMapping("/avaliacao")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService service;

	@RequestMapping(value = "/{*}", method = RequestMethod.GET)
	public String get() {
		return "/avaliacao/solicitaAvaliacao";
	}

	@RequestMapping(value = "/solicitaAvaliacao", method = RequestMethod.POST)
	public String solicitaAvaliacao(@RequestParam String matricula, Model model) {

		try {
			model.addAttribute("turmas", service.solicitaAvaliacao(matricula));
			model.addAttribute("matricula", matricula);

			return "/avaliacao/solicitaAvaliacaoTurma";
		} catch (Exception exc) {
			model.addAttribute("error", exc.getMessage());
			return "/home";
		}
	}

	@RequestMapping(value = "/solicitaAvaliacaoTurma", method = RequestMethod.POST)
	public String solicitaAvaliacaoTurma(@RequestParam String codigoTurma,
			Model model) {

		try {
			model.addAttribute("questoes",
					service.solicitaAvaliacaoTurma(codigoTurma));

			return "/avaliacao/avaliaTurma";
		} catch (Exception exc) {
			model.addAttribute("error", exc.getMessage());
			return "/home";
		}
	}

	@RequestMapping(value = "/avaliaTurma", method = RequestMethod.POST)
	public String avaliaTurma(@RequestParam String codigoTurma, Model model) {

		try {
			model.addAttribute("questoes",
					service.solicitaAvaliacaoTurma(codigoTurma)); // TODO [CMP]

			model.addAttribute("info", "Avaliação registrada.");

			return "forward:/avaliacao/solicitaNovamenteAvaliacao";
		} catch (Exception exc) {
			model.addAttribute("error", exc.getMessage());
			return "/home";
		}
	}

	@RequestMapping(value = "/solicitaNovamenteAvaliacao", method = RequestMethod.POST)
	public String solicitaNovamenteAvaliacao(
			@ModelAttribute("matricula") String matricula, Model model) {

		try {
			model.addAttribute("turmas", service.solicitaAvaliacao(matricula));

			return "/avaliacao/solicitaAvaliacaoTurma";
		} catch (Exception exc) {
			model.addAttribute("error", exc.getMessage());
			return "/home";
		}
	}
}
