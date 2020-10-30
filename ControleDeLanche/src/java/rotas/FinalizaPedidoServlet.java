/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotas;

import DAO.DAOLanche;
import DAO.DAOPedidos;
import DAO.DAOProdutosPrecos;
import DAO.DAORequestReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lanche;
import model.Pedido;
import model.ProdutosPrecos;
import org.json.simple.JSONObject;

/**
 *
 * @author makefake
 */
@WebServlet(name = "FinalizaPedidoServlet", urlPatterns = {"/FinalizaPedido"})
public class FinalizaPedidoServlet extends HttpServlet {

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
        DAORequestReader dred = new DAORequestReader();
        JSONObject js = dred.requestReader(red);
       DAOPedidos daoP = new DAOPedidos(String.valueOf(js.get("user")), String.valueOf(js.get("senha")));
        DAOLanche dl = new DAOLanche(String.valueOf(js.get("user")), String.valueOf(js.get("senha")));
        
        Pedido pppd = new Pedido();
        
        Long codPedido = daoP.trazUltimoPedido()+1;
        int cont =0;
        
        List<Lanche> llc = dl.ListarAllLanches(1,String.valueOf(js.get("user")));
       
       DAOProdutosPrecos ppr = new DAOProdutosPrecos(String.valueOf(js.get("user")), String.valueOf(js.get("senha")));
       double totalLanche=0;
       Long codLanche=null;
      
        
       // System.out.println(llc.get(2).getCodProduto().getNomeProduto());
      /*  for(Lanche pld:llc){
             ProdutosPrecos retPPR = ppr.listarPorFiltro(pld.getCodProduto().getCodProduto());
             System.out.println("Nome: "+pld.getCodProduto().getNomeProduto()+"Preco: "+retPPR.getPrecoProduto()+"Quantidade: "+pld.getQuantidade());
             totalLanche = totalLanche+retPPR.getPrecoProduto()*pld.getQuantidade();
           
        }*/
      
        // System.out.println(totalLanche);
      int list =llc.size();
      
        while(cont<list){
            
            codLanche = llc.get(cont).getCodLanche();
            
         
            
               ProdutosPrecos retPPR = ppr.listarPorFiltro(llc.get(cont).getCodProduto().getCodProduto());
               totalLanche= totalLanche+(retPPR.getPrecoProduto()*llc.get(cont).getQuantidade()); 
               pppd.setCodLanche(llc.get(cont));
               pppd.setCodPedido(codPedido);
              
               String dataS = "2001-10-19";
               Date data = null;
               data = Date.valueOf(dataS);
               pppd.setDataEntrada(data);
               pppd.setDesconto(0.0);
                
      
               pppd.setPrecoTotal(totalLanche);
               pppd.setCpf_cnpj(llc.get(cont).getCpfCnpj());
               pppd.setFgAtivo(true);
               
               
               
     
          
           
try{
         
            if(llc.get(cont).getCodLanche()!=llc.get(cont+1).getCodLanche()){
                  System.out.println("Inserido aqui");
                 daoP.inserirPedido(pppd);
        
                dl.desativaLanche(pppd.getCodLanche().getCodLanche(), String.valueOf(js.get("user")));
                totalLanche=0;
            }
        }catch(Exception e){
                     if(cont ==list){
                    daoP.inserirPedido(pppd);
        
                     dl.desativaLanche(pppd.getCodLanche().getCodLanche(), String.valueOf(js.get("user")));
               
                        }    
                    }
          
              
           
                       
           cont++;
          
          
       }
        
         
          daoP.inserirPedido(pppd);
        
          dl.desativaLanche(pppd.getCodLanche().getCodLanche(), String.valueOf(js.get("user")));
        
      
    
        JSONObject ret = new JSONObject();
        ret.put("ret", "Pedido Realizado");
       // System.out.println("Pedido Inserido");
           
      //  System.out.println(totalLanche);
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           out.println(ret);
           
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
