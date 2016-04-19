/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.tarea4;

import db.Mysql;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class consultaDeSaldo extends JFrame implements ActionListener {

    private final JButton btnAceptar, btnCancelar;
    private final JTextField txtID;
    DefaultTableModel model;
    Connection conn;
    Statement sent;
    public StringBuffer sb;
    
    FlowLayout ventana1 = new FlowLayout();

    public consultaDeSaldo() {
        super("Registra tu compra");

        conn = Mysql.getConnection();
        setBounds(200, 200, 500, 500);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        JLabel lblID = new JLabel("ID de Tarjeta: ");

        txtID = new JTextField(20);

        lblID.setBounds(10, 30, 120, 30);

        txtID.setBounds(200, 30, 120, 30);

        btnAceptar.setBounds(50, 180, 100, 50);
        btnCancelar.setBounds(200, 180, 100, 50);
        JPanel jp = new JPanel();
        jp.setBounds(0, 0, 800, 800);
        jp.setLayout(null);
        this.setContentPane(jp);
        this.getContentPane().add(btnAceptar);
        this.getContentPane().add(btnCancelar);

        this.getContentPane().add(lblID);

        this.getContentPane().add(txtID);
        setVisible(true);
        this.repaint();
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        
        
        
        
    }
    public StringBuffer setStatement(Connection conn) throws SQLException{
                CallableStatement cs = null;
            
            
            try {
              cs = conn.prepareCall("{CALL obtensaldo(?)}");
              cs.registerOutParameter(1, Types.CHAR);
              cs.registerOutParameter(2, Types.CHAR);
              cs.execute();
              sb.append("Nombre: "); 
              sb.append("'"+cs.getString(1)+"' , ");
              sb.append("Saldo: ");
              sb.append("'"+cs.getString(2)+"' , ");
            } catch (Exception e) {
              System.out.println(e.toString());
            } finally {
              cs.close();
            }
                
                return sb;
        }

    private void salir() {
        System.exit(0);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            storedProcedure pro = new storedProcedure();
            StringBuffer sb = new StringBuffer();
           
           JOptionPane.showMessageDialog(null,pro.toString());
           
        }

        if (e.getSource() == btnCancelar) {
            salir();
        }
    }

}
