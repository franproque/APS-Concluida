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
import model.Pedido;
import model.Produto;

import model.Usuario;
import org.json.simple.JSONObject;

/**
 *
 * @author makefake
 */
public class DAOPedidos {
    private Connection c;
    public DAOPedidos(String usuario, String pass){
        this.c = new Conecta().conecta(usuario, pass);
    }
    
    public Long trazUltimoPedido(){
        String sql = "SELECT MAX(cod_pedido) as 'codPedido' from tb_pedidos;";
        Long ret = null;
        try{
            ResultSet rs;
            PreparedStatement stmt = c.prepareStatement(sql);
            rs =stmt.executeQuery();
            
            while(rs.next()){
                ret = rs.getLong("codPedido");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ret;
    }
    public void inserirPedido(Pedido ped){
        String sql="INSERT INTO tb_pedidos(cod_pedido, cod_lanche,preco_total,desconto,fg_ativo,data_entrada,cpf_cnpj) "+
                "VALUES(?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setLong(1,ped.getCodPedido());
            stmt.setLong(2, ped.getCodLanche().getCodLanche());
            stmt.setDouble(3, ped.getPrecoTotal());
            stmt.setDouble(4, ped.getDesconto());
            stmt.setBoolean(5, true);
            stmt.setDate(6, ped.getDataEntrada());
            stmt.setString(7, ped.getCpf_cnpj().getCpf_cnpj());
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public List<Pedido> listarPedidos(){
       String sql="SELECT us.cpf_cnpj, us.email,us.nome_fantasia_nome_completo,us.telefone,us.data_nascimento, us.fg_ativo as 'fg_usuario', lc.cod_lanche,lc.cod_produto,lc.quantidade, lc.fg_ativo as 'fg_lanche', pd.nome_produto,pd.descricao_produto,pd.imagem_produto,pd.fg_ativo as 'fg_produto', ps.cod_pedido, ps.preco_total,ps.desconto,ps.fg_ativo as 'fg_pedidos',ps.data_entrada from tb_pedidos as ps inner join tb_lanches as lc on ps.cod_lanche = lc.cod_lanche inner join tb_produtos as pd on lc.cod_produto = pd.cod_produto INNER JOIN tb_usuario as us ON lc.cpf_cnpj = us.cpf_cnpj where ps.fg_ativo =true";

        List<Pedido> pd = new ArrayList<Pedido>();
        
    try{
        ResultSet rs;
        PreparedStatement stmt = c.prepareStatement(sql);
        rs = stmt.executeQuery();
        while(rs.next()){
            Usuario us = new Usuario();
            us.setCpf_cnpj(rs.getString("cpf_cnpj"));
            us.setEmail(rs.getString("email"));
            us.setNomeFantasiaNomeCompleto(rs.getString("nome_fantasia_nome_completo"));
            us.setTelefone(rs.getString("telefone"));
            us.setDataNascimento(rs.getDate("data_nascimento"));
            us.setFgAtivo(rs.getBoolean("fg_usuario"));
            us.setTelefoneFixo("");
            
            Produto produto = new Produto();
            produto.setCodProduto(rs.getLong("cod_produto"));
            produto.setDescricaoProduto(rs.getString("descricao_produto"));
            produto.setFgAtivo(rs.getBoolean("fg_produto"));
            produto.setImagemProduto(rs.getString("imagem_produto"));
            produto.setNomeProduto(rs.getString("nome_produto"));
            
            Lanche lc = new Lanche();
            
            lc.setCodLanche(rs.getLong("cod_lanche"));
            lc.setCodProduto(produto);
            lc.setCpfCnpj(us);
            lc.setFgAtivo(rs.getBoolean("fg_lanche"));
            lc.setQuantidade(rs.getLong("quantidade"));
            
            Pedido ps = new Pedido();
            
            ps.setCodPedido(rs.getLong("cod_pedido"));
            ps.setCodLanche(lc);
            ps.setDataEntrada(rs.getDate("data_entrada"));
            ps.setDesconto(rs.getDouble("desconto"));
            ps.setFgAtivo(rs.getBoolean("fg_pedidos"));
            ps.setPrecoTotal(rs.getDouble("preco_total"));
            ps.setCpf_cnpj(us);
            pd.add(ps);
            
            
        }
        
        stmt.close();
        c.close();
        
    }catch(SQLException e){
        e.printStackTrace();
    }
    return pd;
    }
    
    public List<Long> trasNumerosPedidos(){
        List<Long> ret = new ArrayList<Long>();
        String sql = "select cod_pedido from tb_pedidos order by cod_pedido asc";
        try{
            ResultSet rs;
            PreparedStatement stmt = c.prepareStatement(sql);
            rs =stmt.executeQuery();
            while(rs.next()){
                ret.add(rs.getLong("cod_pedido"));
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ret;
    }
    public JSONObject valorTotalPedido(Long codP){
        JSONObject js = new JSONObject();
        String sql="SELECT sum(ps.preco_total) as 'valor_total',ps.cod_pedido,us.nome_fantasia_nome_completo,ps.data_saida,ps.data_entregue from tb_pedidos as ps,tb_usuario as us where ps.cpf_cnpj = us.cpf_cnpj and ps.cod_pedido ="+codP;
        
        try{
            ResultSet rs;
           PreparedStatement stmt = c.prepareStatement(sql);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                js.put("nome",rs.getString("nome_fantasia_nome_completo"));
                js.put("codPedido",rs.getLong("cod_pedido"));
                js.put("valorTotal",rs.getDouble("valor_total"));
                js.put("dataSaida", rs.getString("data_saida"));
                System.out.println(rs.getString("data_saida"));
                js.put("dataEntregue", rs.getString("data_entregue"));
            }
           
               
           
             //   System.out.println(ret.getNome());
               // stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
     return js;
    }
    
    
    public void dataSaidaEntregue(int tipo, Long codPedido){
        String sql="";
        try{
            if(tipo==1){
                sql="UPDATE tb_pedidos SET data_saida = now() where cod_pedido ="+codPedido;
            }else{
                sql="UPDATE tb_pedidos SET data_entregue = now() where cod_pedido ="+codPedido;
            }
            
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
