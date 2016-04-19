/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.tarea4;
import db.Mysql;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author casa
 */


public class pagos extends JFrame implements ActionListener {
    private final JButton btnAceptar, btnCancelar;
    private final JTextField txtID, txtCantidad;

    DefaultTableModel model;
    Connection conn;
    Statement sent;
    
    
    public pagos() {
        super("Registra tu pago");
         conn = Mysql.getConnection();
        setBounds(200,200,500,500);
        setLocation(10,10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        JLabel lblID = new JLabel("ID de Tarjeta: ");
        JLabel lblCantidad = new JLabel("Cantidad: ");
        txtID = new JTextField(20);
        txtCantidad = new JTextField(20);
        lblID.setBounds(10, 30, 120, 30);
        lblCantidad.setBounds(10, 100, 150, 30);
        txtID.setBounds(200, 30, 120, 30);
        txtCantidad.setBounds(200, 100, 120, 30);
        btnAceptar.setBounds(50, 180, 100, 50);
        btnCancelar.setBounds(200, 180, 100, 50);
        JPanel jp= new JPanel();
        jp.setBounds(0, 0, 800, 800);
        jp.setLayout(null);
        this.setContentPane(jp);
        this.getContentPane().add(btnAceptar);
        this.getContentPane().add(btnCancelar);
        this.getContentPane().add(lblID);
        this.getContentPane().add(lblCantidad);
        this.getContentPane().add(txtID);
        this.getContentPane().add(txtCantidad);
        setVisible(true);
        this.repaint();
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
    }
    private void salir (){
    System.exit(0);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnAceptar) {
            
            try {

                String sql = "insert into pagos(id_tarjeta, Cantidad)"
                        + "values(?,?)";
                PreparedStatement ps = conn.prepareCall(sql);
                ps.setString(1, txtID.getText());
                ps.setString(2, txtCantidad.getText());
                
                int n = ps.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Datos Guardados");

                }
                JOptionPane.showMessageDialog(rootPane, txtID.getText() 
                        + "\n" + txtCantidad.getText() + "\nSe registró correctamente");

            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "Error" + el.getMessage());

            }
            try {
                conn = Mysql.getConnection();
                String[] titulos = {"id_tarjeta", "Cantidad"};
                String sql = "select * from pagos";
                model = new DefaultTableModel(null, titulos);
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);

                String[] fila = new String[4];

                while (rs.next()) {
                    fila[0] = rs.getString("id_tarjeta");
                    fila[1] = rs.getString("Cantidad");
                    

                    model.addRow(fila);
                }

               // table1.setModel(model);
            } catch (Exception el) {
                el.printStackTrace();
            }
            
            
        }
        if (e.getSource()==btnCancelar) {
            salir();
        }
    }
}
