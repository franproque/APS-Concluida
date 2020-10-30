/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas;

import DAO.DAOProduto;
import DAO.DAORequestReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import org.json.simple.JSONObject;

/**
 *
 * @author makefake
 */
@WebServlet(name = "NovoProdutoServlet", urlPatterns = {"/NovoProduto"})
public class NovoProdutoServlet extends HttpServlet {

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
        response.setContentType("application/json");
        
        BufferedReader red = request.getReader();
        DAORequestReader dr = new DAORequestReader();
        JSONObject jsRed = dr.requestReader(red);
        DAOProduto daoP = new DAOProduto("root", "");
        Produto pd = new Produto();
        
        pd.setCodProduto(daoP.ultimoNumeroProduto()+1);
        pd.setNomeProduto(String.valueOf(jsRed.get("nomeProduto")));
        pd.setImagemProduto(String.valueOf(jsRed.get("imagemProduto")));
        pd.setDescricaoProduto(String.valueOf(jsRed.get("descricaoProduto")));
        pd.setFgAtivo(true);
        
        
        String ret =daoP.novoProduto(pd);
        
        JSONObject js = new JSONObject();
             js.put("retorno", ret);
                
        try (PrintWriter out = response.getWriter()) {
         
         
            out.println(js);
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
