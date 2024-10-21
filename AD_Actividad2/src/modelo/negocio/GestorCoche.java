package modelo.negocio;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheFichero;

public class GestorCoche {
	
	private DaoCocheFichero dc;
	
	/**
 con el Videojuego pasado por parametro.
	 * 
	 * @param c Coche a comparar
	 * @return <b>0</b> el Coche no existe, <b>1</b> el Videojuego existe y es
	 *         valido, <b>2</b> el Videojuego existe pero no es valido y <b>404</b>
	 *         en caso de que haya algún problema en el de entrada salida
	 */
	public int validarVideojuego(Coche c) {
		dc = new DaoCocheFichero();
		try {
			Coche cFichero = dc.getByID(c.getId());
			if (cFichero == null) {
				return 0;
			}
			if (cFichero.equals(c)) {
				return 1;
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 404;
		}
	}

}
