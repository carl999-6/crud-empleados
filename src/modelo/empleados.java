/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felix
 */

    public class empleados extends persona {
     Conexion cn;
    private String codigo;
    private int id,id_puestos;

    public empleados() {}
    public empleados(String codigo, int id, int id_puestos, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id = id;
        this.id_puestos = id_puestos;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_puestos() {
        return id_puestos;
    }

    public void setId_puestos(int id_puestos) {
        this.id_puestos = id_puestos;
    }
    
    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
       cn = new Conexion ();
       cn.abrir_conexion();
       String query;
       query = "SELECT e.id_empleados as id ,e.codigo ,e.nombres ,e.apellidos ,e.direccion ,e.telefono ,e.fecha_nacimiento ,concat(p.id_puestos,') ',p.puesto) as puestos FROM  empleados as e inner join puestos as p on e.id_puestos = p.id_puestos ;";
       ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
       String encabezado[] = 
       {" id"," Codigo"," Nombres"," Apellidos"," Direccion"," Telefono"," Nacimiento"," Puesto"};
       tabla.setColumnIdentifiers(encabezado);
       String datos[] = new String[8];
       while (consulta.next()){
           datos[0] = consulta.getString("id");
           datos[1] = consulta.getString("codigo");
           datos[2] = consulta.getString("nombres");
           datos[3] = consulta.getString("apellidos");
           datos[4] = consulta.getString("direccion");
           datos[5] = consulta.getString("telefono");
           datos[6] = consulta.getString("fecha_nacimiento");
           datos[7] = consulta.getString("puestos");
           tabla.addRow(datos);
       }
       cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage() );
    
    }
    
    return tabla;
    }
    
    public DefaultComboBoxModel leer_empleado(){
    DefaultComboBoxModel  combo = new DefaultComboBoxModel ();
    try{
       cn = new Conexion ();
       cn.abrir_conexion();
       String query;
       query = "SELECT id_empleados as id,empleados from empleados";
       ResultSet consulta =  cn.conexionBD.createStatement().executeQuery(query);
    }catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage() );
    }
    return combo;
    }
    
    public DefaultComboBoxModel id() {
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    try {
        cn = new Conexion();
        cn.abrir_conexion();
        String query = "SELECT id_puestos as id, puesto FROM puestos";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        combo.addElement("0) Elija Puesto"); // Elemento inicial
        while (consulta.next()) {            
            combo.addElement(consulta.getString("id") + ") " + consulta.getString("puesto"));
        }
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return combo;
}
    
    public DefaultComboBoxModel puesto(){
    DefaultComboBoxModel  combo = new DefaultComboBoxModel ();
    try{
       cn = new Conexion ();
       cn.abrir_conexion();
       String query;
       query = "SELECT id_Puestos as id,puestos from puesto";
       ResultSet consulta =  cn.conexionBD.createStatement().executeQuery(query);
       combo.addElement("0) Elija Puesto");
                  while (consulta.next())
                    {            
                      combo.addElement(consulta.getString("id")+") "+consulta.getString("puesto"));
                    }
              cn.cerrar_conexion();
              
       
    }catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage() );
    }
    return combo;
    }

    
    
    public void agregar(){
      try{

        // Definir la conexión y abrirla
        Conexion cn = new Conexion();
        cn.abrir_conexion();
        
        // Definir la consulta
        String query = "INSERT INTO empleados (codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, id_puestos) VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        // Preparar la declaración
        PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
        
        // Asignar los parámetros
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_nacimiento()); // Si es tipo DATE en la BD, asegúrate de formatearlo correctamente
        parametro.setInt(7, getId_puestos());
        
        // Ejecutar la actualización
        int executar = parametro.executeUpdate();
        System.out.println("Se insertó: " + executar + " registro(s).");
        
        // Cerrar la conexión
        cn.cerrar_conexion();
    } catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage());
    }


  }  
    public void actualizar (){
      try{
          PreparedStatement parametro;
          cn = new Conexion ();
          cn.abrir_conexion();
          String query;
          query = "update empleados set codigo=?,nombres=?,apellidos=?,direccion=?,telefono=?,fecha_nacimiento=?,id_puestos=? where id_empleados = ?;";
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setString(1, getCodigo());
          parametro.setString(2, getNombres());
          parametro.setString(3, getApellidos());
          parametro.setString(4, getDireccion());
          parametro.setString(5, getTelefono());
          parametro.setString(6, getFecha_nacimiento());
          parametro.setInt(7, getId_puestos());
          parametro.setInt(8, getId());
          int executar = parametro.executeUpdate();
          System.out.println(" Se Actualizo :" + Integer.toString(executar) + " Registro" );
          cn.cerrar_conexion();
      }catch(SQLException ex){
          System.out.println("Error:" + ex.getMessage());
      
      }
  }  
    public void borrar (){
      try{
          PreparedStatement parametro;
          cn = new Conexion ();
          cn.abrir_conexion();
          String query;
          query = "delete from empleados where id_empleados = ?;";
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setInt(1, getId());
          int executar = parametro.executeUpdate();
          System.out.println(" Se Elimino :" + Integer.toString(executar) + " Registro" );
          cn.cerrar_conexion();
      }catch(SQLException ex){
          System.out.println("Error:" + ex.getMessage());
      
      }
  }  
}
