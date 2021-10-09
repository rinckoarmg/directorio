/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author dianagamboa
 */
import java.io.Serializable;

public class Alumno implements Serializable {
    private String nombres;
    private String apellidos;
    private String fecha;
    private String emailins;
    private String emailper;
    private long cel;
    private long tel;
    private String programa;

    // constructor
    public Alumno(String nombres, String apellidos, String fecha, String emailins, String emailper, long cel, long tel, String programa) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.emailins = emailins;
        this.emailper = emailper;
        this.cel = cel;
        this.tel = tel;
        this.programa = programa;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmailins() {
        return emailins;
    }

    public void setEmailins(String emailins) {
        this.emailins = emailins;
    }

    public String getEmailper() {
        return emailper;
    }

    public void setEmailper(String emailper) {
        this.emailper = emailper;
    }

    public long getCel() {
        return cel;
    }

    public void setCel(long cel) {
        this.cel = cel;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Alumno() { }
    
    @Override
    public String toString() {
        return "Nombres: " + nombres + "\nApellidos: " + apellidos + "\nFecha nacimiento: " + fecha + "\nCorreo institucional: " + emailins + "\nCorreo personal: " + emailper + "\nNúmero de teléfono celular: " + cel + "\nNúmero de teléfono fijo: " + tel + "\nPrograma académico: " + programa + "\n";
    }
    
}
