/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas;

import DAO.DAOLanche;
import DAO.DAOProduto;
import DAO.DAORequestReader;
import DAO.DAOUsuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lanche;
import model.Usuario;
import org.json.simple.JSONObject;

/**
 *
 * @author makefake
 */
@WebServlet(name = "InserirItensLancheServlet", urlPatterns = {"/InserirItensLanche"})
public class InserirItensLancheServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader red = request.getReader();
        DAORequestReader daoReq =new DAORequestReader();
        
        JSONObject js = daoReq.requestReader(red);
        DAOUsuario daoUser = new DAOUsuario(String.valueOf(js.get("user")),String.valueOf(js.get("senha")));
        Lanche lc = new Lanche();
        DAOProduto daoP = new DAOProduto(String.valueOf(js.get("user")),String.valueOf(js.get("senha")));
        
       
       lc.setCodLanche(Integer.parseInt(String.valueOf(js.get("lanche"))));
      
        lc.setCodProduto(daoP.listProdutoCodigo(Integer.parseInt(String.valueOf(js.get("idItem")))));
      
        
      Usuario us = daoUser.trasUsuarioPeloCPF(String.valueOf(js.get("user")));
      
       System.out.println(us.getNomeFantasiaNomeCompleto());
        lc.setCpfCnpj(us);
      lc.setQuantidade(Integer.parseInt(String.valueOf(js.get("quantidade"))));
        lc.setFgAtivo(true);
        
      
       DAOLanche daoLc = new DAOLanche(String.valueOf(js.get("user")),String.valueOf(js.get("senha")));
        JSONObject retorno = new JSONObject();
      
        
       if(daoLc.incluirLancheProduto(lc)){
            retorno.put("retorno", "ok");
        }else{
            retorno.put("retorno", "erro");
        }
        
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(retorno);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
