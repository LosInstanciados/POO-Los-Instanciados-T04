/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.tarea4;
import javax.swing.*;
import java.awt.event.*;
/*import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


/**
 *
 * @author Mariana Villegas
 */
public class Ventana extends JFrame implements ActionListener  {
    JMenuItem cmdAltaCliente, cmdRegistroCompras, cmdPagos, cmdConsultaSaldo;
    JMenu menuClientes, menuMovimientos;
    JMenuBar barMenu;
    /*JLabel lblcodigo;
    JTextField txtcodigo;
    JButton btnAceptar;
    */
    
    public Ventana () {

     //Establecemos el titulo de la ventana
    super ("Monedero Electronico");
    //Establecemos la ubicacion en la pantalla, y las dimensiones de la ventana
            setBounds(20,10,1340, 700);
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


    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
}
