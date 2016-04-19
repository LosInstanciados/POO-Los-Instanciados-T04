/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.tarea4;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.*;
import java.sql.DriverManager;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author Mariana Villegas
 */
public class Ventana extends JFrame implements ActionListener  {
    JMenuItem cmdAltaCliente, cmdRegistroCompras, cmdPagos, cmdConsultaSaldo;
    JMenu menuClientes, menuMovimientos;
    JMenuBar barMenu;
    
     
    public Ventana () {

     //Establecemos el titulo de la ventana
    super ("Monedero Electronico");
    //Establecemos la ubicacion en la pantalla, y las dimensiones de la ventana
            setBounds(200,70,700, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
           
            /*setLayout(null);
              btnAceptar=new JButton("Aceptar");
              btnAceptar.setBounds(500,350,400,70);
              add(btnAceptar);
        
              btnAceptar.addActionListener(this); 
              txtcodigo=new JTextField();
              txtcodigo.setBounds(500,250,400,70);
              add(txtcodigo);
        
              lblcodigo=new JLabel("INSERTA CODIGO");
              lblcodigo.setBounds(500,150,400,70);
              add(lblcodigo);
           */
    
    //Paso 1. Crear los JMenuItems
    cmdAltaCliente = new JMenuItem("Alta de Clientes");
    cmdRegistroCompras = new JMenuItem("Registro de Compras");
    cmdPagos = new JMenuItem("Pagos");
    cmdConsultaSaldo = new JMenuItem("Consulta de Saldo");
    //Paso 2. Creamos los JMenus
    menuClientes = new JMenu("Clientes");
    menuMovimientos = new JMenu("Movimientos");
    //Paso 3. Creamos la barra JMenuBar
    barMenu = new JMenuBar();
   
    //Paso 4. Agregar los items a los menus
    menuClientes.add(cmdAltaCliente);
    
    menuMovimientos.add(cmdRegistroCompras);
    menuMovimientos.add(cmdPagos);
    menuMovimientos.add(cmdConsultaSaldo);
    //Paso 5. Agregar los menus a la barra
    barMenu.add(menuClientes);
    barMenu.add(menuMovimientos);
    
 
        setJMenuBar(barMenu);
       
        
        setVisible(true);
        
        //Paso 6. Que los comandos ESCUCHEN
         cmdAltaCliente.addActionListener(this);
         cmdRegistroCompras.addActionListener(this);
         cmdPagos.addActionListener(this);
         cmdConsultaSaldo.addActionListener(this);
      
    }

     
    /*public void insert(){   
    try {
    // connection string
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/Project?user=root&password=root");
        Statement st = con.createStatement();

        st.executeUpdate("insert into Register VALUES('"
                + txtNombre.getText() + "','" + txtEmail.getText() + "','"
                + txtTelefono.getText() + "','" + txtIdtarjeta.getText());
        JOptionPane.showConfirmDialog(null, "Your Data Has been Inserted",
                "Result", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        st.close();
        con.close();

    }

    catch (Exception e1)

    {
        System.out.println("Exception:" + e1);
    }
        
        
       }
    */
    
       
    @Override
    @SuppressWarnings("override")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cmdAltaCliente){
          altaDeClientes cliente = new altaDeClientes();
                     
        
        }else if(e.getSource()==cmdRegistroCompras){
                   
            registroDeCompras registro = new registroDeCompras();
            
        }else if(e.getSource()==cmdPagos){
                    
            pagos pagos = new pagos();
         }else if(e.getSource()==cmdConsultaSaldo){
                  consultaDeSaldo saldo = new consultaDeSaldo();
                             
         }     
                  }

      }
    
    
    
    
    
    
    

