package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("/entrada")
public class AutorizacaoFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest request = (HttpServletRequest) servletRequest;
	    HttpServletResponse response = (HttpServletResponse) servletResponse;
	    
	    String acao = request.getParameter("acao");
	    
	    HttpSession sessao = request.getSession();
	    
	    boolean usuarioNaoLogado = (sessao.getAttribute("usuarioLogado") == null);
	    //A Ação de login não deve estar protegida 
	    boolean acaoProtegida = !(acao.equals("Login") || acao.equals("LoginForm"));
	    
	    //Se o usuário não existir na sessão, redireciona para a página de login
	    if(usuarioNaoLogado && acaoProtegida) {
	        response.sendRedirect("entrada?acao=LoginForm");
	        return;
	    }
	    
		chain.doFilter(request, response);
	}



}
