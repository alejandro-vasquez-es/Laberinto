import java.util.Arrays;
import java.util.Scanner;

public class Juego {

	public static Scanner scanner = new Scanner(System.in);

	public void jugar(Mapa _mapa) {
		Helpers.clear();
		_mapa.vecesJugado++;
		System.out.println("Empezando juego con el mapa: " + _mapa.nombre);

		int oroRecolectado = 0;

		Jugador jugador = new Jugador(localizarJugadorAleatoriamente(_mapa),
				oroRecolectado);
		_mapa.matriz[jugador.posicion[0]][jugador.posicion[1]] = 'J';
		scanner.nextLine();

		// bot
		boolean esta4Jugador = false;
		boolean estaMismaPosicion = true;
		int[] posicionBot = localizarJugadorAleatoriamente(_mapa);

		if (_mapa.columnas > 5 && _mapa.filas > 5) {
			while (!esta4Jugador) {
				posicionBot = localizarJugadorAleatoriamente(_mapa);
				if (((posicionBot[1] - jugador.posicion[1]) > 4) || ((jugador.posicion[1] - posicionBot[1]) > 4)
						&& ((posicionBot[0] - jugador.posicion[0]) > 4)
						|| ((jugador.posicion[0] - posicionBot[0]) > 4)) {
					esta4Jugador = true;
				}
			}
		} else {
			do {
				if (Arrays.equals(posicionBot, jugador.posicion)) {
					posicionBot = localizarJugadorAleatoriamente(_mapa);
				} else {
					estaMismaPosicion = false;
				}
			} while (estaMismaPosicion);
		}
		Bot bot = new Bot(posicionBot);
		bot.limpiarMatriz(_mapa);

		_mapa.matriz[bot.posicion[0]][bot.posicion[1]] = 'B';

		while (!_mapa.estaJuegoTerminado) {
			turno(_mapa, jugador);
			turnoBot(bot, _mapa, jugador);
			mirar(_mapa);
		}

		_mapa.estaJuegoTerminado = false;

		Reportes.reportesFinalizarJuego(Laberinto.LISTA_JUGADORES[Laberinto.indiceUltimoJugador - 1]);

	}

	public void turno(Mapa _mapa, Jugador _jugador) {
		System.out.println("Su turno, por favor ejecute uno de los comandos descritos en el manual de usuario");
		String opcion = scanner.nextLine();
		switchComandos(opcion, _mapa, _jugador);
	}

	public void turnoBot(Bot _bot, Mapa _mapa, Jugador _jugador) {
		System.out.println("Turno del bot.");

		if (_bot.jugadorALaVista) {
			_bot.simular(_mapa, _jugador);
			// _bot.jugadorALaVista = false;
		} else {
			if (_bot.mirar(_mapa, _jugador)) {
				_bot.jugadorALaVista = true;
				System.out.println("Estas a la vista del bot !Ten cuidado!");
			} else {
				int numeroAleatorio = (int) Math.floor(Math.random() * (4 - 1 + 1) + 1);
				_bot.mover(numeroAleatorio, _mapa, false);
			}
		}

	}

	public void mover(String _direccion, Mapa _mapa, Jugador _jugador) {

		switch (_direccion) {
			case "N":
				moverNorte(_mapa, _jugador);
				break;

			case "S":
				moverSur(_mapa, _jugador);
				break;

			case "E":
				moverEste(_mapa, _jugador);
				break;

			case "O":
				moverOeste(_mapa, _jugador);
				break;

			default:
				break;
		}

	}

	public void moverNorte(Mapa _mapa, Jugador _jugador) {

		if ((_jugador.posicion[0] - 1) < 0) {
			System.out.println("El movimiento no se pudo realizar. !No se puede pasar los limites del laberinto!");
		} else if (_mapa.matriz[_jugador.posicion[0] - 1][_jugador.posicion[1]] == '#') {
			System.out.println("El movimiento no se pudo realizar. !Hay una pared!");
		} else {
			_jugador.posicion[0]--;
			limpiarMatriz(_mapa, _jugador);
			_mapa.matriz[_jugador.posicion[0]][_jugador.posicion[1]] = 'J';
			System.out.println("El movimiento se realizo con exito");
			_jugador.movimientos++;
		}

	}

