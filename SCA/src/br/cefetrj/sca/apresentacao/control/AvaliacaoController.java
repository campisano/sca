package br.cefetrj.sca.apresentacao.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import br.cefetrj.sca.dominio.AvaliacaoRepositorio;
import br.cefetrj.sca.dominio.TurmaRepositorio;
import br.cefetrj.sca.service.AvaliacaoService;

public class AvaliacaoController implements Controller, BeanFactoryAware {
	private AvaliacaoService service;

	@Override
	public void setBeanFactory(BeanFactory context) {
		service = (AvaliacaoService) context.getBean("AvaliacaoServiceBean");
		service.setTurmaRepositorio((TurmaRepositorio) context
				.getBean("TurmaRepositorioBean"));
		service.setAvaliacaoRepositorio((AvaliacaoRepositorio) context
				.getBean("AvaliacaoRepositorioBean"));
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");

		if (command.equals("solicitaAvaliacao")) {
			return solicitaAvaliacao(request, response);
		} else {

			ModelAndView mv = new ModelAndView("erro.jsp");
			mv.addObject("msg", "Comando inválido");

			return mv;
		}
	}

	private ModelAndView solicitaAvaliacao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String matricula = request.getParameter("matricula");

		List<String[]> turmas = service.solicitaAvaliacao(matricula);

		ModelAndView mv = new ModelAndView("turmas.jsp");
		mv.addObject("turmas", turmas);

		return mv;
	}
}
