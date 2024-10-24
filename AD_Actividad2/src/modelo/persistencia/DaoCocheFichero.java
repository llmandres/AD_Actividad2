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
import java.util.Random;

import modelo.entidad.Coche;

public class DaoCocheFichero {
	private static final String NOMBRE_FICHERO = "coches.dat";

	public Coche getByID(long id) {
		Coche v = null;
	    File file = new File(NOMBRE_FICHERO);
	    if (!file.exists()) {
	          try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		try (FileInputStream fr = new FileInputStream(NOMBRE_FICHERO); ObjectInputStream br = new ObjectInputStream(fr)) {

				int bytesEnBuffer = fr.available();
				Coche c = null;
				while (bytesEnBuffer > 0) {
					try {
						c = (Coche) br.readObject();
						if(c.getId() == id) {
						Coche c1 = new Coche();
						c1.setId(c.getId());
						c1.setMarca(c.getMarca());
						c1.setModelo(c.getMarca());
						c1.setTipoMotor(c.getTipoMotor());
						if (c1.getId() == id) 
						return c1;
						}
					} catch (IOException e2) {

						System.out.println(e2.getMessage());
					} catch (ClassNotFoundException e3) {
						System.out.println(e3.getMessage());
					}
					bytesEnBuffer = fr.available();
	
				}
			}catch (IOException e) {
			}	
			return null;
		} 
	
	public void register(Coche c) throws Exception {
	    File file = new File(NOMBRE_FICHERO);
	    if (!file.exists()) {
	        try {
	            if (file.createNewFile()) {

	            } else {

	                return;
	            }
	        } catch (IOException e) {

	            return;
	        }
	    }

	    List<Coche> coches = listarCoches(); 
	    coches.add(c); 
	    try (ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(NOMBRE_FICHERO))) {
	        for (Coche coche : coches) {
	            bw.writeObject(coche); 
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}
	public void eliminarCoche(long id) throws Exception {
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

					System.out.println(e2.getMessage());
				} catch (ClassNotFoundException e3) {

					System.out.println(e3.getMessage());
				}
				bytesEnBuffer = fr.available();

	
			}
		}catch (IOException e) {
		}	
		return listaCoches;
	}
	public long generarId() {
		Random r = new Random();
		boolean distinto = false;
		long id =r.nextLong(1, 1000000);;
		try {
			 for (Coche c : listarCoches()){
				do {
				if (id == c.getId()) {
					id = r.nextLong(1, 1000000);
				}else {
					distinto = true;
				}
				}while(!distinto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
		
	}

}
