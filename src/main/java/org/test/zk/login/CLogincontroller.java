package org.test.zk.login;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;

import java.sql.*;

public class CLogincontroller extends SelectorComposer<Component> {

    private static final long serialVersionUID = -4413893792907606285L;
    
    
    
    @Wire
    Button conectar;
    @Wire
    Button desconectar;
    @Wire
    Button revisar;
    protected Connection con = null;
    public void registro () {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Registro exitoso");

        } catch (Exception e) {

            System.out.println(e.toString());

        } 
        
    }
    public void conexion () {
        
      //...

      try {

          con = DriverManager.getConnection(
                  "jdbc:mysql://127.0.0.1:3306/test","root", "123885");

          // Otros y operaciones sobre la base de datos...

      } catch (SQLException ex) {

          // Mantener el control sobre el tipo de error
          System.out.println("SQLException: " + ex.getMessage());

      }
        
    }
    
    @Listen ( "onClick=#conectar" )
    public void onClickconectar (Event event){
    conexion();
    conectar.setLabel( "conectado" );
    }
    @Listen ( "onClick=#desconectar" )
    public void onClickdesconectar (Event event){
    try {
        con.close();
    }
    catch ( SQLException e ) {
        e.printStackTrace();
    }
    desconectar.setLabel( "desconectado" );
    }
    @Listen ( "onClick=#revisar" )
    public void onClickrevisar (Event event){
    registro();
    revisar.setLabel( "revisado" );
    
    }
}
