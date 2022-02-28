
// char[][] matriz = {
// { '#', 'S', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
// '#' },
// { '#', 'O', '#', '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'G',
// '#' },
// { '#', 'O', '#', '#', 'O', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'O',
// '#' },
// { '#', 'O', '#', '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O', '#', 'O',
// '#' },
// { '#', 'O', '#', '#', 'O', '#', 'O', '#', '#', '#', '#', '#', 'O', 'O', 'O',
// '#' },
// { '#', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'O', 'O', 'O', '#', '#', '#', '#',
// '#' },
// { '#', 'O', '#', '#', 'O', '#', '#', '#', '#', '#', 'O', 'O', 'O', 'O', 'O',
// '#' },
// { '#', 'O', '#', 'O', 'O', '#', 'O', 'O', 'O', 'O', 'O', '#', '#', '#', '#',
// '#' },
// { '#', 'O', '#', 'G', 'O', '#', '#', '#', '#', '#', 'O', '#', 'G', '#', 'O',
// 'S' },
// { '#', 'O', '#', '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'O',
// '#' },
// { '#', 'O', 'O', 'O', 'O', 'O', 'O', '#', 'G', 'G', 'O', '#', '#', 'O', '#',
// '#' },
// { '#', 'O', '#', 'O', '#', '#', '#', '#', '#', '#', 'O', 'O', 'O', 'O', 'O',
// '#' },
// { '#', 'O', '#', 'O', 'G', '#', 'O', 'O', 'O', 'O', 'O', '#', 'O', '#', 'O',
// '#' },
// { '#', 'O', '#', '#', '#', '#', 'O', '#', '#', '#', '#', '#', 'O', '#', 'O',
// '#' },
// { 'S', 'G', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'O', 'O', 'O', '#', 'G',
// '#' },
// { '#', '#', '#', '#', '#', '#', '#', '#', 'S', '#', '#', '#', '#', '#', '#',
// '#' }
// };

// Mapa mapa = new Mapa("Mapa por defecto", matriz);
// LISTA_MAPAS[0] = mapa;

import java.util.Scanner;

public class Laberinto {

	static Mapa[] LISTA_MAPAS = new Mapa[50];
	static int indiceUltimoMapa = 0;

	static Jugador[] LISTA_JUGADORES = new Jugador[50];
	static int indiceUltimoJugador = 0;

	static int totalPartidasGanadas = 0;
	static int totalPartidas = 0;
	static int totalOroPartidas = 0;
	static int totalMovimientos = 0;