	public void moverSur(Mapa _mapa, Jugador _jugador) {

		if ((_jugador.posicion[0] + 1) == _mapa.filas) {
			System.out.println("El movimiento no se pudo realizar. !No se puede pasar los limites del laberinto!");
		} else if (_mapa.matriz[_jugador.posicion[0] + 1][_jugador.posicion[1]] == '#') {
			System.out.println("El movimiento no se pudo realizar. !Hay una pared!");
		} else {
			_jugador.posicion[0]++;
			limpiarMatriz(_mapa, _jugador);
			_mapa.matriz[_jugador.posicion[0]][_jugador.posicion[1]] = 'J';
			System.out.println("El movimiento se realizo con exito");
			_jugador.movimientos++;
		}

	}

	public void moverEste(Mapa _mapa, Jugador _jugador) {

		if ((_jugador.posicion[1] + 1) == _mapa.columnas) {
			System.out.println("El movimiento no se pudo realizar. !No se puede pasar los limites del laberinto!");
		} else if (_mapa.matriz[_jugador.posicion[0]][_jugador.posicion[1] + 1] == '#') {
			System.out.println("El movimiento no se pudo realizar. !Hay una pared!");
		} else {
			_jugador.posicion[1]++;
			limpiarMatriz(_mapa, _jugador);
			_mapa.matriz[_jugador.posicion[0]][_jugador.posicion[1]] = 'J';
			System.out.println("El movimiento se realizo con exito");
			_jugador.movimientos++;
		}

	}

	public void moverOeste(Mapa _mapa, Jugador _jugador) {

		if ((_jugador.posicion[1] - 1) < 0) {
			System.out.println("El movimiento no se pudo realizar. !No se puede pasar los limites del laberinto!");
		} else if (_mapa.matriz[_jugador.posicion[0]][_jugador.posicion[1] - 1] == '#') {
			System.out.println("El movimiento no se pudo realizar. !Hay una pared!");
		} else {
			_jugador.posicion[1]--;
			limpiarMatriz(_mapa, _jugador);
			_mapa.matriz[_jugador.posicion[0]][_jugador.posicion[1]] = 'J';
			System.out.println("El movimiento se realizo con exito");
			_jugador.movimientos++;
		}

	}

	public void limpiarMatriz(Mapa _mapa, Jugador _jugador) {

		for (int i = 0; i < _mapa.filas; i++) {
			for (int j = 0; j < _mapa.columnas; j++) {
				char casilla = _mapa.matriz[i][j];

				if (casilla == _jugador.caracter) {
					int indiceOroLevantado = esOro(new int[] { i, j }, _mapa);
					int indiceSalida = esSalida(new int[] { i, j }, _mapa);
					if (indiceOroLevantado != -1 && !_mapa.listaOro[indiceOroLevantado].estaLevantada) {
						_mapa.matriz[i][j] = 'G';
					} else if (indiceSalida != -1) {
						_mapa.matriz[i][j] = 'S';
					} else {
						_mapa.matriz[i][j] = 'O';
					}
				}
			}
		}
	}

	public int esOro(int[] _posicion, Mapa _mapa) {
		// Recorre la lista de oro para verificar si el vector posicion (argumento) es
		// una casilla de oro
		for (int i = 0; i < _mapa.listaOro.length; i++) {
			if (Arrays.equals(_posicion, _mapa.listaOro[i].posicion) && _mapa.listaOro[i].estaLevantada == false) {
				return i;
			}
		}
		return -1;
	}

	public int esSalida(int[] _posicion, Mapa _mapa) {
		// Recorre la lista de salidas para verificar si el vector posicion (argumento)
		// es
		// una casilla de salida
		for (int i = 0; i < _mapa.salidas.length; i++) {
			if (Arrays.equals(_posicion, _mapa.salidas[i].posicion)) {
				return i;
			}
		}
		return -1;
	}

