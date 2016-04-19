/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.tarea4;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import db.Mysql;
import static db.Mysql.getConnection;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mariana Villegas
 */
public class altaDeClientes extends JFrame implements ActionListener {

    protected JButton btnAceptar, btnCancelar, btnLimpiar;
    protected JTextField txtNombre, txtEmail, txtTelefono, txtIdtarjeta;
    protected final JFrame v = new JFrame("ALTA DE CLIENTES");

    //protected final JTable table1 = new JTable();
    DefaultTableModel model;
    Connection conn;
    Statement sent;

    public altaDeClientes() {
        v.setSize(500, 250);  //Establecemos las dimensiones del formulario (ancho x alto)
        v.setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
        //initComponents();
        conn = Mysql.getConnection();

    
        //Paso 1. Vamos a crear etiquetas
        JLabel lblNombre = new JLabel("Nombre: ");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblTelefono = new JLabel("Telefono:");
        JLabel lblIdtarjeta = new JLabel("ID Tarjeta:");

        //Paso 2. Vamos a crear campos de texto
        txtNombre = new JTextField(20);
        txtEmail = new JTextField(20);
        txtTelefono = new JTextField(10);
        txtIdtarjeta = new JTextField(19);

        //Paso 3. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        btnLimpiar = new JButton("Limpiar");
      

        //Paso 4. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 5. Ubicamos los elementos en el contenedor
        lblNombre.setBounds(20, 30, 70, 10); //x,y, ancho y alto
        lblNombre.setFont(new Font("Arial", 1, 14));
        lblNombre.setForeground(new Color(25, 85, 255));
        txtNombre.setBounds(95, 30, 300, 25);
        txtNombre.setFont(new Font("Arial", 1, 14));

        lblEmail.setBounds(20, 65, 70, 10);
        lblEmail.setFont(new Font("Arial", 1, 14));
        txtEmail.setBounds(95, 65, 300, 25);
        txtEmail.setFont(new Font("Arial", 1, 14));

        lblTelefono.setBounds(20, 100, 70, 10);
        lblTelefono.setFont(new Font("Arial", 1, 14));
        txtTelefono.setBounds(95, 100, 300, 25);
        txtTelefono.setFont(new Font("Arial", 1, 14));

        lblIdtarjeta.setBounds(20, 135, 70, 10);
        lblIdtarjeta.setFont(new Font("Arial", 1, 14));
        txtIdtarjeta.setBounds(95, 135, 300, 25);
        txtIdtarjeta.setFont(new Font("Arial", 1, 14));

        btnAceptar.setBounds(90, 175, 80, 25);
        btnCancelar.setBounds(180, 175, 100, 25);
        btnLimpiar.setBounds(290, 175, 120, 25);
        

        //Paso 6. Agremos los componentes al contenedor
        pnlContenido.add(lblNombre);
        pnlContenido.add(txtNombre);
        pnlContenido.add(lblEmail);
        pnlContenido.add(txtEmail);
        pnlContenido.add(lblTelefono);
        pnlContenido.add(txtTelefono);
        pnlContenido.add(lblIdtarjeta);
        pnlContenido.add(txtIdtarjeta);
        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);
        pnlContenido.add(btnLimpiar);
        

        //Paso 7. Asociamos el contenedor a la ventana
        v.setContentPane(pnlContenido);

        //Paso 8. Hacemos visible la ventana
        v.setVisible(true);

        //Paso 9. Escucha de eventos.
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnLimpiar.addActionListener(this);
       

    }

    public void salir() {
        System.exit(0);
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {

            try {
                
                String sql = "insert into clientes(nombre, email, telefono, id_tarjeta)"
                        + "values(?,?,?,?)";
                PreparedStatement ps = conn.prepareCall(sql);
                ps.setString(1, txtNombre.getText());
                ps.setString(2, txtEmail.getText());
                ps.setString(3, txtTelefono.getText());
                ps.setString(4, txtIdtarjeta.getText());
                int n = ps.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Datos Guardados");

                }
                JOptionPane.showMessageDialog(rootPane, txtNombre.getText() + "\n" + txtEmail.getText()
                        + "\n" + txtTelefono.getText() + "\n" + txtIdtarjeta.getText() + "\nSe registró correctamente");

            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "Error" + el.getMessage());

            }
            try {
                conn = Mysql.getConnection();
                String[] titulos = {"Nombre", "Email", "Telefono", "id_tarjeta"};
                String sql = "select * from clientes";
                model = new DefaultTableModel(null, titulos);
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);

                String[] fila = new String[4];

                while (rs.next()) {
                    fila[0] = rs.getString("Nombre");
                    fila[1] = rs.getString("Email");
                    fila[2] = rs.getString("Telefono");
                    fila[3] = rs.getString("id_tarjeta");

                    model.addRow(fila);
                }

                // table1.setModel(model);
            } catch (Exception el) {
                el.printStackTrace();
            }
            

        } else if (e.getSource() == btnCancelar) {
            salir();

        } else if (e.getSource() == btnLimpiar) {
            txtNombre.setText("");
            txtEmail.setText("");
            txtTelefono.setText("");
            txtIdtarjeta.setText("");

        } 

    }
}
