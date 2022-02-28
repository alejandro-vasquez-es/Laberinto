public class Mapa {

	public String nombre;
	public char[][] matriz;
	public Salida[] salidas;
	public Oro[] listaOro;

	public int columnas;
	public int filas;
	public boolean estaJuegoTerminado = false;
	public int vecesJugado = 0;
	public int vecesGanadas = 0;
	public int vecesPerdidas = 0;

	public Mapa(String _nombre, char[][] _matriz, Salida[] _salidas, Oro[] _listaOro) {

		this.nombre = _nombre;
		this.matriz = _matriz;
		this.salidas = _salidas;
		this.listaOro = _listaOro;

		this.columnas = this.matriz[0].length;
		this.filas = this.matriz.length;

	}

}
