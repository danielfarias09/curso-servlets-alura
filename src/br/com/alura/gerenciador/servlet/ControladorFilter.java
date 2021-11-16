package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acoes.Acao;

public class ControladorFilter implements Filter {
    private static final String PACOTE_ACAO = "br.com.alura.gerenciador.acoes.";

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
	    System.out.println("ControladorFilter");
	    
	    HttpServletRequest request = (HttpServletRequest) servletRequest;
	    HttpServletResponse response = (HttpServletResponse) servletResponse;
	    
	    String paramAcao = request.getParameter("acao");	    
	    String nomeClasse = PACOTE_ACAO + paramAcao;
        
        String nome;
        try {
            Class classe = Class.forName(nomeClasse);//A Máquina virtual carrega a classe em memória com o nome passado
            Acao acao = (Acao) classe.newInstance();// Cria uma instância da classe e faz o casting para Acao 
            nome = acao.executa(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ServletException(e);
        }
        
        String[] retorno = nome.split(":");
        String operacao = retorno[0];
        String endereco = retorno[1];
        String dirViews = "WEB-INF/view/";
        
        if(operacao.equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher(dirViews + endereco);
            rd.forward(request, response);
            
        }else {
            response.sendRedirect(endereco);
        } 
	    

	    //Este não precisa do método chain, pois é o último filtro da cadeia
		//chain.doFilter(request, response);
	}
}