	public void salir(Mapa _mapa, Jugador _jugador) {
		int indiceSalida = esSalida(_jugador.posicion, _mapa);

		if (indiceSalida == -1) {
			System.out.println("Usted no se encuentra en una casilla de salida");
		} else if (_mapa.salidas[indiceSalida].oroNecesario > _jugador.oroRecolectado) {
			System.out.println("No tiene el oro suficiente para salir desde esta salida");
		} else {
			_mapa.estaJuegoTerminado = true;
			limpiarMatriz(_mapa, _jugador);
			Laberinto.LISTA_JUGADORES[Laberinto.indiceUltimoJugador] = _jugador;
			Laberinto.indiceUltimoJugador++;
			_jugador.estado = "salir";
			for (int i = 0; i < _mapa.listaOro.length; i++) {
				_mapa.listaOro[i].estaLevantada = false;
				_mapa.matriz[_mapa.listaOro[i].posicion[0]][_mapa.listaOro[i].posicion[1]] = 'G';
			}

			_mapa.vecesGanadas++;
			Laberinto.totalPartidasGanadas++;
			Laberinto.totalPartidas++;
			Laberinto.totalOroPartidas += _jugador.oroRecolectado;
			Laberinto.totalMovimientos += _jugador.movimientos;
		}
	}

	public int[] localizarJugadorAleatoriamente(Mapa _mapa) {
		int[] posicionAleatoria = new int[2];
		do {
			posicionAleatoria = Helpers.localizacionRandom(_mapa.filas, _mapa.columnas);
		} while (_mapa.matriz[posicionAleatoria[0]][posicionAleatoria[1]] == '#');
		return posicionAleatoria;
	}

	public void mirar5x5(Mapa _mapa, Jugador _jugador) {

		if ((_mapa.columnas <= 5) && (_mapa.filas <= 5)) {
			mirar(_mapa);
		} else {
			int rango[][] = {
					{ -2, 2 }, // rango en y
					{ -2, 2 } // rango en x
			};

			final int sumaRangoPosicionX1 = rango[1][0] + _jugador.posicion[1];
			final int sumaRangoPosicionX2 = rango[1][1] + _jugador.posicion[1];
			if (sumaRangoPosicionX1 < 0) {
				rango[1][0] -= sumaRangoPosicionX1;
				rango[1][1] -= sumaRangoPosicionX1;
			} else if (sumaRangoPosicionX2 > (_mapa.columnas - 1)) {
				rango[1][0] -= sumaRangoPosicionX2 - (_mapa.columnas - 1);
				rango[1][1] = Math.abs(sumaRangoPosicionX2 - rango[1][1] - (_mapa.columnas - 1));
			}

			final int sumaRangoPosicionY1 = rango[0][0] + _jugador.posicion[0];
			final int sumaRangoPosicionY2 = rango[0][1] + _jugador.posicion[0];
			if (sumaRangoPosicionY1 < 0) {
				rango[0][0] -= sumaRangoPosicionY1;
				rango[0][1] -= sumaRangoPosicionY1;
			} else if (sumaRangoPosicionY2 > (_mapa.filas - 1)) {
				rango[0][0] -= sumaRangoPosicionY2 - (_mapa.filas - 1);
				rango[0][1] = Math.abs(sumaRangoPosicionY2 - rango[0][1] - (_mapa.filas - 1));

			}

			imprimir5x5(rango, _mapa.matriz, _jugador.posicion);
		}

	}

	public void imprimir5x5(int[][] _rangos, char[][] _matriz, int[] _posicionJugador) {
		int[] rangoY = _rangos[0];
		int[] rangoX = _rangos[1];

		for (int i = rangoY[0]; i <= rangoY[1]; i++) {
			String fila = "";
			for (int j = rangoX[0]; j <= rangoX[1]; j++) {
				int y = _posicionJugador[0] + i;
				int x = _posicionJugador[1] + j;

				fila += _matriz[y][x] + " ";
			}
			System.out.println(fila);
		}

	}

