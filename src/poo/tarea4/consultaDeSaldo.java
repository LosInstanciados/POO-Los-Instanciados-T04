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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class consultaDeSaldo extends JFrame {

    private final JButton btnAceptar, btnCancelar;
    private final JTextField txtID, txtsaldo;

    private JLabel lbls;
    DefaultTableModel modelo;
    Connection conn;
    Statement sent;
    public StringBuffer sb;

    public consultaDeSaldo() {
        super("Registra tu compra");

        conn = Mysql.getConnection();
        setBounds(200, 200, 500, 500);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        JLabel lblID = new JLabel("ID de Tarjeta: ");
        JLabel lbls = new JLabel("Saldo: ");
        txtID = new JTextField(20);
        txtsaldo = new JTextField(20);

        lblID.setBounds(10, 30, 120, 30);
        lbls.setBounds(10, 60, 120, 30);

        txtID.setBounds(200, 30, 120, 30);
        txtsaldo.setBounds(200, 60, 120, 30);

        btnAceptar.setBounds(50, 180, 100, 50);
        btnCancelar.setBounds(200, 180, 100, 50);
        JPanel jp = new JPanel();
        jp.setBounds(0, 0, 800, 800);
        jp.setLayout(null);
        this.setContentPane(jp);
        this.getContentPane().add(btnAceptar);
        this.getContentPane().add(btnCancelar);

        this.getContentPane().add(lblID);
        this.getContentPane().add(lbls);
        this.getContentPane().add(txtsaldo);
        this.getContentPane().add(txtID);
        setVisible(true);
        this.repaint();
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                salir();
            }
        });
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if((txtID.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null, "Todos los campos deben de estar llenos");
                }else{
                try {

                    Statement comando = conn.createStatement();
                    ResultSet registro = comando.executeQuery("select cantidad from saldo where id_tarjeta=" + txtID.getText());
                    if (registro.next() == true) {
                        txtsaldo.setText(registro.getString("cantidad"));

                    } else {
                        JOptionPane.showMessageDialog(null, "ID incorrecta");
                    }

                } catch (SQLException ex) {
                    setTitle(ex.toString());
                }
            }
            }
        });

    }

    private void salir() {
        System.exit(0);
    }
}
