package br.com.alura.gerenciador.acoes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Banco;
import br.com.alura.gerenciador.model.Empresa;

public class ListaEmpresas implements Acao {
    
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Listando Empresas");
        
        Banco banco = new Banco();
        List<Empresa> lista = banco.getEmpresas();
        
        request.setAttribute("empresas", lista);
        
        return "forward:listaEmpresas.jsp";
    }

}
