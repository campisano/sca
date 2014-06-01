package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.cefetrj.sca.service.FornecerGradeService;

public class IncluirProfessor {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });

		FornecerGradeService serv = (FornecerGradeService) context
				.getBean("FornecerGradeServiceBean");

		serv.incluirProfessor("1506449", "Eduardo Bezerra");
	}
}