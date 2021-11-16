package br.com.alura.gerenciador.acoes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.model.Banco;
import br.com.alura.gerenciador.model.Usuario;

public class Login implements Acao {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");     
        
        Banco banco = new Banco();
        Usuario usuario = banco.fazerLogin(login, senha);
        
        
        if(usuario == null) {
            System.out.println("Usuário não encontrado!");
            return "redirect:entrada?acao=LoginForm";
        }       
        
        //Salva usuário na sessão
        HttpSession sessao = request.getSession();
        sessao.setAttribute("usuarioLogado", usuario);       
        return "redirect:entrada?acao=ListaEmpresas";
    }    
    
}
