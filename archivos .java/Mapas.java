import java.util.Scanner;

public class Mapas {

	static Scanner scanner = new Scanner(System.in);

	public void crearMapa() {

		Helpers.clear();

		String NOMBRE_NUEVO_MAPA;
		int COLUMNAS, FILAS;

		System.out.println("--------------------CREAR--MAPA--------------------");

		System.out.println("Por favor escriba el número de columnas del mapa");
		COLUMNAS = scanner.nextInt();

		System.out.println("Por favor escriba el número de filas del mapa");
		FILAS = scanner.nextInt();

		char matrizMapa[][] = new char[FILAS][COLUMNAS];

		boolean tieneSalidas = false;

		Salida[] tempListaSalidas = new Salida[200];
		int indicetempListaSalidas = 0;

		Oro[] tempListaCasillasOro = new Oro[200];
		int indiceListaOro = 0;

		int sumaOro = 0;
		int sumaOroFila = 0;

		int salidaMayorOro = 0;
		int salidaMayorAnterior;

		int casillasOroIngresadasFila = 0;
		int casillasSalidaIngresadasFila = 0;

		for (int i = 0; i < FILAS; i++) {
			System.out.println("Por favor escriba con el formato descrito en el manual la fila #" + (i + 1));
			String stringFila = scanner.next();

			sumaOroFila = 0;
			casillasOroIngresadasFila = 0;
			casillasSalidaIngresadasFila = 0;
			salidaMayorAnterior = salidaMayorOro;

			for (int j = 0; j < COLUMNAS; j++) {
				char casilla = stringFila.charAt(j);
				if (casilla == 'S') {

					System.out.println("Por favor ingrese la cantidad de oro necesaria para la salida de la fila "
							+ (i + 1) + " en la columna " + (j + 1));
					int oroNecesario = scanner.nextInt();
					int[] posicion = { i, j };
					tieneSalidas = true;
					Salida salida = new Salida(oroNecesario, posicion);
					tempListaSalidas[indicetempListaSalidas] = salida;
					indicetempListaSalidas++;
					casillasSalidaIngresadasFila++;
					if (oroNecesario > salidaMayorOro)
						salidaMayorOro = oroNecesario;

				} else if (casilla == 'G') {

					System.out.println("Por favor ingrese la cantidad de oro que contiene la casilla de oro en la fila "
							+ (i + 1) + " con número de columna " + (j + 1));
					int oroCasilla = scanner.nextInt();
					sumaOro += oroCasilla;
					sumaOroFila += oroCasilla;
					int[] posicion = { i, j };
					Oro oro = new Oro(oroCasilla, posicion);
					tempListaCasillasOro[indiceListaOro] = oro;
					indiceListaOro++;
					casillasOroIngresadasFila++;

				}

				matrizMapa[i][j] = casilla;
			}

			while (i == (FILAS - 1) && !tieneSalidas) {
				indiceListaOro -= casillasOroIngresadasFila;
				System.out.println("Su mapa no tiene salidas, por favor vuelva a ingresar la última fila con una.");
				sumaOro -= sumaOroFila;
				sumaOroFila = 0;
				System.out.println("Por favor escriba con el formato descrito en el manual la fila #" + (i + 1));
				stringFila = scanner.next();

				for (int j = 0; j < COLUMNAS; j++) {
					char casilla = stringFila.charAt(j);
					if (casilla == 'S') {
						System.out.println("Por favor ingrese la cantidad de oro necesaria para la salida de la fila "
								+ (i + 1) + " en la columna " + (j + 1));
						int oroNecesario = scanner.nextInt();
						int[] posicion = { i, j };
						tieneSalidas = true;
						Salida salida = new Salida(oroNecesario, posicion);
						tempListaSalidas[indicetempListaSalidas] = salida;
						indicetempListaSalidas++;
						casillasSalidaIngresadasFila++;
						if (oroNecesario > salidaMayorOro)
							salidaMayorOro = oroNecesario;

					} else if (casilla == 'G') {

						System.out.println(
								"Por favor ingrese la cantidad de oro que contiene la casilla de oro en la fila "
										+ (i + 1) + " con número de columna " + (j + 1));
						int oroCasilla = scanner.nextInt();
						sumaOro += oroCasilla;
						sumaOroFila += oroCasilla;
						int[] posicion = { i, j };
						Oro oro = new Oro(oroCasilla, posicion);
						tempListaCasillasOro[indiceListaOro] = oro;
						indiceListaOro++;
						casillasOroIngresadasFila++;

					}

					matrizMapa[i][j] = casilla;
				}
			}
			while (i == (FILAS - 1) && (salidaMayorOro > sumaOro)) {
				salidaMayorOro = salidaMayorAnterior;
				indiceListaOro -= casillasOroIngresadasFila;
				indicetempListaSalidas--;

				System.out.println(
						"La cantidad de oro total del mapa, sumada entre todas las casillas no es suficiente para alguna/s salidas");
				sumaOro -= sumaOroFila;
				sumaOroFila = 0;
				System.out.println("Por favor escriba con el formato descrito en el manual la fila #" + (i + 1)
						+ " cumpliendo que la suma total de las casillas");
				System.out.println("de oro sean igual o mayor a la cantidad mayor de oro necesaria para una salida.");
				stringFila = scanner.next();

				for (int j = 0; j < COLUMNAS; j++) {
					char casilla = stringFila.charAt(j);
					if (casilla == 'S') {
						System.out.println("Por favor ingrese la cantidad de oro necesaria para la salida de la fila "
								+ (i + 1) + " en la columna " + (j + 1));
						int oroNecesario = scanner.nextInt();
						int[] posicion = { i, j };
						tieneSalidas = true;
						Salida salida = new Salida(oroNecesario, posicion);
						tempListaSalidas[indicetempListaSalidas] = salida;
						indicetempListaSalidas++;
						casillasSalidaIngresadasFila++;
						if (oroNecesario > salidaMayorOro)
							salidaMayorOro = oroNecesario;

					} else if (casilla == 'G') {

						System.out.println(
								"Por favor ingrese la cantidad de oro que contiene la casilla de oro en la fila "
										+ (i + 1) + " con número de columna " + (j + 1));
						int oroCasilla = scanner.nextInt();
						sumaOro += oroCasilla;
						sumaOroFila += oroCasilla;
						int[] posicion = { i, j };
						Oro oro = new Oro(oroCasilla, posicion);
						tempListaCasillasOro[indiceListaOro] = oro;
						indiceListaOro++;
						casillasOroIngresadasFila++;

					}

					matrizMapa[i][j] = casilla;
				}
			}

		}

		Oro[] listaOro = new Oro[indiceListaOro];

		for (int i = 0; i < tempListaCasillasOro.length; i++) {
			if (tempListaCasillasOro[i] != null) {
				listaOro[i] = tempListaCasillasOro[i];
			}
		}

		Salida[] salidas = new Salida[indicetempListaSalidas];

		for (int i = 0; i < tempListaSalidas.length; i++) {
			if (tempListaSalidas[i] != null) {
				salidas[i] = tempListaSalidas[i];
			}
		}

		System.out.println("Por favor escriba el nombre que tendrá su mapa");
		scanner.nextLine();
		NOMBRE_NUEVO_MAPA = scanner.nextLine();

		Mapa mapa = new Mapa(NOMBRE_NUEVO_MAPA, matrizMapa, salidas,
				listaOro);
		Laberinto.LISTA_MAPAS[Laberinto.indiceUltimoMapa] = mapa;

		Laberinto.indiceUltimoMapa++;

		System.out.println(
				"Su mapa ha sido creado de manera existosa, puede visualizarlo desde la opcion \"VISUALIZAR\" del menu principal");

		crearMapaSalir(false);

	}

