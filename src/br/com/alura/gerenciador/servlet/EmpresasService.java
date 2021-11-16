package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.model.Banco;
import br.com.alura.gerenciador.model.Empresa;

@WebServlet("/empresas")//Webservice que retorna json ao invés da uma página html
public class EmpresasService extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Empresa> empresas = new Banco().getEmpresas();
		
		//Recebe o tipo de resposta no header
		String tipoResposta = request.getHeader("Accept");
		
		if(tipoResposta.contains("json")) {
		    Gson gson = new Gson();
	        String json = gson.toJson(empresas);  
	        
	        response.setContentType("application/json");
	        response.getWriter().print(json);
		    
		}else if(tipoResposta.contains("xml")) {
		    XStream xstream = new XStream();
	        xstream.alias("empresa", Empresa.class);
	        String xml = xstream.toXML(empresas);  
	        
	        response.setContentType("application/xml");
	        response.getWriter().print(xml);
		}else {
		    response.setContentType("application/json");
            response.getWriter().print("{'message': 'No Content'}");
		}

	}

}