	public static void main(String[] args) {
		Helpers.clear();

		char[][] matriz = {
				{ '#', '#', '#', '#', '#', '#', '#', '#', 'S', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
						'#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
				{ 'S', 'O', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O',
						'O', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'S' },
				{ '#', '#', '#', '#', '#', '#', 'O', 'O', '#', '#', '#', 'O', '#', '#', '#', 'O', '#', 'O', 'O', '#',
						'#', '#', '#', '#', '#', 'O', 'O', '#', 'O', '#' },
				{ '#', 'O', 'O', 'G', 'O', '#', 'O', '#', 'G', 'G', '#', 'O', '#', 'G', '#', 'O', '#', 'G', '#', 'O',
						'#', 'O', 'O', 'O', '#', '#', 'O', 'O', 'O', '#' },
				{ '#', 'O', '#', '#', 'O', '#', 'O', 'O', 'O', 'O', '#', 'G', '#', 'O', '#', 'O', '#', '#', 'O', 'O',
						'#', 'O', '#', 'O', 'O', '#', 'O', '#', 'O', '#' },
				{ '#', 'G', '#', 'O', 'O', '#', 'O', '#', '#', '#', '#', '#', '#', 'O', '#', 'O', 'O', 'O', '#', 'O',
						'#', 'O', '#', '#', '#', '#', 'O', '#', 'O', '#' },
				{ '#', 'O', '#', '#', 'O', 'O', 'O', 'O', 'O', 'O', '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
						'O', 'O', '#', 'O', 'O', 'O', 'O', '#', '#', '#' },
				{ '#', '#', 'O', 'O', 'O', '#', 'O', '#', 'O', '#', 'O', 'O', '#', '#', 'O', '#', '#', 'O', '#', '#',
						'#', '#', '#', 'O', '#', 'O', '#', '#', 'G', '#' },
				{ '#', 'O', 'O', '#', 'O', '#', 'O', 'O', 'O', '#', 'O', '#', 'O', '#', 'O', 'O', '#', 'O', '#', 'O',
						'O', 'O', 'O', 'O', '#', 'O', 'O', '#', 'G', '#' },
				{ '#', 'O', '#', '#', 'O', '#', '#', '#', 'O', '#', 'O', '#', 'O', '#', '#', 'O', '#', 'O', '#', '#',
						'#', 'O', '#', '#', '#', '#', 'G', '#', 'O', '#' },
				{ '#', 'O', 'O', 'O', 'O', '#', 'O', '#', 'O', '#', 'O', '#', 'O', '#', 'O', 'O', '#', 'O', 'O', 'G',
						'#', 'O', 'O', '#', 'O', '#', 'O', '#', 'O', '#' },
				{ '#', '#', '#', 'G', '#', 'O', 'O', 'O', 'O', '#', 'O', '#', 'O', '#', '#', 'O', '#', '#', '#', '#',
						'#', 'O', '#', 'O', '#', '#', 'O', '#', 'O', '#' },
				{ '#', 'O', 'O', '#', 'O', 'O', '#', '#', '#', 'O', 'O', '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', 'O',
						'O', 'O', 'O', 'O', 'O', '#', 'O', '#', 'O', '#' },
				{ '#', '#', 'O', '#', '#', 'O', '#', 'O', 'O', '#', 'O', '#', 'O', 'O', 'O', '#', 'O', '#', '#', 'O',
						'#', 'O', 'O', '#', 'O', '#', 'O', '#', 'O', '#' },
				{ '#', 'O', 'O', '#', 'O', 'O', '#', 'O', '#', '#', 'O', '#', 'G', '#', 'O', '#', '#', 'O', 'O', '#',
						'O', 'O', '#', 'O', 'O', '#', 'O', '#', 'O', '#' },
				{ '#', '#', 'O', '#', '#', 'O', 'O', 'O', 'G', '#', 'O', '#', '#', '#', 'O', '#', 'O', 'O', 'O', 'O',
						'O', '#', 'O', 'O', '#', '#', 'O', '#', 'O', '#' },
				{ '#', 'O', 'O', '#', 'O', 'O', '#', 'O', '#', '#', 'O', 'O', '#', 'O', 'O', '#', 'O', '#', '#', '#',
						'#', '#', 'O', '#', 'O', '#', 'G', '#', 'O', '#' },
				{ '#', '#', 'O', 'O', 'O', '#', '#', '#', 'O', '#', 'O', '#', 'O', '#', 'O', 'O', 'O', '#', 'G', 'O',
						'O', 'O', 'O', 'O', 'O', 'O', '#', '#', 'O', '#' },
				{ '#', 'G', '#', 'O', '#', 'O', 'O', 'O', 'O', '#', 'O', 'O', '#', '#', '#', '#', '#', '#', '#', '#',
						'#', '#', '#', '#', '#', 'O', 'O', 'O', 'O', '#' },
				{ '#', 'G', '#', 'O', 'O', 'O', '#', '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
						'O', 'O', 'O', 'O', '#', '#', 'O', '#', 'O', '#' },
				{ '#', 'O', '#', '#', '#', '#', '#', 'O', 'O', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
						'#', '#', '#', 'O', 'O', '#', 'O', '#', '#', '#' },
				{ '#', 'O', '#', 'O', 'O', 'O', '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
						'O', 'O', 'O', '#', 'O', '#', 'O', 'O', 'O', 'S' },
				{ '#', 'O', 'O', 'O', '#', 'O', '#', 'O', 'O', '#', 'O', '#', '#', '#', '#', '#', '#', 'O', '#', '#',
						'#', 'O', 'O', 'O', 'O', '#', 'O', '#', '#', '#' },
				{ '#', '#', '#', '#', '#', 'O', '#', 'O', '#', 'O', 'O', '#', 'G', 'O', 'O', 'O', '#', 'O', 'O', 'O',
						'#', 'O', '#', '#', '#', '#', 'O', '#', 'O', '#' },
				{ '#', 'O', '#', 'O', 'O', 'O', '#', 'O', 'G', '#', 'O', '#', '#', '#', '#', 'O', '#', '#', '#', 'O',
						'#', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'O', '#' },
				{ '#', 'O', '#', '#', '#', 'O', '#', '#', '#', '#', 'O', '#', 'O', 'O', 'O', 'O', '#', 'G', '#', 'O',
						'#', '#', '#', 'O', '#', 'G', '#', '#', 'O', '#' },
				{ '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O', '#', 'O', '#', 'O',
						'O', 'O', 'O', 'O', '#', 'O', '#', 'O', 'O', '#' },
				{ '#', 'O', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'O', '#', 'O', '#', '#', '#', 'O', '#', 'O',
						'#', '#', '#', '#', '#', 'O', 'O', 'O', '#', '#' },
				{ '#', 'O', 'O', 'O', 'O', 'O', 'G', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
						'O', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'G', '#' },
				{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'S', '#', '#', '#', '#', '#', '#',
						'#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
		};

		Salida[] salidas = {
				new Salida(30, new int[] { 0, 8 }),
				new Salida(15, new int[] { 1, 0 }),
				new Salida(51, new int[] { 1, 29 }),
				new Salida(51, new int[] { 21, 29 }),
				new Salida(51, new int[] { 29, 13 }),
		};

		Oro[] listaOro = {
				new Oro(5, new int[] { 3, 3 }),
				new Oro(7, new int[] { 3, 8 }),
				new Oro(3, new int[] { 3, 9 }),
				new Oro(5, new int[] { 3, 13 }),
				new Oro(5, new int[] { 3, 17 }),

				new Oro(2, new int[] { 3, 11 }),

				new Oro(3, new int[] { 5, 1 }),

				new Oro(7, new int[] { 7, 28 }),

				new Oro(1, new int[] { 8, 28 }),

				new Oro(1, new int[] { 9, 26 }),

				new Oro(2, new int[] { 10, 19 }),

				new Oro(1, new int[] { 11, 3 }),

				new Oro(2, new int[] { 14, 12 }),

				new Oro(1, new int[] { 15, 8 }),

				new Oro(4, new int[] { 16, 26 }),

				new Oro(2, new int[] { 17, 18 }),

				new Oro(5, new int[] { 18, 1 }),

				new Oro(7, new int[] { 19, 1 }),
				new Oro(7, new int[] { 19, 13 }),
				new Oro(2, new int[] { 19, 17 }),
				new Oro(3, new int[] { 19, 21 }),

				new Oro(3, new int[] { 23, 12 }),

				new Oro(3, new int[] { 24, 8 }),

				new Oro(9, new int[] { 25, 17 }),
				new Oro(5, new int[] { 25, 25 }),

				new Oro(8, new int[] { 28, 6 }),
				new Oro(10, new int[] { 28, 28 }),
		};

		Mapa mapaPorDefecto = new Mapa("Mapa por defecto", matriz, salidas, listaOro);
		LISTA_MAPAS[indiceUltimoMapa] = mapaPorDefecto;
		indiceUltimoMapa++;

		char[][] matriz2 = {
				{ '#', 'G', '#', 'G', 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'S', 'O', '#', '#', '#', '#', 'O', '#', '#', 'O' },
				{ '#', 'O', 'O', 'O', 'O', 'O', 'O', 'O', '#', 'O' },
				{ '#', 'O', '#', '#', '#', '#', '#', 'O', '#', 'O' },
				{ '#', 'O', 'O', 'O', 'O', 'S', '#', 'G', '#', 'G' }
		};

		// Arreglo de salidas
		Salida[] salidas2 = {
				new Salida(10, new int[] { 1, 0 }),
				new Salida(20, new int[] { 4, 5 })
		};

		Oro[] listaOro2 = {
				new Oro(5, new int[] { 0, 1 }),
				new Oro(3, new int[] { 0, 3 }),
				new Oro(2, new int[] { 4, 7 }),
				new Oro(10, new int[] { 4, 9 }),
		};

		Mapa mapaPequeño = new Mapa("Mapa pequeño", matriz2, salidas2, listaOro2);
		LISTA_MAPAS[indiceUltimoMapa] = mapaPequeño;
		indiceUltimoMapa++;

		menuPrincipal(false);

	}

	public static void menuPrincipal(boolean deNuevo) {

		Helpers.clear();

		if (deNuevo) {
			System.out.println("El número escrito no es una opción valida, por favor vuelva a intentar");
		}

		Scanner scanner = new Scanner(System.in);
		final String[] MENU_PRINCIPAL_OPCIONES = new String[] { "JUGAR", "CREAR", "REPORTES", "VISUALIZAR", "SALIR" };
		System.out.println("---------------MENU--PRINCIPAL-----------------");
		System.out.println("Por favor eliga una de las siguientes opciones:");
		for (int i = 0; i < MENU_PRINCIPAL_OPCIONES.length; i++) {

			System.out.println((i + 1) + ". " + MENU_PRINCIPAL_OPCIONES[i]);

		}

		int OPCION_MENU_PRINCIPAL = scanner.nextInt();
		switchOpcionesMenuPrincipal(OPCION_MENU_PRINCIPAL);

	}

	public static void switchOpcionesMenuPrincipal(int _opcion) {

		Juego juego = new Juego();
		Mapas mapa = new Mapas();
		Reportes reportes = new Reportes();

		switch (_opcion) {
			case 1:
				juego.abrirMenu(false);
				break;
			case 2:
				mapa.crearMapa();
				break;
			case 3:
				reportes.reporteGeneral();
				break;
			case 4:
				Mapas.abrirMenu(false);
				break;
			case 5:
				System.out.println("Saliendo del juego...");
				break;

			default:
				menuPrincipal(true);
				break;
		}

	}

}