	public void crearMapaSalir(boolean _deNuevo) {
		if (_deNuevo) {
			Helpers.clear();
			System.out.println("El numero ingresado no es valido, intente nuevamente");
		}
		System.out.println("Por favor eliga una de las siguientes opciones");
		System.out.println("1. SALIR AL MENU PRINCIPAL");
		int OPCION_CREAR_MAPA = scanner.nextInt();

		if (OPCION_CREAR_MAPA == 1) {
			Laberinto.menuPrincipal(false);
		} else {
			crearMapaSalir(true);
		}
	}

	static void previsualizarMapa(Mapa _mapa, boolean _deNuevo) {

		Helpers.clear();

		int filas = _mapa.matriz.length;
		int columnas = _mapa.matriz[0].length;

		System.out.println("-----------------------------------------------------");
		System.out.println("Previsualización del mapa con nombre \"" + _mapa.nombre + "\"");

		for (int i = 0; i < filas; i++) {
			String columna = "";
			for (int j = 0; j < columnas; j++) {
				columna = columna + _mapa.matriz[i][j] + " ";
			}
			System.out.println(columna);
		}

		// System.out.println("Lista de salidas del mapa con el oro necesario");
		// for (int i = 0; i < _mapa.salidas.length; i++)
		// System.out.println("la salida #" + (i + 1) + " necesita " +
		// _mapa.salidas[i].oroNecesario
		// + " de oro y está en la posición [" + _mapa.salidas[i].posicion[0] + ","
		// + _mapa.salidas[i].posicion[1] + "]");

		// System.out.println("Lista de casillas de oro con su cantidad de oro");
		// for (int i = 0; i < _mapa.listaOro.length; i++)
		// System.out.println("la casilla de oro #" + (i + 1) + " almacena " +
		// _mapa.listaOro[i].cantidadOro
		// + " de oro y está en la posición [" + _mapa.listaOro[i].posicion[0] + ","
		// + _mapa.listaOro[i].posicion[1] + "]");

		if (_deNuevo)
			System.out.println("El número ingresado no es válido");

		System.out.println("Por favor selecciones una de las siguientes opciones");
		System.out.println("1. SALIR AL MENU PRINCIPAL");
		System.out.println("2. SALIR AL MENU PREVISUALIZAR MAPA ");

		int OPCION_PREVISUALIZAR_MAPA = scanner.nextInt();

		if (OPCION_PREVISUALIZAR_MAPA == 1) {
			Laberinto.menuPrincipal(false);
		} else if (OPCION_PREVISUALIZAR_MAPA == 2) {
			abrirMenu(false);
		} else {
			previsualizarMapa(_mapa, true);
		}

	}

