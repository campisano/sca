package br.cefetrj.sca.apresentacao;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/")
public class IndexView {

	@RequestMapping(value = "/")
	public String solicitaAvaliacao(ModelMap model) {

		return "/index";
	}
}
