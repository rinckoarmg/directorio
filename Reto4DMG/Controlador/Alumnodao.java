/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Conexion;
//import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/* @author dianagamboa
 */
public class Alumnodao  {
    public static final String CREAR = "INSERT INTO Estudiantes(Nombres, Apellidos, Fecha, Correoi, Correop, Cel, Tel, Programa) VALUES(?,?,?,?,?,?,?,?)";
    public static final String MODIFICAR = "UPDATE Estudiantes SET Correop=?, Cel=?, Tel=?, Programa=? WHERE Correoi=?";
    public static final String ELIMINAR = "DELETE FROM Estudiantes WHERE Correoi=?";
    public static final String BUSCAR = "SELECT * FROM Estudiantes WHERE Correoi=?";
    public static final String MOSTRARTODO = "SELECT * FROM Estudiantes ORDER BY Nombres";
    public static final String BUSCARAP = "SELECT * FROM Estudiantes WHERE Apellidos=?";
    public static final String BUSCARPROG = "SELECT * FROM Estudiantes WHERE Programa=?";
    public static final String BUSCARFECHA = "SELECT * FROM Estudiantes WHERE Fecha=?";
    public static final String BUSCARCEL = "SELECT * FROM Estudiantes WHERE Cel=?";
    public static final String CONTEO = "SELECT count(Programa) FROM Estudiantes WHERE Programa=?";
    //public static final String URL = "jdbc:mysql://localhost:3306/bd_estudiantes";
    //public static final String USERNAME = "root";
    //public static final String PASSWORD = "";
    //lista de tipo alumno
    public List<Alumno> directorio;
    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion = new Conexion();
    
    //constructor
    public Alumnodao() {
        directorio = new ArrayList<>();
        //Archivo.crearArchivo(directorio);  
    }
    
    public void importar(){
        directorio = Archivo.leerArchivo();
        Connection conectar = null;
        
        for (Alumno alumno : directorio){
            try {

            conectar = conexion.getConection();
            ps = conectar.prepareStatement(CREAR);
            ps.setString(1, alumno.getNombres());
            ps.setString(2, alumno.getApellidos());
            ps.setString(3, alumno.getFecha());
            ps.setString(4, alumno.getEmailins());
            ps.setString(5, alumno.getEmailper());
            ps.setLong(6, alumno.getCel());
            ps.setLong(7, alumno.getTel());
            ps.setString(8, alumno.getPrograma());

            int rs = ps.executeUpdate();

            if (rs > 0) {
                System.out.println("Alumno agregado");
                //limpiar();
            } else {
                System.out.println("Error");
                //limpiar();
            }
            conectar.close();

            } catch (Exception e) {
            System.out.println(e);
            }
        }
    }
    
