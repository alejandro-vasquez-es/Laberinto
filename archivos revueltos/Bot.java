import java.util.Arrays;

public class Bot {

	boolean jugadorALaVista = false;
	int[] posicion;
	int numeroTurno = 1;
	Juego juego = new Juego();
	char caracter = 'B';
	int movimientos = 0;
	int vecesEnVision = 0;

	public Bot(int[] _posicion) {

		this.posicion = _posicion;

	}

	public void getMovimientos() {
		System.out.println(
				"El total de movimientos realizados por el bot para atrapar al jugador es de: " + this.movimientos);
	}

	public void getVecesEnVision() {
		System.out.println(
				"El total de veces que el jugador estuvo en visión del bot es de: " + this.vecesEnVision);
	}

	public void mover(int _direccion, Mapa _mapa, boolean _estaSimulacion) {
		switch (_direccion) {
			case 1:
				// mover norte
				if ((this.posicion[0] - 1) < 0) {
					if (!_estaSimulacion) {
						System.out.println(
								"El bot intentó salir de los límites del laberinto, movimiento denegado.");
					}
				} else if (_mapa.matriz[this.posicion[0] - 1][this.posicion[1]] == '#') {
					if (!_estaSimulacion) {
						System.out.println("El bot intentó moverse a una pared, movimiento denegado");

					}
				} else {
					this.posicion[0]--;
					this.limpiarMatriz(_mapa);
					_mapa.matriz[this.posicion[0]][this.posicion[1]] = this.caracter;
					if (!_estaSimulacion) {
						System.out.println("El bot se movió hacia el norte");
					}
					this.movimientos++;
				}
				break;
			case 2:
				// mover sur
				if ((this.posicion[0] + 1) == _mapa.filas) {
					if (!_estaSimulacion) {
						System.out.println(
								"El bot intentó salir de los límites del laberinto, movimiento denegado.");
					}
				} else if (_mapa.matriz[this.posicion[0] + 1][this.posicion[1]] == '#') {
					if (!_estaSimulacion) {
						System.out.println("El bot intentó moverse a una pared, movimiento denegado");

					}
				} else {
					this.posicion[0]++;
					this.limpiarMatriz(_mapa);
					_mapa.matriz[this.posicion[0]][this.posicion[1]] = this.caracter;
					if (!_estaSimulacion) {
						System.out.println("El bot se movió hacia el sur");

					}
					this.movimientos++;
				}
				break;
			case 3:
				// mover oeste
				if ((this.posicion[1] - 1) < 0) {
					if (!_estaSimulacion) {
						System.out.println("El bot intentó salir de los límites del laberinto, movimiento denegado.");
					}
				} else if (_mapa.matriz[this.posicion[0]][this.posicion[1] - 1] == '#') {
					if (!_estaSimulacion) {
						System.out.println("El bot intentó moverse a una pared, movimiento denegado");

					}
				} else {
					this.posicion[1]--;
					this.limpiarMatriz(_mapa);
					_mapa.matriz[this.posicion[0]][this.posicion[1]] = this.caracter;
					if (!_estaSimulacion) {
						System.out.println("El bot se movió hacia el oeste");

					}
				}
				this.movimientos++;

				break;
			case 4:
				// mover este
				if ((this.posicion[1] + 1) == _mapa.columnas) {
					if (!_estaSimulacion) {
						System.out.println(
								"El bot intentó salir de los límites del laberinto, movimiento denegado.");
					}
				} else if (_mapa.matriz[this.posicion[0]][this.posicion[1] + 1] == '#') {
					if (!_estaSimulacion) {
						System.out.println("El bot intentó moverse a una pared, movimiento denegado");

					}
				} else {
					this.posicion[1]++;
					this.limpiarMatriz(_mapa);
					_mapa.matriz[this.posicion[0]][this.posicion[1]] = this.caracter;
					if (!_estaSimulacion) {
						System.out.println("El bot se movió hacia el este");

					}
					this.movimientos++;
				}
				break;

			default:
				break;
		}

	}

	public double distancia(Jugador _jugador) {

		int[] coordenadaJugador = { _jugador.posicion[0] - this.posicion[0], _jugador.posicion[1] - this.posicion[1] };

		double distancia = Math.sqrt(Math.pow(coordenadaJugador[0], 2) + Math.pow(coordenadaJugador[1], 2));

		return distancia;

	}

