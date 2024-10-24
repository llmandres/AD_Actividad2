package interfaz;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.TipoDeMotor;
import modelo.negocio.GestorCoche;

public class InterfazCoche {

	GestorCoche gc = new GestorCoche();
	Scanner sc = new Scanner(System.in);

	public void menuSwitch() {
		int opcion1 = 0;
		do {
			System.out.println("---- Menu ----");
			System.out.println("1. Guardar Coche");
			System.out.println("2. Obtener Coche por ID");
			System.out.println("3. Borrar Coche");
			System.out.println("4. Listar Coches");
			System.out.println("5. Salir");
			opcion1 = Integer.parseInt(sc.nextLine());
			switch (opcion1) {
			case 1: {

				Coche c = new Coche();
				System.out.println("Marca del Coche?");
				c.setMarca(sc.nextLine());
				System.out.println("Modelo del Coche?");
				c.setModelo(sc.nextLine());
				System.out.println("Tipo de motor");
				menuMotores();
				int opcion2 = Integer.parseInt(sc.nextLine());
				seleccionarMotor(opcion2, c);
				c.setId(gc.randomID());

				guardarCoche(c);
			}
				break;
			case 2: {
				System.out.println("Introduce la id");
				long id = Long.parseLong(sc.nextLine());
				obetenerPorId(id);
			}
				break;
			case 3: {
				System.out.println("Introduce la id");
				long id = Long.parseLong(sc.nextLine());
				borrarCoche(id);

			}
				break;
			case 4: {
				listarCoches();
			}
				break;
			case 5: {
				System.out.println("Saliendo");
			}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion1);
			}
		} while (opcion1 != 5);

	}

	public void listarCoches() {
		try {
			gc.listarCoche().forEach(c -> {
				System.out.println(c);
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void menuPrincipal() {
		System.out.println("---- Menu ----");
		System.out.println("1. Guardar Coche");
		System.out.println("2. Obtener Coche por ID");
		System.out.println("3. Borrar Coche");
		System.out.println("4. Listar Coches");
		System.out.println("5. Salir");
		System.out.println("Elige una opción: ");

	}

	public void guardarCoche(Coche c) {
		gc.guardar(c);
	}

	public void seleccionarMotor(int opcion, Coche c) {

		switch (opcion) {
		case 1: {
			c.setTipoMotor(TipoDeMotor.DIESEL);
		}
			break;
		case 2: {
			c.setTipoMotor(TipoDeMotor.GASOLINA);
		}
			break;
		case 3: {
			c.setTipoMotor(TipoDeMotor.HIDROGENO);
		}
			break;
		default:
			System.out.println("Opcion Erronea");
		}
	}

	public void menuMotores() {
		System.out.println("1. Diesel");
		System.out.println("2. Gasolina");
		System.out.println("3. Hidrogeno");
	}

	public void obetenerPorId(long id) {
		switch (gc.validarCoche(id)) {
		case 0: {
			System.out.println("El coche no existe");
		}
		case 1: {
			try {
				Coche c = gc.devolverUnCoche(id);
				System.out.println(c.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		break;
		case 404: {
			System.out.println("Ha ocurrido un error");
		}
			break;
		default:
			System.out.println("Error");
		}

	}

	public void borrarCoche(long id) {
		try {
			gc.eliminarCoche(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
