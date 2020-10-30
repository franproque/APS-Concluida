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
import model.Lanche;
import model.Produto;
import model.Usuario;

/**
 *
 * @author makefake
 */
public class DAOLanche {
    private Connection c;
    public  DAOLanche(String usuario, String pass){
       this.c = new Conecta().conecta(usuario, pass);
    }
    
    public Boolean incluirLancheProduto(Lanche lc){
        Boolean retorno =true;
        try{
            String sql="INSERT INTO tb_lanches(cod_lanche,cpf_cnpj,cod_produto,quantidade,fg_ativo)\n" +
                        "VALUES(?,?,?,?,?);";
            PreparedStatement stmt = c.prepareStatement(sql);
            
            stmt.setLong(1, lc.getCodLanche());
            stmt.setString(2,lc.getCpfCnpj().getCpf_cnpj());
            stmt.setLong(3, lc.getCodProduto().getCodProduto());
            stmt.setLong(4, lc.getQuantidade());
            stmt.setBoolean(5, lc.getFgAtivo());
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return retorno;
    }
    
    public List<Lanche> ListarAllLanches(int tipoSelect,String filtro){
        List<Lanche> llc= new ArrayList<Lanche>();
     
     //   String sql="SELECT l.cod_lanche, l.cpf_cnpj, l.quantidade,l.fg_ativo as 'fg_ativo_lanche',p.cod_produto,p.nome_produto,p.descricao_produto,p.imagem_produto,p.fg_ativo as 'fg_ativo_produto',us.email,us.nome_fantasia_nome_completo,us.telefone_fixo,us.telefone, us.data_nascimento, us.fg_ativo as 'fg_ativo_usuario' FROM tb_lanches as l INNER JOIN tb_produtos as p on  p.cod_produto = l.cod_produto INNER JOIN tb_usuario as us ON us.cpf_cnpj= l.cpf_cnpj where l.fg_ativo = TRUE;";
     String sql="SELECT l.cod_lanche,l.cod_produto, l.cpf_cnpj, l.quantidade, l.fg_ativo as 'fg_ativo_lanche',p.nome_produto,p.descricao_produto,p.imagem_produto, p.fg_ativo as 'fg_ativo_produto',us.email,us.nome_fantasia_nome_completo,us.telefone_fixo,us.telefone,us.data_nascimento,us.fg_ativo as 'fg_ativo_usuario'FROM tb_lanches as l INNER JOIN tb_produtos as p ON l.cod_produto = p.cod_produto INNER JOIN tb_usuario as us ON l.cpf_cnpj = us.cpf_cnpj where l.fg_ativo = True " ;
     if(tipoSelect ==1){
         sql =sql+" and l.cpf_cnpj ='"+filtro+"' order by l.cod_lanche asc" ;
     }else if(tipoSelect ==2){
         sql = sql+" and l.cod_lanche ="+Long.parseLong(filtro);
     }
     try{
              ResultSet rs;
           PreparedStatement stmt = c.prepareStatement(sql);
           rs=stmt.executeQuery();
           
      
          // System.out.println(rs);
          while(rs.next()){
                 Lanche blc = new Lanche();
                 Produto p = new Produto();
                 Usuario us = new Usuario();
               blc.setCodLanche(rs.getLong("cod_lanche"));
               p.setCodProduto(rs.getLong("cod_produto"));
               p.setNomeProduto(rs.getString("nome_produto"));
               p.setDescricaoProduto(rs.getString("descricao_produto"));
               p.setImagemProduto(rs.getString("imagem_produto"));
               p.setFgAtivo(rs.getBoolean("fg_ativo_produto"));
               
               
               blc.setCodProduto(p);
               us.setCpf_cnpj(rs.getString("cpf_cnpj"));
               us.setDataNascimento(rs.getDate("data_nascimento"));
               us.setEmail(rs.getString("email"));
               us.setFgAtivo(rs.getBoolean("fg_ativo_usuario"));
               us.setNomeFantasiaNomeCompleto(rs.getString("nome_fantasia_nome_completo"));
               us.setTelefone(rs.getString("telefone"));
               us.setTelefoneFixo(rs.getString("telefone_fixo"));
               
               blc.setCpfCnpj(us);
               blc.setQuantidade(rs.getInt("quantidade"));
               blc.setFgAtivo(rs.getBoolean("fg_ativo_lanche"));
               System.out.println(blc);
               llc.add(blc);
          
           }
        /*  int cont =0;
          while(cont<llc.size()){
              System.out.println(llc.get(cont).getCodProduto().getNomeProduto());
              cont++;
          }
         
      */
         stmt.close();
        }catch(SQLException e ){
            e.printStackTrace();
        }
       return llc;
        
    }
    
    public Long ultimoLanche(String usuario){
        String sql="SELECT MAX(cod_lanche) as 'cod_lanche'from tb_lanches where cpf_cnpj ='"+usuario+"'";
          ResultSet rs;
          Long ret =null;
        try{
          
            PreparedStatement stmt =c.prepareStatement(sql);
            rs=stmt.executeQuery();
            
        while(rs.next()){
            ret=rs.getLong("cod_lanche");
        }    
        stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ret;
    }
 
    public void desativaLanche(Long codLanche, String cpf){
       String sql="UPDATE tb_lanches SET fg_ativo = false WHERE cod_lanche="+codLanche+" and cpf_cnpj='"+cpf+"'";
       try{
              PreparedStatement stmt = c.prepareStatement(sql);
              stmt.execute();
              stmt.close();
       }catch(SQLException e){
           e.printStackTrace();
       }
     
    }
    
    public String excluirLanche(String cpf, Long codLanche){
        String ret="";
        String sql="delete from tb_lanches where cod_lanche ="+codLanche+" and cpf_cnpj='"+cpf+"'";
        try{
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            ret="Excluido com sucesso";
        }catch(SQLException e){
            ret=String.valueOf(e);
        }
        return ret;
    }
   
    
}