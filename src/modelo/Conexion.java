/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author felix
 */
public class Conexion {

    public static com.sun.jdi.connect.spi.Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private final String puerto = "3306";
    public Connection conexionBD;
    public final String bd = "db_empresa2";
    public final String urlConexion = String.format("jdbc:mysql://127.0.0.1:3306/%s",bd);
    public final String usuario = "root";
    public final String contraseña = "dhuwjv";
    public final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion(){
    try{
        Class.forName(jdbc);
        conexionBD = DriverManager.getConnection(urlConexion,usuario,contraseña);
    /*JOptionPane.showMessageDialog(null, "Conexion Exitosa...","Exito",JOptionPane.INFORMATION_MESSAGE);*/
    }catch(HeadlessException | ClassNotFoundException | SQLException ex){
        System.out.println("Error..." + ex.getMessage());
    }    
    }
    public void cerrar_conexion(){
    try{
     conexionBD.close();
    }catch(SQLException ex){
        System.out.println("Error..." + ex.getMessage());
    }
    }
    
}
