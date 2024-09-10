/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import  java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author felix
 */
public class Cliente extends persona{
    private String nit;
        int id;
        Conexion cn;
        
        
        public Cliente() { }          
        public Cliente(int id,String nit,String nombre, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombre, apellidos, direccion, telefono, fecha_nacimiento, nit);
        this.nit = nit;
        this.id = id;
            }

   public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
    
    public String getnit() {
        return nit;
    }

    public void setnit(String nit) {
        this.nit = nit;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query ;
            query = "Select id_clientes as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento from clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
           String encabezado [] = {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento"} ;
           tabla.setColumnIdentifiers(encabezado);
           
           String datos [] = new String[7];
           
           while(consulta.next()){
           datos [0] = consulta.getString("id");
           datos [1] = consulta.getString("nit");
           datos [2] = consulta.getString("nombres");
           datos [3] = consulta.getString("apellidos");
           datos [4] = consulta.getString("direccion");
           datos [5] = consulta.getString("telefono");
           datos [6] = consulta.getString("fecha_nacimiento");
           tabla.addRow(datos);
           }
           cn.cerrar_conexion();
           
        }catch(SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error:" + ex.getMessage());
        
        }
        return tabla;
    }
    
    public void Agregar(){
    try{
        PreparedStatement parametro;
        String query;
        query = "INSERT INTO clientes (nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES(?,?,?,?,?,?);";
        cn = new Conexion();
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, this.getnit());
        parametro.setString(2, this.getNombres());
        parametro.setString(3, this.getApellidos());
        parametro.setString(4, this.getDireccion());
        parametro.setString(5, this.getTelefono());
        parametro.setString(6, this.getFecha_nacimiento());
        
        int executar =  parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + "Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
    
    }catch(HeadlessException | SQLException ex){
        System.out.println("Error..."+ ex.getMessage());
    } 
    
    }
    
    public void Actualizar(){
     try{
        PreparedStatement parametro;
        String query;
        query = "update clientes set nit = ?,nombres = ?,apellidos= ?,direccion= ?,telefono= ?,fecha_nacimiento= ? "+
        "where id_clientes = ?";
        cn = new Conexion();
        cn.abrir_conexion();
        
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, this.getnit());
        parametro.setString(2, this.getNombres());
        parametro.setString(3, this.getApellidos());
        parametro.setString(4, this.getDireccion());
        parametro.setString(5, this.getTelefono());
        parametro.setString(6, this.getFecha_nacimiento());
        parametro.setInt(7, this.getid());
        
        int executar =  parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + "Registro Actualizado","Actualizar",JOptionPane.INFORMATION_MESSAGE);
    
    }catch(HeadlessException | SQLException ex){
        System.out.println("Error..."+ ex.getMessage());
    } 
    }
    
    public void Eliminar(){ 
     try{
        PreparedStatement parametro;
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "delete from clientes where id_clientes = ?";
        
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, this.getid());
        
        int executar =  parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + "Registro Eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
    
    }catch(HeadlessException | SQLException ex){
        System.out.println("Error..."+ ex.getMessage());
    } 
    
    }
     
}
