/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Controlador.*;
import javax.swing.JFrame;

/**
 *
 * @author Juan Esteban Muñoz, Mateo Pacheco, Luis Fernando Suarez, Aldo Marquez
 */
public class main extends JFrame {

    public main() {
       initUI();
    }
    private void initUI() {
        add(new controler());
        setSize(1000, 500);
        setTitle("AngryBirds");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
        main ex = new main();
        ex.setVisible(true);
       
    }
}
