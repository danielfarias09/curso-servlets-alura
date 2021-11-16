package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//Todas as requisições que passam pelo servlet que está mapeado com /entrada 
//também passarão antes por este Filter
//@WebFilter(urlPatterns="/entrada")
public class MonitoramentoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long antes = System.currentTimeMillis();
        
        //Executa a ação
        chain.doFilter(request, response);
        
        
        long depois = System.currentTimeMillis();
        
        String acao = request.getParameter("acao");
        
        System.out.println("Tempo de Execução da ação " + acao + ": " + (depois - antes));
        
    }

}
