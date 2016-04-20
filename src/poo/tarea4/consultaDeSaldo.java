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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class consultaDeSaldo extends JFrame {

    private final JButton btnAceptar, btnCancelar;
    private final JTextField txtID, txtsaldo, txtnombre;

    private JLabel lbls, lbln;
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
        //Paso 1. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
//Paso 2. Vamos a crear etiquetas
        JLabel lblID = new JLabel("ID de Tarjeta: ");
        JLabel lbls = new JLabel("Saldo: ");
        JLabel lbln = new JLabel("Nombre: ");
        //Paso 3. Vamos a crear campos de texto
        txtID = new JTextField(20);
        txtsaldo = new JTextField(20);
        txtnombre = new JTextField(20);
Paso 3. Ubicamos los elementos en el contenedor
        lblID.setBounds(10, 30, 120, 30);
        lbls.setBounds(10, 60, 120, 30);
        lbln.setBounds(10, 90, 120, 30);

        txtID.setBounds(200, 30, 120, 30);
        txtsaldo.setBounds(200, 60, 120, 30);
        txtnombre.setBounds(200, 90, 120, 30);

        btnAceptar.setBounds(50, 180, 100, 50);
        btnCancelar.setBounds(200, 180, 100, 50);
        //Paso 4. Vamos a crear un contenedor
        JPanel jp = new JPanel();
        jp.setBounds(0, 0, 800, 800);
        jp.setLayout(null);
        
        this.setContentPane(jp);
        //Paso 6. Agremos los componentes al contenedor
        this.getContentPane().add(btnAceptar);
        this.getContentPane().add(btnCancelar);

        this.getContentPane().add(lblID);
        this.getContentPane().add(lbls);
        this.getContentPane().add(lbln)
        this.getContentPane().add(txtsaldo);
        this.getContentPane().add(txtID);
        this.getContentPane().add(txtnombre);
        setVisible(true);
        this.repaint();
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                salir();
            }
        });
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //verificamos que los campos no esten vacios
                if((txtID.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null, "Todos los campos deben de estar llenos");
                }else{
                try {
//utilizamos Statement porque apenas compilaremos un query
                    Statement comando = conn.createStatement();
                    //el query quedara guardado en un resultado establecido, este query se encarga de seleccionar las colunas cantidad y nombre de la base de datos y compara si el id es igual al id que se introdujo en el formulario
                    ResultSet registro = comando.executeQuery("select cantidad, Nombre from saldo where id_tarjeta=" + txtID.getText());
                   //si el resultado es verdadero en los campos de saldo y nombre del formulario apareceran los datos de la consulta que se hizo
                    if (registro.next() == true) {
                        txtsaldo.setText(registro.getString("cantidad"));
                        txtnombre.setText(registro.getString("Nombre"));

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
