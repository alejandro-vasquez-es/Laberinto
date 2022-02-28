public class Helpers {

	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static int[] localizacionRandom(int _filas, int _columnas) {

		int randomX = (int) Math.floor(Math.random() * ((_columnas - 1) - 0 + 1) + 0);
		int randomY = (int) Math.floor(Math.random() * ((_filas - 1) - 0 + 1) + 0);
		int[] location = { randomY, randomX };

		return location;

	}

}
