/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas;

import DAO.DAOProduto;
import DAO.DAOProdutosPrecos;
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
import model.ProdutosPrecos;
import org.json.simple.JSONObject;

/**
 *
 * @author makefake
 */
@WebServlet(name = "CadastroPrecoProdutoServlet", urlPatterns = {"/CadastroPrecoProduto"})
public class CadastroPrecoProdutoServlet extends HttpServlet {

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
        
        DAORequestReader daoR = new DAORequestReader();
        
        JSONObject js = daoR.requestReader(red);
        DAOProduto daop = new DAOProduto("root", "");
        ProdutosPrecos pp = new ProdutosPrecos();
        Produto p = daop.listProdutoCodigo(Integer.parseInt(String.valueOf(js.get("idProduto"))));
       DAOProdutosPrecos dpp =new DAOProdutosPrecos("root", "");
        p.setFgAtivo(true);
      
        
        pp.setCodProduto(p);
       pp.setCodProdutosPrecos(0);
       pp.setPeso(Double.parseDouble(String.valueOf(js.get("peso"))));
       pp.setPrecoProduto(Double.parseDouble(String.valueOf(js.get("preco"))));
       System.out.println(pp.getPrecoProduto());
       pp.setUnMedida(String.valueOf(js.get("unMedida")));
       pp.setFgAtivo(true);
       
       
       String ret = dpp.inserirPreco(pp);
       JSONObject retJSON = new JSONObject();
       retJSON.put("retorno", ret);
       // System.out.println( daop.listProdutoCodigo(Integer.parseInt(String.valueOf(js.get("idProduto")))).getNomeProduto());
     //   pp.setCodProduto();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
         out.println(retJSON);
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
