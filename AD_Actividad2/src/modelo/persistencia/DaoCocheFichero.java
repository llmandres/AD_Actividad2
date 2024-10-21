package modelo.persistencia;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;

public class DaoCocheFichero {
	private static final String NOMBRE_FICHERO = "coches.dat";

	public Coche getByID(long id) throws Exception {
		Coche v = null;
		try (FileInputStream fr = new FileInputStream(NOMBRE_FICHERO); ObjectInputStream br = new ObjectInputStream(fr)) {

				int bytesEnBuffer = fr.available();
				Coche c = null;
				while (bytesEnBuffer > 0) {//mientras haya bytes que leer, sigo leyendo objetos
					try {
						c = (Coche) br.readObject();
						Coche c1 = new Coche();
						c1.setId(c.getId());
						c1.setMarca(c.getMarca());
						c1.setModelo(c.getMarca());
						c1.setTipoMotor(c.getTipoMotor());
						return c1;
					} catch (IOException e2) {
						System.out.println("Error al leer los contactos de la agenda");
						System.out.println(e2.getMessage());
					} catch (ClassNotFoundException e3) {
						System.out.println("La clase Contacto no est� cargada en memoria");
						System.out.println(e3.getMessage());
					}
					bytesEnBuffer = fr.available();//preguntamos si hay m�s contenido disponible
												//este metodo nos devuelve el numero de bytes que quedan por leer
					System.out.println("Bytes pendientes en buffer: " + bytesEnBuffer);
				}
			}catch (IOException e) {
			}	
			return null;
		} 
	
	public void register(Coche c) throws Exception {
		File f = new File(NOMBRE_FICHERO);
		if (!f.exists()) {
			throw new Exception("fichero no existe");
		}
		try (FileOutputStream fw = new FileOutputStream(NOMBRE_FICHERO, true); ObjectOutputStream bw = new ObjectOutputStream(fw)) {
			bw.writeObject(c);
		} catch (Exception e) {
			throw e;
		}
	}
	public void eliminarVideojuego(long id) throws Exception {
	    List<Coche> coches = new ArrayList<>(); 
	    boolean encontrado = false;

	    try (ObjectInputStream buffer = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO))) {
	        while (true) {
	            try {
	                Coche c = (Coche) buffer.readObject();
	                if (c.getId() != id) { 
	                    coches.add(c); 
	                } else {
	                    encontrado = true; 
	                }
	            } catch (EOFException e) {
	                break; 
	            } catch (IOException | ClassNotFoundException e) {
	                System.out.println("Error al leer el archivo: " + e.getMessage());
	                throw e; 
	            }
	        }
	    }
	    if (encontrado) {
	        try (ObjectOutputStream buffer = new ObjectOutputStream(new FileOutputStream(NOMBRE_FICHERO))) {
	            for (Coche v : coches) {
	                buffer.writeObject(v); 
	            }
	        }
	        System.out.println("Videojuego con ID '" + id + "' eliminado.");
	    } else {
	        System.out.println("Videojuego no encontrado con ID: " + id);
	    }
	}
	public List<Coche> listarCoches() throws Exception {
		List<Coche> listaCoches = new ArrayList<>();

		try (FileInputStream fr = new FileInputStream(NOMBRE_FICHERO); ObjectInputStream br = new ObjectInputStream(fr)) {

			int bytesEnBuffer = fr.available();
			Coche c = null;
			while (bytesEnBuffer > 0) {
				try {
					c = (Coche) br.readObject();
					Coche c1 = new Coche();
					c1.setId(c.getId());
					c1.setMarca(c.getMarca());
					c1.setModelo(c.getMarca());
					c1.setTipoMotor(c.getTipoMotor());
					listaCoches.add(c1);
				} catch (IOException e2) {
					System.out.println("Error al leer los contactos de la agenda");
					System.out.println(e2.getMessage());
				} catch (ClassNotFoundException e3) {
					System.out.println("La clase Contacto no est� cargada en memoria");
					System.out.println(e3.getMessage());
				}
				bytesEnBuffer = fr.available();

				System.out.println("Bytes pendientes en buffer: " + bytesEnBuffer);
			}
		}catch (IOException e) {
		}	
		return listaCoches;
	}

}
