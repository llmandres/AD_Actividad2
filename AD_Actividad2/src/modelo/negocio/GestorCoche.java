package modelo.negocio;

import java.io.IOException;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheFichero;

public class GestorCoche {
	
	private DaoCocheFichero dc;
	
	/**
 con el Videojuego pasado por parametro.
	 * 
	 * @param c Coche a comparar
	 * @return <b>0</b> el Coche no existe, <b>1</b> el Coche existe y es
	 *         valido, <b>2</b> el Coche existe pero no es valido y <b>404</b>
	 *         en caso de que haya algún problema en el de entrada salida
	 */
	public int validarCoche(long id) {
	    dc = new DaoCocheFichero();
	    try {
	        Coche c = dc.getByID(id); 
	        if (c == null) {
	            return 0;
	        }else {
	        return 1; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return 404; 
	    }
	}
	/**
	 * Método que guarda un Coche pasado por parametro
	 * 
	 * @param v el Videojuego a guardar
	 * @return <b>0</b> el Videojuego pasado por parametro es null, <b>1</b> la
	 *         marca esta vacia  <b>2</b> el modelo esta vacio <b>3</b> el Coche se
	 *         ha guardado <b>500</b> en caso de que haya algún problema en el de 
	 *         entrada salida
	 */
	public int guardar(Coche c) {
		
		if (c == null) {
			return 0;
		}

		dc = new DaoCocheFichero();
		try {
			if (c.getMarca().isBlank()) {
				return 1;
			} else if (c.getModelo().isBlank()) {
				return 2;
			}  else {
				dc.register(c);
				return 3;
			}
		} catch (Exception e) {
			return 500;
		}
	}
	public List<Coche> listarCoche() throws Exception {
		dc = new DaoCocheFichero();

		return dc.listarCoches();
	}
	public void eliminarCoche(long id) throws Exception {
		dc = new DaoCocheFichero();
		try {
			dc.eliminarCoche(id);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public long randomID() {
		dc = new DaoCocheFichero();
		return dc.generarId();
	}
	public Coche devolverUnCoche(long id) throws Exception {
		dc = new DaoCocheFichero();
			Coche c = dc.getByID(id);
			return c;
	}

}
