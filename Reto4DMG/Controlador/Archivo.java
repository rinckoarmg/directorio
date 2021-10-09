package Controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Modelo.Alumno;

public class Archivo {
    static String archivo = "datos_estudiantes.csv";
    //crea el archivo en disco, recibe como parámetro la lista de alumnos
    public static void crearArchivo(List<Alumno> directorio) {
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter(archivo);
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter flujoescritura = new BufferedWriter(flwriter);
            for (Alumno alumno : directorio) {
                //escribe los datos en el archivo
                flujoescritura.write(alumno.getNombres()+ "," + alumno.getApellidos()+ "," + alumno.getFecha()
                        + "," + alumno.getEmailins()+ "," + alumno.getEmailper()+ "," + alumno.getCel()+ "," + alumno.getTel() + "," + alumno.getPrograma()+ "\n");
            }
            //cierra el buffer intermedio
            flujoescritura.flush();
            flujoescritura.close();

        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    //crea el archivo en disco, retorna la lista de alumnos
    public static ArrayList leerArchivo() {
        // crea el flujo para leer desde el archivo
        File file = new File(archivo);
        ArrayList listaEstudiantes = new ArrayList<>();
        Scanner sc;
        try {
            //se pasa el flujo al objeto sc
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // el objeto sc lee linea a linea desde el archivo
                String linea = sc.nextLine();
                Scanner delimitar = new Scanner(linea);
                //que valida que antes o despues de una coma (,) exista cualquier cosa
                //parte la cadena recibida cada vez que encuentre una coma				
                delimitar.useDelimiter("\\s*,\\s*");
                Alumno al = new Alumno();
                al.setNombres(delimitar.next());
                al.setApellidos(delimitar.next());
                al.setFecha(delimitar.next());
                al.setEmailins(delimitar.next());
                al.setEmailper(delimitar.next());
                al.setCel(Long.parseLong(delimitar.next()));
                al.setTel(Long.parseLong(delimitar.next()));
                al.setPrograma(delimitar.next());
                listaEstudiantes.add(al);
            }
            //se cierra el ojeto sc
            sc.close();
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        return listaEstudiantes;
    }

    //añadir más alumnos al archivo
    public static void otroArchivo(List<Alumno> directorio) {
        FileWriter flwriter = null;
        try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
            flwriter = new FileWriter(archivo, true);
            BufferedWriter flujoescritura = new BufferedWriter(flwriter);
            for (Alumno alumno : directorio) {
                //escribe los datos en el archivo
                flujoescritura.write(alumno.getNombres()+ "," + alumno.getApellidos()+ "," + alumno.getFecha()
                        + "," + alumno.getEmailins()+ "," + alumno.getEmailper()+ "," + alumno.getCel()+ "," + alumno.getTel() + "," + alumno.getPrograma()+ "\n");
            }
            flujoescritura.flush();
            flujoescritura.close();
   
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }
    }
    public static void borrar() throws IOException{
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter(archivo);
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter flujoescritura = new BufferedWriter(flwriter);
            flujoescritura.write("");
            flujoescritura.flush();
            flujoescritura.close();
        } catch (IOException e){
            
        }
        
    }
    
}
