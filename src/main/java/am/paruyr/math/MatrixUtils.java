package am.paruyr.math;

/**
 * Matrix helper util class
 * 
 * @author paruyr
 *
 */
public class MatrixUtils {

	/**
	 * Matrix generator based on given size
	 * 
	 * @param size
	 * @return
	 */
	public static float[][] generateFloatSquareMatrix(final int size) {
		if (size < 1)
			throw new IllegalArgumentException("size should be greater than 0");

		float[][] result = new float[size][size];
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result.length; j++)
				result[i][j] = (float) Math.random();
		return result;
	}

}
