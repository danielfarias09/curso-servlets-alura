package br.com.alura.gerenciador.acoes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Banco;
import br.com.alura.gerenciador.model.Empresa;

public class MostraEmpresa implements Acao {
    
    public String  executa(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Mostando dados da Empresa");
        
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);
        
        Banco banco = new Banco();
        
        Empresa empresa = banco.buscaEmpresaPelaId(id);
        
        System.out.println(empresa.getNome());

        request.setAttribute("empresa", empresa);
        
        return "forward:formAlteraEmpresa.jsp"
;    }

}
