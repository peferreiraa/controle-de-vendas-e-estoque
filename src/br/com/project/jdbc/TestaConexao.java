/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.jdbc;

import javax.swing.JOptionPane;



/**
 *
 * @author whoami
 */
public class TestaConexao {
    
    public static void main(String[] args) {
        try{
            new ConnectionFactory().getConnection();
            JOptionPane.showConfirmDialog(null, "Conectado!");
        }
        catch(Exception e){
            JOptionPane.showConfirmDialog(null, "Error: " + e);
        }
    }
}
