package br.com.alura.gerenciador.acoes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovaEmpresaForm implements Acao {
    
    public String  executa(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Mostrando Formulário da Empresa");
        
        return "forward:formNovaEmpresa.jsp"
;    }

}
