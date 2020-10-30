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
import model.Usuario;
import java.sql.Date;
import java.sql.Statement;
/**
 *
 * @author makefake
 */
public class DAOUsuario {
   private Connection c; 
   public DAOUsuario(String usuario, String pass){
       this.c=new Conecta().conecta(usuario,pass);
   }
   //Verifica o usuario no banco;
    public String verificaLogin(String email){
        
       String cpf="";
       try{
          ResultSet rs;
             PreparedStatement stmt = c.prepareStatement("Select cpf_cnpj,email from tb_usuario where cpf_cnpj ='"+email+"'");
            rs= stmt.executeQuery();
          
            while(rs.next()){
                cpf=rs.getString("cpf_cnpj");
            }
              stmt.close();
            
             System.out.println(cpf);
       }  catch(SQLException e){
           System.out.println(e);
       }
      
       return cpf;
    }
    public void executaSQL(String sql){
                      try{
            Statement put=c.createStatement();
            put.executeUpdate(sql);
             //put.execute(sql2);
             put.close();
       
  
           
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
             
    }
    public void criarUsuarioSGBD(String email, String pass){
       String sql="CREATE USER '"+email+"'@'localhost' IDENTIFIED BY '"+pass+"';";
      
                
                  String sql2="GRANT select ON DB_LANCHONETE.tb_produtos TO '"+email+"'@'localhost' WITH GRANT OPTION;";
                  String sql3="GRANT select ON DB_LANCHONETE.tb_produtos_precos TO '"+email+"'@'localhost' WITH GRANT OPTION; ";
                  String sql4="GRANT select,insert,update,delete ON DB_LANCHONETE.tb_lanches TO '"+email+"'@'localhost' WITH GRANT OPTION; ";
                  String sql5="GRANT insert,select,update ON DB_LANCHONETE.tb_enderecos TO '"+email+"'@'localhost' WITH GRANT OPTION; ";
                  String sql6="GRANT select,insert ON DB_LANCHONETE.tb_pedidos TO '"+email+"'@'localhost' WITH GRANT OPTION; ";
                  String sql7="GRANT select,insert ON DB_LANCHONETE.tb_usuario TO '"+email+"'@'localhost' WITH GRANT OPTION; ";
                      
                executaSQL(sql);
           
                executaSQL(sql2);
                executaSQL(sql3);
                executaSQL(sql4);
                executaSQL(sql5);
                executaSQL(sql6);
                executaSQL(sql7);
            
                
  
        
    }
     public Usuario trasUsuarioPeloCPF(String cpf){
        Usuario user = new Usuario();
       try{
          ResultSet rs;
             PreparedStatement stmt = c.prepareStatement("SELECT * FROM tb_usuario where cpf_cnpj ='"+cpf+"'");
            rs= stmt.executeQuery();
            
        while(rs.next()){
            user.setCpf_cnpj(rs.getString("cpf_cnpj"));
            user.setDataNascimento(rs.getDate("data_nascimento"));
            user.setEmail(rs.getString("email"));
            user.setFgAtivo(rs.getBoolean("fg_ativo"));
            user.setNomeFantasiaNomeCompleto(rs.getString("nome_fantasia_nome_completo"));
            user.setTelefone(rs.getString("telefone"));
            user.setTelefoneFixo(rs.getString("telefone_fixo"));
        }
     
         stmt.close();
            
         c.close();
    }catch(SQLException e){
        e.printStackTrace();
    }
     
       return user;
     }
    public String criarNovoUsuario(Usuario user,String senha){
        String ok="";
        criarUsuarioSGBD(user.getCpf_cnpj(), senha);
        try{
            String sql="INSERT INTO tb_usuario(cpf_cnpj,email,nome_fantasia_nome_completo,telefone_fixo,telefone,data_nascimento,fg_ativo)" +
                        "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stmt = c.prepareStatement(sql);
            
            stmt.setString(1, user.getCpf_cnpj());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getNomeFantasiaNomeCompleto());
            stmt.setString(4, user.getTelefoneFixo());
            stmt.setString(5, user.getTelefone());
            stmt.setDate(6, user.getDataNascimento());
            stmt.setBoolean(7, user.getFgAtivo());
            
            stmt.execute();
            stmt.close();
            
           
                ok="Registrado";
                System.out.println("registrado");
            
           
            
        }catch(SQLException e){
            ok=String.valueOf(e);
        }
         return ok;
    }
   
}
