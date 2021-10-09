
package Controlador;

import Modelo.Alumno;
import java.util.List;

/**
 *
 * @author dianagamboa
 */
public interface Interf {
    public boolean registrar(Alumno alumno);
    public List<Alumno> obtener();
    public boolean actualizar(Alumno alumno);
    public boolean eliminar(Alumno alumno);
}
