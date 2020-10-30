/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas;

import DAO.DAORequestReader;
import DAO.DAOUsuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import model.Usuario;
import org.json.simple.JSONObject;

//import model.Usuario;
//import model.UsuarioPK;

/**
 *
 * @author makefake
 */
@WebServlet(name = "NovoUsuario", urlPatterns = {"/register"})
public class NovoUsuarioServlet extends HttpServlet {

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
        DAORequestReader daoReq = new DAORequestReader();
        JSONObject js = daoReq.requestReader(red);
        String dataS= String.valueOf(js.get("dataNascimento"));
         DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date data = null;
        data = Date.valueOf(dataS);
        System.out.println(data);
        Usuario user = new Usuario();
        user.setCpf_cnpj(String.valueOf(js.get("cpf_cnpj")));
        user.setDataNascimento(data);
        user.setEmail(String.valueOf(js.get("email")));
        user.setNomeFantasiaNomeCompleto(String.valueOf(js.get("nomeFantasiaNomeCompleto")));
        user.setFgAtivo(true);
        user.setTelefone(String.valueOf(js.get("telefone")));
        user.setTelefoneFixo("3333");
        
        DAOUsuario daoUser = new DAOUsuario("root","");
        
       // daoUser.criarNovoUsuario(user);
      //  System.out.println(user.getNomeFantasiaNomeCompleto());
        
        
        
     JSONObject jsRetorno = new JSONObject();
     
            /* TODO output your page here. You may use following sample code. */
            jsRetorno.put("retorno",daoUser.criarNovoUsuario(user,String.valueOf(js.get("senha"))));
                 
                  try(PrintWriter out = response.getWriter()){
                 out.println(jsRetorno);
       
 
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