	static void abrirMenu(boolean deNuevo) {

		Helpers.clear();

		if (deNuevo) {
			System.out.println("El número escrito no es una opción valida, por favor vuelva a intentar");
		}

		System.out.println("-----------MENU--PREVISUALIZAR--MAPA------------");
		System.out.println("Por favor eliga el mapa que desea previsualizar:");

		int ID_SALIR = 0;

		for (int i = 0; i < Laberinto.LISTA_MAPAS.length; i++) {
			if (Laberinto.LISTA_MAPAS[i] != null) {

				System.out.println((i + 1) + ". " + Laberinto.LISTA_MAPAS[i].nombre);
				ID_SALIR = i + 2;

			}
		}
		System.out.println(ID_SALIR + ". REGRESAR AL MENU PRINCIPAL ");

		switchOpcionesMenuPrevisualizar(ID_SALIR);
	}

	static void switchOpcionesMenuPrevisualizar(int _id_salir) {

		int OPCION_MENU = scanner.nextInt();

		if (OPCION_MENU == _id_salir) {
			Laberinto.menuPrincipal(false);
		} else if (OPCION_MENU < _id_salir) {
			previsualizarMapa(Laberinto.LISTA_MAPAS[OPCION_MENU - 1], false);
		} else {
			abrirMenu(true);
		}

	}

}
