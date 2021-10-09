package Vista;

import Controlador.Alumnodao;
import java.io.IOException;
import java.util.Scanner;
import Modelo.Alumno;
import Vista.Consultas;
//import Modelo.conexion;
import java.io.FileNotFoundException;

public class Principal {
    
    public static String nombres, apellidos, fecha, correoins, correoper, programa;
    public static long cel, tel;
    static Scanner sc = new Scanner(System.in);
    static int opcion;
    static Alumnodao aBO = new Alumnodao();
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        //Conexion
        //new conexion();
        //objeto business object
        
        
        boolean salir = false;
        
        while (!salir) {
            System.out.println("INSTITUTO LA FLORESTA");
            System.out.println("Seleccione tarea a realizar: ");
            System.out.println("1. Ingresar estudiante");
            System.out.println("2. Consultas");
            System.out.println("3. Modificar estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("5. Ver directorio de estudiantes");
            System.out.println("6. Salir");
            try{
                System.out.println("Opción: ");
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (Exception e){
                System.out.println("El dato ingresado no es un número");
            }
            System.out.println("");

            switch (opcion) {
                case 1:
                    System.out.println("Ingresar estudiante");
                    System.out.println("Ingresar nombres: ");
                    nombres = sc.nextLine();
                    System.out.println("\nIngresar apellidos: ");
                    apellidos = sc.nextLine();
                    System.out.println("\nIngresar fecha de nacimiento (YYYY-MM-DD): ");
                    fecha = sc.nextLine();
                    System.out.println("\nIngresar correo institucional: ");
                    correoins = sc.nextLine();
                    System.out.println("\nIngresar correo personal: ");
                    correoper = sc.nextLine();
                    try{
                        System.out.println("\nIngresar número de celular: ");
                        cel = sc.nextLong();
                        //cel = Long.parseLong(sc.nextLine());
                        sc.nextLine();
                    } catch (Exception e){
                        cel = 0; 
                    }
                    try{
                        System.out.println("\nIngresar número fijo: ");
                        tel = sc.nextLong();
                        sc.nextLine();
                    } catch (Exception e){
                        tel = 0;
                    }
                    
                    System.out.println("\nIngresar programa: ");
                    programa = sc.nextLine();
                    aBO.crear(nombres, apellidos, fecha, correoins, correoper, cel, tel, programa);
                    System.out.println("Se agregó el estudiante\n");
                    break;
                case 2: 
                    Consultas con = new Consultas();
                    con.menusec();
                    break;
                case 3:
                    
                    System.out.println("Modificar estudiante");
                    System.out.println("Ingresar correo institucional: ");
                    correoins = sc.nextLine();
                    Alumno validar = aBO.obtener(correoins);
                    if (validar != null){
                        System.out.println("\nIngresar correo personal: ");
                        correoper = sc.nextLine();
                        try{
                            System.out.println("\nIngresar número de celular: ");
                            cel = Long.parseLong(sc.nextLine());
                        } catch (Exception e){
                            cel = 0;
                        }
                        try{
                            System.out.println("\nIngresar número fijo: ");
                            tel = Long.parseLong(sc.nextLine());
                        } catch (Exception e){
                            tel = 0;
                        }
                        System.out.println("Ingresar programa: ");
                        programa = sc.nextLine();
                        aBO.actualizar(correoins, correoper, cel, tel, programa);
                        //System.out.println("Se modificó el estudiante");
                    } else {
                        System.out.println("El estudiante no se encuentra registrado en el instituto");
                    }
                    break;
                case 4:
                    System.out.println("Eliminar estudiante");
                    System.out.println("Ingresar correo institucional: ");
                    correoins = sc.nextLine();
                    validar = aBO.obtener(correoins);
                    if (validar != null){
                        aBO.eliminar(correoins);
                        //System.out.println("Se eliminó el estudiante");
                    } else {
                        System.out.println("El estudiante no se encuentra registrado en el instituto");
                    }
                    break;
                case 5:
                    System.out.println("\nEl directorio de los estudiantes");
                    //obtiene todos los alumnos
                    aBO.obtenerdirectorio().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("\nHasta pronto");
                    salir = true;
                    break;
            }
        }
    }
}

