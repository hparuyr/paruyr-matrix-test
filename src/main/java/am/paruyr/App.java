package am.paruyr;

import java.util.Scanner;

import am.paruyr.math.MatrixUtils;
import am.paruyr.math.models.SquareMatrix;

/**
 * Entry point
 * 
 * @author paruyr
 *
 * @see: To run from command line use: mvn exec:java -Dexec.mainClass="am.paruyr.App"
 */
public class App {

	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			System.out.println("Enter matrix size:");
			int size = in.nextInt();
			if (size < 1 || size > 10000)
				throw new UnsupportedOperationException("Unsupported matrix size");

			SquareMatrix a = new SquareMatrix(MatrixUtils.generateFloatSquareMatrix(size));
			SquareMatrix b = new SquareMatrix(MatrixUtils.generateFloatSquareMatrix(size));

			long currentTimeMillis = System.currentTimeMillis();
			SquareMatrix.multiply(a, b);
			System.out.println(
					"Sequential multiplication duration:" + (System.currentTimeMillis() - currentTimeMillis) + " ms");

			currentTimeMillis = System.currentTimeMillis();
			SquareMatrix.multiplyParralel(a, b);
			System.out.println(
					"Parralel multiplication duration:" + (System.currentTimeMillis() - currentTimeMillis) + " ms");
		} finally {
			if (in != null)
				in.close();
		}
	}

}
