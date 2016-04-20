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
 * @author Paco
 */
public class registroDeCompras extends JFrame implements ActionListener {

    private final JButton btnAceptar, btnCancelar;
    private final JTextField txtTicket, txtID, txtCantidad;
    //Declaramos las variables que serviran para conectar con la base de datos
    DefaultTableModel model;
    Connection conn;
    Statement sent;

    public registroDeCompras() {
        super("Registra tu compra");
        
        conn = Mysql.getConnection();
        setBounds(200,200,500,500);
        setLocation(10,10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Paso 1. Creamos botonones
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        //Paso 2. Creamos etiquetas
        JLabel lblTicket = new JLabel("no. de Ticket: ");
        JLabel lblID = new JLabel("ID de Tarjeta: ");
        JLabel lblCantidad = new JLabel("Cantidad: ");
        //Paso 3 creamos campos de texto
        txtTicket = new JTextField(20);
        txtID = new JTextField(20);
        txtCantidad = new JTextField(20);
        //Paso 4. Le damos dimensiones y locacion a las etiquetas, campos de texto y botones
        lblTicket.setBounds(10, 65, 120, 30);
        lblID.setBounds(10, 30, 120, 30);
        lblCantidad.setBounds(10, 100, 150, 30);
        txtTicket.setBounds(200, 65, 120, 30);
        txtID.setBounds(200, 30, 120, 30);
        txtCantidad.setBounds(200, 100, 120, 30);
        btnAceptar.setBounds(50, 180, 100, 50);
        btnCancelar.setBounds(200, 180, 100, 50);
        //Paso 5. Creamos un panel
        JPanel jp= new JPanel();
        jp.setBounds(0, 0, 800, 800);
        jp.setLayout(null);
        //Paso 6. Agregamos los componentes al panel
        this.setContentPane(jp);
        this.getContentPane().add(btnAceptar);
        this.getContentPane().add(btnCancelar);
        this.getContentPane().add(lblTicket);
        this.getContentPane().add(lblID);
        this.getContentPane().add(lblCantidad);
        this.getContentPane().add(txtTicket);
        this.getContentPane().add(txtID);
        this.getContentPane().add(txtCantidad);
        setVisible(true);
        
        //escucha de eventos
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
//guardamos el query en una variable tipo string
                String sql = "insert into registro_de_compras(Ticket,Cantidad, id_tarjeta)"
                        + "values(?,?,?)";
                        //Utilizamos PreparedStatement en vez de Statement para una query que ya ha sido compilada y hacemos la conexion, en este caso la variable es sql y quedara guardada en el statement ps
                PreparedStatement ps = conn.prepareCall(sql);
                  // el primer parametro que recibira ps.setString sera el numero de columna y el segundo sera la obtencion del valor que guardaron nuestras variables escritas en el codigo
                ps.setString(1, txtTicket.getText());
                ps.setString(2, txtCantidad.getText());
                ps.setString(3, txtID.getText());
                  //ejecutaremos el statment ps y lo guardaremos en un interger llamado n 
                  int n = ps.executeUpdate();
             
                // Verificamos que existan resultados y si existen n sera mayor a 0
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Datos Guardados");

                }
                JOptionPane.showMessageDialog(rootPane, txtID.getText() + "\n" + txtTicket.getText()
                        + "\n" + txtCantidad.getText() + "\nSe registró correctamente");

            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "Error" + el.getMessage());

            }
            
        }
        if (e.getSource()==btnCancelar) {
            salir();
        }
    }

}
