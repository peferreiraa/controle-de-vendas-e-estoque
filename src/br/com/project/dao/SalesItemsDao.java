/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Client;
import br.com.project.model.Product;
import br.com.project.model.Sale;
import br.com.project.model.SalesItems;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author whoami
 */
public class SalesItemsDao {
    
    private final Connection conn;

    public SalesItemsDao() {

        this.conn = new ConnectionFactory().getConnection();
    }
    
    public void registerItem(SalesItems obj){
        
         String sql = "insert into tb_itensvendas "
                + "(venda_id, produto_id, qtd, subtotal)"
                + "values(?, ?, ?, ?)";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, obj.getVenda().getId());
            st.setInt(2, obj.getProduto().getId());
            st.setInt(3, obj.getQtd());
            st.setDouble(4, obj.getSubtotal());
            
            st.execute();
            st.close();
            
            
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    //Listar itens vendidos por id de vendedor
    public List<SalesItems> toListSalesItems(int vendaId){
        
        List<SalesItems> list = new ArrayList<>();
        String sql = "select i.id, p.descricao, i.qtd, p.preco,  "
                + "i.subtotal from tb_itensvendas as i "
                + "inner join tb_produtos as p on(i.produto_id = p.id) "
                + "where i.venda_id = ? ";
        
        try{
           
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setInt(1, vendaId);
            
            
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
               SalesItems item = new SalesItems();
               Product product = new Product();
               
               item.setId(rs.getInt("i.id"));
               product.setDescricao(rs.getString("p.descricao"));
               item.setQtd(rs.getInt("i.qtd"));
               product.setPreco(rs.getDouble("p.preco"));
               item.setSubtotal(rs.getDouble("i.subtotal"));
               
               
               
               
               
               item.setProduto(product);
               
               list.add(item);
               
            }
            
            return list;
            
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
            return null;
        }
    }
}
