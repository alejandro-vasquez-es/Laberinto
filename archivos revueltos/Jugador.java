public class Jugador {

	int[] posicion;
	int oroRecolectado;
	int movimientos = 0;
	String estado = "En juego";
	char caracter = 'J';

	public Jugador(int[] _posicion, int _oroRecolectado) {
		this.posicion = _posicion;
		this.oroRecolectado = _oroRecolectado;
	}

	public void getOroRecolectado() {
		System.out.println("La cantidad total de oro recolectada es de " + this.oroRecolectado);
	}

	public void getMovimientos(String _estado) {
		System.out.println("La cantidad de movimientos realizados para " + _estado + " es de: " + this.movimientos);

	}

}