	public void mirar(Mapa _mapa) {

		for (int i = 0; i < _mapa.filas; i++) {
			String columna = "";
			for (int j = 0; j < _mapa.columnas; j++) {
				columna = columna + _mapa.matriz[i][j] + " ";
			}
			System.out.println(columna);
		}

	}

	public void levantar(Mapa _mapa, Jugador _jugador) {

		int indiceOroLevantado = esOro(_jugador.posicion, _mapa);

		if (indiceOroLevantado == -1) {
			System.out.println("No se encuentra en una casilla de oro, pierde el turno");
		} else {
			Oro casillaOro = _mapa.listaOro[indiceOroLevantado];
			casillaOro.estaLevantada = true;

			_jugador.oroRecolectado += casillaOro.cantidadOro;

			System.out.println("La accion se realizo correctamente");
			System.out.println("Usted ha levantado " + casillaOro.cantidadOro + " de oro");
			_jugador.getOroRecolectado();
		}

	}

	public void oroRequerido(Mapa _mapa, Jugador _jugador) {

		int indiceSalida = esSalida(_jugador.posicion, _mapa);

		if (indiceSalida == -1) {
			System.out.println("No se encuentra en una casilla de salida, pierde el turno.");
		} else {
			Salida salida = _mapa.salidas[indiceSalida];

			salida.getOroNecesario();
		}

	}

	public void switchComandos(String _opcion, Mapa _mapa, Jugador _jugador) {

		switch (_opcion) {
			case "MOVER N":
			case "mover n":
				mover("N", _mapa, _jugador);
				break;
			case "MOVER S":
			case "mover s":
				mover("S", _mapa, _jugador);
				break;
			case "MOVER E":
			case "mover e":
				mover("E", _mapa, _jugador);
				break;
			case "MOVER O":
			case "mover o":
				mover("O", _mapa, _jugador);
				break;
			case "SALIR":
			case "salir":
				salir(_mapa, _jugador);
				break;
			case "MIRAR":
			case "mirar":
				mirar5x5(_mapa, _jugador);
				break;
			case "MIRAR2":
			case "mirar2":
				mirar(_mapa);
				break;
			case "LEVANTAR":
			case "levantar":
				levantar(_mapa, _jugador);
				break;
			case "ORO":
			case "oro":
				_jugador.getOroRecolectado();
				break;
			case "ORO_REQUERIDO":
			case "oro_requerido":
				oroRequerido(_mapa, _jugador);
				break;
			default:
				System.out.println("Ese comando no existe");
				break;
		}
	}

	public void abrirMenu(boolean deNuevo) {

		Helpers.clear();

		if (deNuevo) {
			System.out.println("El número escrito no es una opción valida, por favor vuelva a intentar");
		}

		System.out.println("-----------MENU--DEL--JUEGO-------------");
		System.out.println("Por favor eliga el mapa que desea jugar:");

		int ID_SALIR = 0;

		for (int i = 0; i < Laberinto.LISTA_MAPAS.length; i++) {
			if (Laberinto.LISTA_MAPAS[i] != null) {

				System.out.println((i + 1) + ". " + Laberinto.LISTA_MAPAS[i].nombre);
				ID_SALIR = i + 2;

			}
		}
		System.out.println(ID_SALIR + ". REGRESAR AL MENU PRINCIPAL ");

		switchOpcionesMenuJuego(ID_SALIR);

	}

	public void switchOpcionesMenuJuego(int _id_salir) {

		int OPCION_MENU_JUEGO = scanner.nextInt();

		if (OPCION_MENU_JUEGO == _id_salir) {
			Laberinto.menuPrincipal(false);
		} else if (OPCION_MENU_JUEGO < _id_salir) {
			jugar(Laberinto.LISTA_MAPAS[OPCION_MENU_JUEGO - 1]);
		} else {
			abrirMenu(true);
		}

	}

}