	public void simular(Mapa _mapa, Jugador _jugador) {

		int[] posicionOriginal = this.posicion.clone();

		this.mover(1, _mapa, true);
		double distanciaNorte = distancia(_jugador);
		if (!Arrays.equals(posicionOriginal, this.posicion))
			this.movimientos--;
		this.posicion = posicionOriginal.clone();
		this.limpiarMatriz(_mapa);
		_mapa.matriz[this.posicion[0]][this.posicion[1]] = this.caracter;

		this.mover(2, _mapa, true);
		double distanciaSur = distancia(_jugador);
		if (!Arrays.equals(posicionOriginal, this.posicion))
			this.movimientos--;
		this.posicion = posicionOriginal.clone();
		this.limpiarMatriz(_mapa);
		_mapa.matriz[this.posicion[0]][this.posicion[1]] = this.caracter;

		this.mover(3, _mapa, true);
		double distanciaOeste = distancia(_jugador);
		if (!Arrays.equals(posicionOriginal, this.posicion))
			this.movimientos--;
		this.posicion = posicionOriginal.clone();
		this.limpiarMatriz(_mapa);
		_mapa.matriz[this.posicion[0]][this.posicion[1]] = this.caracter;

		this.mover(4, _mapa, true);
		double distanciaEste = distancia(_jugador);
		if (!Arrays.equals(posicionOriginal, this.posicion))
			this.movimientos--;
		this.posicion = posicionOriginal.clone();
		this.limpiarMatriz(_mapa);
		_mapa.matriz[this.posicion[0]][this.posicion[1]] = this.caracter;

		double distanciaMenor = distanciaNorte;
		int direccionDistanciaMenor = 1;
		if (distanciaMenor > distanciaSur) {
			distanciaMenor = distanciaSur;
			direccionDistanciaMenor = 2;
		}
		if (distanciaMenor > distanciaOeste) {
			distanciaMenor = distanciaOeste;
			direccionDistanciaMenor = 3;
		}
		if (distanciaMenor > distanciaEste) {
			distanciaMenor = distanciaEste;
			direccionDistanciaMenor = 4;
		}

		this.mover(direccionDistanciaMenor, _mapa, true);
		System.out.println("El bot realizó un movimiento para atraparte. !Corre!");
		if (Arrays.equals(_jugador.posicion, this.posicion)) {
			System.out.println("El bot atrapó al jugador");
			terminarJuego(_mapa, _jugador);
		}
		if (!this.mirar(_mapa, _jugador)) {
			this.jugadorALaVista = false;
			System.out.println("El bot ya no te puede ver, intenta no cruzarte cerca de él.");
		}

	}

	public void terminarJuego(Mapa _mapa, Jugador _jugador) {

		_mapa.estaJuegoTerminado = true;
		this.limpiarMatriz(_mapa);
		Laberinto.LISTA_JUGADORES[Laberinto.indiceUltimoJugador] = _jugador;
		Laberinto.indiceUltimoJugador++;
		_jugador.estado = "perder";
		for (int i = 0; i < _mapa.listaOro.length; i++) {
			_mapa.listaOro[i].estaLevantada = false;
			_mapa.matriz[_mapa.listaOro[i].posicion[0]][_mapa.listaOro[i].posicion[1]] = 'G';
		}

		_mapa.vecesPerdidas++;
		Laberinto.totalVecesAtrapado++;
		Laberinto.totalPartidas++;
		Laberinto.totalOroPartidas += _jugador.oroRecolectado;
		Laberinto.totalMovimientos += _jugador.movimientos;

	}

	public boolean mirar(Mapa _mapa, Jugador _jugador) {

		if ((_mapa.columnas <= 5) && (_mapa.filas <= 5)) {
			this.vecesEnVision++;
			return true;
		}

		int rango[][] = {
				{ -2, 2 }, // rango en y
				{ -2, 2 } // rango en x
		};

		final int sumaRangoPosicionX1 = rango[1][0] + this.posicion[1];
		final int sumaRangoPosicionX2 = rango[1][1] + this.posicion[1];
		if (sumaRangoPosicionX1 < 0) {
			rango[1][0] -= sumaRangoPosicionX1;
			rango[1][1] -= sumaRangoPosicionX1;
		} else if (sumaRangoPosicionX2 > (_mapa.columnas - 1)) {
			rango[1][0] -= sumaRangoPosicionX2 - (_mapa.columnas - 1);
			rango[1][1] = Math.abs(sumaRangoPosicionX2 - rango[1][1] - (_mapa.columnas - 1));
		}

		final int sumaRangoPosicionY1 = rango[0][0] + this.posicion[0];
		final int sumaRangoPosicionY2 = rango[0][1] + this.posicion[0];
		if (sumaRangoPosicionY1 < 0) {
			rango[0][0] -= sumaRangoPosicionY1;
			rango[0][1] -= sumaRangoPosicionY1;
		} else if (sumaRangoPosicionY2 > (_mapa.filas - 1)) {
			rango[0][0] -= sumaRangoPosicionY2 - (_mapa.filas - 1);
			rango[0][1] = Math.abs(sumaRangoPosicionY2 - rango[0][1] - (_mapa.filas - 1));

		}

		return jugadorALaVista(rango, _mapa.matriz, _jugador.posicion);

	}

	public boolean jugadorALaVista(int[][] _rangos, char[][] _matriz, int[] _posicionJugador) {
		int[] rangoY = _rangos[0];
		int[] rangoX = _rangos[1];

		for (int i = rangoY[0]; i <= rangoY[1]; i++) {
			for (int j = rangoX[0]; j <= rangoX[1]; j++) {
				int y = this.posicion[0] + i;
				int x = this.posicion[1] + j;

				if (Arrays.equals(new int[] { y, x }, _posicionJugador)) {
					this.vecesEnVision++;
					return true;
				}

			}
		}

		return false;

	}

	public void limpiarMatriz(Mapa _mapa) {

		for (int i = 0; i < _mapa.filas; i++) {
			for (int j = 0; j < _mapa.columnas; j++) {
				char casilla = _mapa.matriz[i][j];

				if (casilla == this.caracter) {
					int indiceOroLevantado = this.juego.esOro(new int[] { i, j }, _mapa);
					int indiceSalida = this.juego.esSalida(new int[] { i, j }, _mapa);
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

}
