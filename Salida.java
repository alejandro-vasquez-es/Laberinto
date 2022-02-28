public class Salida {

	public int oroNecesario;
	public int[] posicion;

	public Salida(int _oroNecesario, int[] _posicion) {

		this.oroNecesario = _oroNecesario;
		this.posicion = _posicion;

	}

	public void getOroNecesario() {

		System.out.println("El oro necesario para esta salida es de : " + this.oroNecesario);

	}

}
