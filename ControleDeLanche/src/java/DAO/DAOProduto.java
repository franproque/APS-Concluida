/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import org.json.simple.JSONObject;

/**
 *
 * @author makefake
 */
public class DAOProduto {
    private Connection c;
    public DAOProduto(String usuario, String pass){
        this.c =new Conecta().conecta(usuario, pass);
    }
    
    
        public Produto listProdutoCodigo(Integer id){
        Produto lp =new 
        Produto();
       
        String sql="SELECT cod_produto, nome_produto, descricao_produto, imagem_produto,fg_ativo as \"fg_ativo_produto\" FROM tb_produtos where cod_produto ="+id;
        try{
             ResultSet rs;
           PreparedStatement stmt = c.prepareStatement(sql);
           rs=stmt.executeQuery();
            Produto p = new Produto();
           while(rs.next()){
              
              
              
               
               p.setCodProduto(rs.getLong("cod_produto"));
               p.setDescricaoProduto(rs.getString("descricao_produto"));
               p.setFgAtivo(rs.getBoolean("fg_ativo_produto"));
               p.setImagemProduto(rs.getString("imagem_produto"));
               p.setNomeProduto(rs.getString("nome_produto"));
               
           
               
                    

              
           }
                 lp=p;
            stmt.close();
              
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return lp;
    }
        
        public String novoProduto(Produto pd){
          String  ret="";
            String sql="INSERT INTO tb_produtos(cod_produto,nome_produto,descricao_produto,imagem_produto,fg_ativo) VALUES(?,?,?,?,?)";
            
            try{
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setLong(1, pd.getCodProduto());
                stmt.setString(2,pd.getNomeProduto() );
                stmt.setString(3, pd.getDescricaoProduto());
                stmt.setString(4, pd.getImagemProduto());
                stmt.setBoolean(5, true);
                
                stmt.execute();
                
                stmt.close();
                ret="Inserido com sucesso";
            }catch(SQLException e){
                ret =String.valueOf(e);
            }
            return ret;
        }
        
        public Long ultimoNumeroProduto(){
            Long ret =null;
            String sql = "SELECT MAX(cod_produto) as ultimoCodigo from tb_produtos";
            try{
                ResultSet rs;
                
                PreparedStatement stmt = c.prepareStatement(sql);
                
                rs=stmt.executeQuery();
                
                while(rs.next()){
                    ret = rs.getLong("ultimoCodigo");
                }
                stmt.close();
            }catch(SQLException e){
                
            }
            return ret;
            
        }
        
        public List<JSONObject> listarTodosOsProdutos(){
            
            List<JSONObject> lJSON = new ArrayList<JSONObject>();
            
            try{
                String  sql="SELECT cod_produto, nome_produto, descricao_produto, imagem_produto from tb_produtos ";
                ResultSet rs;
                PreparedStatement stmt = c.prepareStatement(sql);
                rs= stmt.executeQuery();
                
                while(rs.next()){
                    JSONObject js = new JSONObject();
                    
                    js.put("codProduto", rs.getLong("cod_produto"));
                    js.put("nomeProduto", rs.getString("nome_produto"));
                    js.put("descricaoProduto",rs.getString("descricao_produto"));
                    
                    lJSON.add(js);
                }
                
            }catch(SQLException e){
                e.printStackTrace();
            }
            
            return lJSON;
        }
    
}
