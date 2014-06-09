package br.cefetrj.sca.apresentacao.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		return "/avaliacao/solicitaAvaliacaoView";
	}

	@RequestMapping(value = "/solicitaAvaliacao", method = RequestMethod.POST)
	public String solicitaAvaliacao(@RequestParam String matricula, Model model) {

		try {
			model.addAttribute("turmas", service.solicitaAvaliacao(matricula));
			model.addAttribute("matricula", matricula);

			return "/avaliacao/solicitaAvaliacaoTurmaView";
		} catch (Exception exc) {
			model.addAttribute("error", exc.getMessage());

			return "/homeView";
		}
	}

	@RequestMapping(value = "/solicitaAvaliacaoTurma", method = RequestMethod.POST)
	public String solicitaAvaliacaoTurma(
			@ModelAttribute("matricula") String matricula,
			@RequestParam String codigoTurma, Model model) {

		try {
			model.addAttribute("questoes",
					service.solicitaAvaliacaoTurma(matricula, codigoTurma));

			return "/avaliacao/avaliaTurmaView";
		} catch (Exception exc) {
			model.addAttribute("error", exc.getMessage());

			return "/homeView";
		}
	}

	@RequestMapping(value = "/avaliaTurma", method = RequestMethod.POST)
	public String avaliaTurma(@ModelAttribute("matricula") String matricula,
			@RequestParam String codigoTurma, HttpServletRequest request,
			Model model) {

		Map<String, String[]> parameters = request.getParameterMap();
		List<Integer> respostas = new ArrayList<Integer>();

		try {
			int i = 0;
			while (parameters.containsKey("quesito" + i)) {
				respostas
						.add(Integer.parseInt(parameters.get("quesito" + i)[0]));

				++i;
			}
		} catch (Exception exc) {
			model.addAttribute("error",
					"Erro: Respostas com conteúdo inválido.");

			return "/homeView";
		}

		try {
			service.avaliaTurma(matricula, codigoTurma, respostas);
			model.addAttribute("info", "Avaliação registrada.");

			return "forward:/avaliacao/solicitaNovamenteAvaliacao";
		} catch (Exception exc) {
			model.addAttribute("error", exc.getMessage());

			return "/homeView";
		}
	}

	@RequestMapping(value = "/solicitaNovamenteAvaliacao", method = RequestMethod.POST)
	public String solicitaNovamenteAvaliacao(
			@ModelAttribute("matricula") String matricula, Model model) {

		try {
			model.addAttribute("turmas", service.solicitaAvaliacao(matricula));

			return "/avaliacao/solicitaAvaliacaoTurmaView";
		} catch (Exception exc) {
			model.addAttribute("error", exc.getMessage());

			return "/homeView";
		}
	}
}
