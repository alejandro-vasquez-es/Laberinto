import java.util.Scanner;

public class Reportes {

	static Scanner scanner = new Scanner(System.in);

	static void reportesFinalizarJuego(Jugador _jugador) {

		Helpers.clear();
		System.out.println("----Reporte--de-la--partida-------");
		System.out.println("Ha ganado el juego !Felicidades!");
		_jugador.getOroRecolectado();
		_jugador.getMovimientos(_jugador.estado);

		int opcion = 0;
		while (opcion != 1) {
			System.out.println("Por favor presione 1 para salir al menu de eleccion de mapa para jugar");
			opcion = scanner.nextInt();
		}

		Juego juego = new Juego();
		juego.abrirMenu(false);

	}

	public void reporteGeneral() {

		Helpers.clear();

		System.out.println("----------REPORTES-GENERALES----------");

		// cantidad de veces atrapado por el bot

		System.out.println("La cantidad de partidas ganadas es de: " + Laberinto.totalPartidasGanadas);

		double oroPromedioPartida = 0;
		double movimientosPromedio = 0;

		if (Laberinto.totalPartidas != 0) {
			oroPromedioPartida = Laberinto.totalOroPartidas / Laberinto.totalPartidas;
			System.out.println("El oro promedio por partida es de: " + oroPromedioPartida);

			movimientosPromedio = Laberinto.totalMovimientos / Laberinto.totalPartidas;
			System.out.println("El promedio de movimientos por partida es de: " + movimientosPromedio);
		}

		int mapaMasJugado = 0;
		String nombreMapaMasJugado = "No se ha jugado ningun mapa";
		for (int i = 0; i < Laberinto.LISTA_MAPAS.length; i++) {
			if (Laberinto.LISTA_MAPAS[i] != null) {
				if (Laberinto.LISTA_MAPAS[i].vecesJugado > mapaMasJugado) {
					mapaMasJugado = Laberinto.LISTA_MAPAS[i].vecesJugado;
					nombreMapaMasJugado = Laberinto.LISTA_MAPAS[i].nombre;
				}
			}
		}
		System.out.println("El mapa más jugado es el mapa con nombre: \"" + nombreMapaMasJugado + "\"");

		int mapaMasGanado = 0;
		String nombreMapaMasGanado = "";
		for (int i = 0; i < Laberinto.LISTA_MAPAS.length; i++) {
			if (Laberinto.LISTA_MAPAS[i] != null) {
				if (Laberinto.LISTA_MAPAS[i].vecesGanadas > mapaMasGanado) {
					mapaMasGanado = Laberinto.LISTA_MAPAS[i].vecesGanadas;
					nombreMapaMasGanado = Laberinto.LISTA_MAPAS[i].nombre;
				}
			}
		}
		System.out.println("El mapa en el que más se ha ganado es el mapa con nombre: \"" + nombreMapaMasGanado + "\"");

		int mapaMasPerdido = 0;
		String nombreMapaMasPerdido = "";
		for (int i = 0; i < Laberinto.LISTA_MAPAS.length; i++) {
			if (Laberinto.LISTA_MAPAS[i] != null) {
				if (Laberinto.LISTA_MAPAS[i].vecesPerdidas < mapaMasPerdido) {
					mapaMasPerdido = Laberinto.LISTA_MAPAS[i].vecesPerdidas;
					nombreMapaMasPerdido = Laberinto.LISTA_MAPAS[i].nombre;
				}
			}
		}
		System.out
				.println("El mapa en el que más se ha perdido es el mapa con nombre: \"" + nombreMapaMasPerdido + "\"");

		System.out.println("El total de mapas creados es de: " + (Laberinto.indiceUltimoMapa - 1));

		int opcion = 0;
		while (opcion != 1) {
			System.out.println("Por favor presione 1 para salir al menu principal");
			opcion = scanner.nextInt();
		}
		Laberinto.menuPrincipal(false);

	}

}
