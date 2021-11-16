package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acoes.Acao;

//Não está mais um uso, virou ControladorFilter
//@WebServlet("/entrada")
public class UnicoServlet extends HttpServlet {

    private static final long serialVersionUID = -2419927588194005253L;
    
    private static final String PACOTE_ACAO = "br.com.alura.gerenciador.acoes.";   
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        
    }

}
