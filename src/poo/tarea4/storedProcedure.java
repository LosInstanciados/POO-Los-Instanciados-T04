/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.tarea4;


import db.Mysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class storedProcedure {

       
    DefaultTableModel model;
    Connection conn;
    Statement sent;
    public StringBuffer sb;

    public storedProcedure(){
        conn = Mysql.getConnection();
    }
  /* public void pro (){
       try{
       String sql = "CREATE PROCEDURE obtensaldo(IN pidtarjeta CHAR(20)) "
               + "BEGIN "
               + "  SELECT Nombre, cantidad "
               + " FROM saldo WHERE id_tarjeta = pidtarjeta; "
               + "END";
       
       }
       
       
       
}*/
   
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

    @Override
    public String toString() {
        return "storedProcedure{" + "sb=" + sb + '}';
    }

    

    

    
   
   
}

    

    
      

        
    

