/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.model;

import java.awt.Component;
import java.awt.TextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author whoami
 */
public class Utils {
    
    //Limpar campos
    public void Clear(JPanel container) {
        Component components[] = container.getComponents();
        for(Component component: components){
           if(component instanceof JTextField){
               ((JTextField)component).setText(null);
           }
           if(component instanceof TextField){
               ((TextField)component).setText(null);
           }
        }
    }
}
