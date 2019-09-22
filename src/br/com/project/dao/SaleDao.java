/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Client;
import br.com.project.model.Sale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author whoami
 */
public class SaleDao {
    
    private final Connection conn;

    public SaleDao() {

        this.conn = new ConnectionFactory().getConnection();
    }
    
    public void registerSale(Sale obj){
        
        String sql = "insert into tb_vendas "
                + "(cliente_id, data_venda, total_venda, observacoes)"
                + "values(?, ?, ?, ?)";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, obj.getClient().getId());
            st.setString(2, obj.getDataVenda());
            st.setDouble(3, obj.getTotalVendas());
            st.setString(4, obj.getObservacao());
            
            st.execute();
            st.close();
            
            
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
        }
    }
    
    public int returnLastSale(){
        
        int idVenda = 0;
        String sql = "select max(id)id from tb_vendas;";
        
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                Sale sale = new Sale();
                
                sale.setId(rs.getInt("id"));
                
                idVenda = sale.getId();
            }
            
            return idVenda;
        }
        catch(SQLException error){
            throw new RuntimeException(error);
        }
    }
    
    //filtrar datas 
    
    public List<Sale> toListProductByDate(LocalDate dataInicio, LocalDate dataFinal){
        
        List<Sale> list = new ArrayList<>();
        String sql = "select v.id, date_format(v.data_venda, '%d/%m/%Y') "
                + "as data_formatada, c.nome, v.total_venda, "
                + "v.observacoes from tb_vendas as v inner join tb_clientes as "
                + "c on(v.cliente_id = c.id) where v.data_venda between ? and ?";
        
        try{
           
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setString(1, dataInicio.toString());
            st.setString(2, dataFinal.toString());
            
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Sale obj = new Sale();
                Client client = new Client();
                
                obj.setId(rs.getInt("v.id"));
                obj.setDataVenda(rs.getString("data_formatada"));
                client.setNome(rs.getString("nome"));
                obj.setTotalVendas(rs.getDouble("v.total_venda"));
                obj.setObservacao(rs.getString("v.observacoes"));
                
                obj.setClient(client);
                
                list.add(obj);
            }
            
            return list;
            
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Erro: " + error);
            return null;
        }
    }
    
    public double returnTotalSaleByDate(LocalDate dataVenda){
        
        List<Sale> list = new ArrayList<>();
        double totalVenda = 0;
        String sql = "select sum(total_venda) as total from tb_vendas "
                + "where data_venda = ?";
        
        try{
           
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setString(1, dataVenda.toString());
            
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
               totalVenda = rs.getDouble("total");
            }
            
            return totalVenda;
            
        }
        catch(SQLException error){
            throw new RuntimeException(error);
            
        }
    }
}
