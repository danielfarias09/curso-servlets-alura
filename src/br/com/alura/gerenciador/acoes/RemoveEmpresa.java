package br.com.alura.gerenciador.acoes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Banco;

public class RemoveEmpresa implements Acao {
    
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Removendo Empresas");
        
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);
        
        System.out.println(id);
        
        Banco banco = new Banco();
        banco.removeEmpresa(id);
        
        return "redirect:entrada?acao=ListaEmpresas";
    }

}
