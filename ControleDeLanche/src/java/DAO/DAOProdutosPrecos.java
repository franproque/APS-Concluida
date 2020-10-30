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
import model.ProdutosPrecos;
import org.json.simple.JSONObject;

/**
 *
 * @author makefake
 */
public class DAOProdutosPrecos {
    private Connection c;
    public DAOProdutosPrecos(String usuario, String pass){
        this.c = new Conecta().conecta(usuario, pass);
    }
    public Long ultimoprecoCadastrado(){
        Long ret =null;
        String sql = "select max(cod_produtos_precos) as 'ultimo_numero' from tb_produtos_precos";
        try{
            ResultSet rs;
            
            PreparedStatement stmt = c.prepareStatement(sql);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                ret =rs.getLong("ultimo_numero");
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ret;
    }
    
    public List<ProdutosPrecos> listarTudo(){
        List<ProdutosPrecos> lp =new ArrayList<ProdutosPrecos>();
       
        String sql="SELECT p.cod_produto,p.nome_produto,p.descricao_produto,p.fg_ativo as 'fg_ativo_produto',p.imagem_produto,pp.cod_produtos_precos,pp.fg_ativo,pp.preco_produto,pp.peso,pp.un_medida FROM tb_produtos as p INNER JOIN tb_produtos_precos as pp on p.cod_produto = pp.cod_produto where pp.fg_ativo = true and p.fg_ativo= true;";
        try{
             ResultSet rs;
           PreparedStatement stmt = c.prepareStatement(sql);
           rs=stmt.executeQuery();
           
           while(rs.next()){
               ProdutosPrecos pp = new ProdutosPrecos();
               Produto p = new Produto();
               pp.setCodProdutosPrecos(rs.getLong("cod_produtos_precos"));
               pp.setFgAtivo(rs.getBoolean("fg_ativo_produto"));
               pp.setPeso(rs.getDouble("peso"));
               pp.setUnMedida(rs.getString("un_medida"));
               pp.setPrecoProduto(rs.getDouble("preco_produto"));
               
               p.setCodProduto(rs.getLong("cod_produto"));
               p.setDescricaoProduto(rs.getString("descricao_produto"));
               p.setFgAtivo(rs.getBoolean("fg_ativo_produto"));
               p.setImagemProduto(rs.getString("imagem_produto"));
               p.setNomeProduto(rs.getString("nome_produto"));
               
               pp.setCodProduto(p);
               
               lp.add(pp);
           }
           stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return lp;
    }
    
    
    public ProdutosPrecos listarPorFiltro(Long codProduto){
        ProdutosPrecos pp = new ProdutosPrecos();
       
        String sql="SELECT p.cod_produto,p.nome_produto,p.descricao_produto,p.fg_ativo as 'fg_ativo_produto',p.imagem_produto,pp.cod_produtos_precos,pp.fg_ativo,pp.preco_produto,pp.peso,pp.un_medida FROM tb_produtos as p INNER JOIN tb_produtos_precos as pp on p.cod_produto = pp.cod_produto where pp.fg_ativo = true and p.fg_ativo= true and p.cod_produto ="+codProduto;
        try{
             ResultSet rs;
           PreparedStatement stmt = c.prepareStatement(sql);
           rs=stmt.executeQuery();
           
           while(rs.next()){
              
               Produto p = new Produto();
               pp.setCodProdutosPrecos(rs.getLong("cod_produtos_precos"));
               pp.setFgAtivo(rs.getBoolean("fg_ativo_produto"));
               pp.setPeso(rs.getDouble("peso"));
               pp.setUnMedida(rs.getString("un_medida"));
               pp.setPrecoProduto(rs.getDouble("preco_produto"));
               
               p.setCodProduto(rs.getLong("cod_produto"));
               p.setDescricaoProduto(rs.getString("descricao_produto"));
               p.setFgAtivo(rs.getBoolean("fg_ativo_produto"));
               p.setImagemProduto(rs.getString("imagem_produto"));
               p.setNomeProduto(rs.getString("nome_produto"));
               
               pp.setCodProduto(p);
               
              
           }
           stmt.close();
           
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return pp;
    }
    

    
    public String inserirPreco(ProdutosPrecos pp){
        System.out.println(pp.getCodProduto().getCodProduto());
         String sqlSelect ="SELECT cod_produto FROM tb_produtos_precos where cod_produto ="+pp.getCodProduto().getCodProduto();
        String ret ="";
      
        try{
            ResultSet rs;
            PreparedStatement stmt =c.prepareStatement(sqlSelect);
            Long l = null;
            rs = stmt.executeQuery();
            
            if(rs.next()){
   //Se passar ele vai estar na posicao 1 , já pronto para usar os getters
     do{
         ret=String.valueOf(rs.getLong("cod_produto"));
          String sql="UPDATE tb_produtos_precos set preco_produto = ?, peso=? ,un_medida =? where cod_produto = ?";
            PreparedStatement stmt2 = c.prepareStatement(sql);
            
            
                stmt2.setDouble(1, pp.getPrecoProduto());
                stmt2.setDouble(2, pp.getPeso());
                stmt2.setString(3, pp.getUnMedida());
                stmt2.setLong(4,pp.getCodProduto().getCodProduto());
            
               
            
            stmt2.execute();
            stmt2.close();
              ret ="atualizado com sucesso";
         
         
                     }while(rs.next());
        }else{
      
                PreparedStatement stmt3 = c.prepareStatement("INSERT INTO tb_produtos_precos(cod_produtos_precos,cod_produto,preco_produto,fg_ativo,peso,un_medida) VALUES(?,?,?,?,?,?)");
               
         stmt3.setLong(1,ultimoprecoCadastrado()+1);
                stmt3.setLong(2,pp.getCodProduto().getCodProduto());
                stmt3.setDouble(3,pp.getPrecoProduto());
                stmt3.setBoolean(4, true);
                stmt3.setDouble(5, pp.getPeso());
                stmt3.setString(6, pp.getUnMedida());
                stmt3.execute();
                stmt3.close();
                   ret ="Inserido com sucesso";  
}
            
            
             
                   System.out.println("Tudo zerado");
                    
            
        }catch(SQLException e){
            
   ret = String.valueOf(e);
        }
  return ret;  
}

}
