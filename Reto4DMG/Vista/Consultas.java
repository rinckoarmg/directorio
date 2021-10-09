
package Vista;
    
import Modelo.Alumno;
import java.util.Scanner;

public class Consultas extends Principal {
    
    public void menusec(){
    System.out.println("Consultas");
    System.out.println("Seleccione consulta a realizar: ");
    System.out.println("1. Buscar estudiante por correo electrónico");
    System.out.println("2. Buscar estudiante por apellidos");
    System.out.println("3. Buscar por programa");
    System.out.println("4. Buscar cantidad de estudiantes por programa");
    System.out.println("5. Buscar por fecha de nacimiento");
    System.out.println("6. Buscar por número de celular");
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
            System.out.println("Buscar estudiante por correo electrónico");
            System.out.println("Ingresar correo institucional: ");
            correoins = sc.nextLine();
            Alumno alumno = aBO.obtener(correoins);
            if (alumno != null){
                System.out.println(alumno);
            } else {
                System.out.println("El estudiante no se encuentra registrado en el instituto\n");
            }
            break;
        case 2: 
            System.out.println("Buscar estudiante por apellidos");
            System.out.println("Ingresar apellidos: ");
            apellidos = sc.nextLine();
            aBO.listado(apellidos,1).forEach(System.out::println);
            if ( aBO.listado(apellidos,1)== null){
                System.out.println("El estudiante no se encuentra registrado en el instituto\n");
            }
            break;
        case 3:

            System.out.println("Buscar por programa");
            System.out.println("Ingresar programa: ");
            programa = sc.nextLine();
            for(Alumno alu : (aBO.listado(programa,2)))
                System.out.println(alu.getNombres() + " " + alu.getApellidos());
            //aBO.buscarProg(programa).forEach(System.out::println);
            if ( aBO.listado(programa,2)== null){
                System.out.println("El estudiante no se encuentra registrado en el instituto\n");
            }
            break;
        case 4:
            System.out.println("Buscar cantidad de estudiantes por programa");
            System.out.println("Ingresar programa: ");
            programa = sc.nextLine();
            int cont = 0;
            for(Alumno alu : (aBO.listado(programa,2)))
                cont++;
            System.out.println("Cantidad estudiantes "+ programa + " :" + cont + "\n");
            break;
        case 5:
            System.out.println("Buscar por fecha de nacimiento");
            System.out.println("Ingresar fecha de nacimiento: ");
            fecha = sc.nextLine();
            aBO.listado(fecha,3).forEach(System.out::println);
            if ( aBO.listado(fecha,3)== null){
                System.out.println("El estudiante no se encuentra registrado en el instituto\n");
            }
            break;
        case 6:
            System.out.println("Buscar por número de celular");
            System.out.println("Ingresar número de celular: ");
            cel = sc.nextLong();
            sc.nextLine();
            aBO.buscarCel(cel).forEach(System.out::println);
            if ( aBO.buscarCel(cel)== null){
                System.out.println("El estudiante no se encuentra registrado en el instituto\n");
            }
            break;
        }
    }    
}