    public void crear(String nombre, String apellido, String fech, String maili, String mailp, long ce, long te, String prog) throws IOException {
        
        Connection con = null;

        try {

            con = conexion.getConection();
            ps = con.prepareStatement(CREAR);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, fech);
            ps.setString(4, maili);
            ps.setString(5, mailp);
            ps.setLong(6, ce);
            ps.setLong(7, te);
            ps.setString(8, prog);

            int rs = ps.executeUpdate();

            if (rs > 0) {
                System.out.println("Alumno agregado");
                //limpiar();
            } else {
                System.out.println("Error");
                //limpiar();
            }
            ps.close();
            con.close();
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //elimina el alumno que se le pasa como paraámetro
    public void eliminar(String email) throws IOException {
        Conexion conexion = new Conexion();
        Connection conectar = null;
        PreparedStatement ps;
        try {
            conectar = conexion.getConection();
            ps = conectar.prepareStatement(ELIMINAR);
            ps.setString(1, email);

            int rs = ps.executeUpdate();

            if (rs > 0) {
                System.out.println("Se eliminó el estudiante");
            } else {
                System.out.println("Error");
            }
            ps.close();
            conectar.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //obtiene toda la lista de directorio
    public List<Alumno> obtenerdirectorio() {
        directorio = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conectar = null;
        Alumno al = null;
        try {
            conectar = conexion.getConection();
            ps = conectar.prepareStatement(MOSTRARTODO);
            rs = ps.executeQuery();
            while (rs.next()){
                al = new Alumno();
                al.setNombres(rs.getString("Nombres"));
                al.setApellidos(rs.getString("Apellidos"));
                al.setFecha(rs.getString("Fecha"));
                al.setEmailins(rs.getString("Correoi"));
                al.setEmailper(rs.getString("Correop"));
                al.setCel(rs.getLong("Cel"));
                al.setTel(rs.getLong("Tel"));
                al.setPrograma(rs.getString("Programa"));
                directorio.add(al);
            }
            ps.close();
            conectar.close();
        } catch (Exception e) {
            System.out.println("Error de entrada y salida"+e.getMessage());
        }
        
        return directorio;
    }

    //obtiene un alumno de acuerdo al id pasado como parámetro
    public Alumno obtener(String email) {
        Conexion conexion = new Conexion();
        Connection conectar = null;
        Alumno al = null;
        try {
            conectar = conexion.getConection();
            ps = conectar.prepareStatement(BUSCAR);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()){
                al = new Alumno();
                al.setNombres(rs.getString("Nombres"));
                al.setApellidos(rs.getString("Apellidos"));
                al.setFecha(rs.getString("Fecha"));
                al.setEmailins(rs.getString("Correoi"));
                al.setEmailper(rs.getString("Correop"));
                al.setCel(rs.getLong("Cel"));
                al.setTel(rs.getLong("Tel"));
                al.setPrograma(rs.getString("Programa"));

            } else {
                System.out.println("No existe una persona con ese email");
            }
            ps.close();
            conectar.close();
        } catch (Exception e) {
            System.out.println("Error de entrada y salida"+e.getMessage());
        }
        return al;  
    }
        // actualiza el alumno que se le pasa como parámetro
    public void actualizar(String emailin, String emailpe, long ce, long te, String prog) throws IOException {
        Conexion conexion = new Conexion();
        Connection conectar = null;
        try {
            conectar = conexion.getConection();
            ps = conectar.prepareStatement(MODIFICAR);
            
            ps.setString(1, emailpe);
            ps.setLong(2, ce);
            ps.setLong(3, te);
            ps.setString(4, prog);
            ps.setString(5, emailin);

            int rs = ps.executeUpdate();

            if (rs > 0) {
                System.out.println("Se modificó el estudiante");
            } else {
                System.out.println("Error");
            }
            ps.close();
            conectar.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Alumno> listado(String x, int op){
        String sql = null;
        if (op == 1){
            sql = BUSCARAP;
        } else if (op == 2){
            sql = BUSCARPROG;
        } else if (op == 3){
            sql = BUSCARFECHA;
        } else {
            System.out.println("Opcion no válida");
        }
        directorio = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conectar = null;
        Alumno al = null;
        try {
            conectar = conexion.getConection();
            ps = conectar.prepareStatement(sql);
            ps.setString(1, x);
            rs = ps.executeQuery();
            while (rs.next()){
                al = new Alumno();
                al.setNombres(rs.getString("Nombres"));
                al.setApellidos(rs.getString("Apellidos"));
                al.setFecha(rs.getString("Fecha"));
                al.setEmailins(rs.getString("Correoi"));
                al.setEmailper(rs.getString("Correop"));
                al.setCel(rs.getLong("Cel"));
                al.setTel(rs.getLong("Tel"));
                al.setPrograma(rs.getString("Programa"));
                directorio.add(al);
            }
            ps.close();
            conectar.close();
        } catch (Exception e) {
            System.out.println("Error de entrada y salida"+e.getMessage());
        }
        return directorio;
    }

    public List<Alumno> buscarCel(long celu){
        directorio = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conectar = null;
        Alumno al = null;
        try {
            conectar = conexion.getConection();
            ps = conectar.prepareStatement(BUSCARCEL);
            ps.setLong(1, celu);
            rs = ps.executeQuery();
            while (rs.next()){
                al = new Alumno();
                al.setNombres(rs.getString("Nombres"));
                al.setApellidos(rs.getString("Apellidos"));
                al.setFecha(rs.getString("Fecha"));
                al.setEmailins(rs.getString("Correoi"));
                al.setEmailper(rs.getString("Correop"));
                al.setCel(rs.getLong("Cel"));
                al.setTel(rs.getLong("Tel"));
                al.setPrograma(rs.getString("Programa"));
                directorio.add(al);
            }
            ps.close();
            conectar.close();
        } catch (Exception e) {
            System.out.println("Error de entrada y salida"+e.getMessage());
        }
        return directorio;
    }
}
