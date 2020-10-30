/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas;

import DAO.DAOLanche;
import DAO.DAOProdutosPrecos;
import DAO.DAORequestReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lanche;
import org.json.simple.JSONObject;

/**
 *
 * @author makefake
 */
@WebServlet(name = "ListarLancheServlet", urlPatterns = {"/ListarLanche"})
public class ListarLancheServlet extends HttpServlet {

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
        
        DAORequestReader vm = new DAORequestReader();
        JSONObject js= vm.requestReader(red);
        ArrayList<JSONObject> lanchesJSON = new ArrayList<JSONObject>();
        DAOLanche dll = new DAOLanche(String.valueOf(js.get("user")),String.valueOf(js.get("senha")));
      
        List<Lanche>lac= dll.ListarAllLanches(1, String.valueOf(js.get("user")));
    
      
       int cont =0;
       Long ultimoValor=null;
       
       while(cont<lac.size()){
            Long cd =lac.get(cont).getCodLanche();
           
            if(cd!=ultimoValor){
            JSONObject llcJSON = new JSONObject();
            llcJSON.put("codLanche", lac.get(cont).getCodLanche());
            
                 lanchesJSON.add(llcJSON);
            }
            
            
            ultimoValor= lac.get(cont).getCodLanche();
           
           cont++;
           
         
       }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          out.println(lanchesJSON);
            
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
