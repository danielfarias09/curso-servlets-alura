package br.com.alura.gerenciador.acoes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Acao {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessao = request.getSession();
        //sessao.removeAttribute("usuarioLogado");
        //Remove Todos os atributos da sessão com cookies e etc
        sessao.invalidate();
        
        return "redirect:entrada?acao=LoginForm";
    }

}
