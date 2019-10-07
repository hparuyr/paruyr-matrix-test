package am.paruyr.math.models;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Square Matrix wrapper class
 * 
 * @author paruyr
 *
 */
public class SquareMatrix {
	private final int size;
	private final float[][] core;

	/**
	 * Create empty matrix with given size
	 * 
	 * @param size
	 */
	public SquareMatrix(final int size) {
		this.size = size;
		core = new float[this.size][this.size];
	}

	/**
	 * Create and initialize square matrix based on 2D input array
	 * 
	 * @param matrix
	 * @throws IllegalArgumentException
	 */
	public SquareMatrix(final float[][] matrix) throws IllegalArgumentException {
		if (matrix == null || matrix.length < 1 || matrix.length != matrix[0].length)
			throw new IllegalArgumentException("Incorrect square matrix use.");

		this.size = matrix.length;
		this.core = new float[this.size][this.size];
		for (int i = 0; i < this.size; i++)
			for (int j = 0; j < this.size; j++)
				this.core[i][j] = matrix[i][j];
	}

	/**
	 * Multiply 2 given matrices sequentially
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static SquareMatrix multiply(final SquareMatrix a, final SquareMatrix b) {
		if (a == null || b == null || a.getSize() != b.getSize())
			throw new IllegalArgumentException("Multiplication for give square matrices not supported.");

		final SquareMatrix result = new SquareMatrix(a.getSize());

		for (int i = 0; i < a.getSize(); i++)
			for (int j = 0; j < a.getSize(); j++)
				for (int k = 0; k < a.getSize(); k++)
					result.core[i][j] += a.core[i][k] * b.core[k][j];

		return result;
	}

	/**
	 * Multiply 2 given matrices Parallel
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static SquareMatrix multiplyParralel(final SquareMatrix a, final SquareMatrix b) {
		if (a == null || b == null || a.getSize() != b.getSize())
			throw new IllegalArgumentException("Multiplication for give square matrices not supported.");

		final SquareMatrix result = new SquareMatrix(a.getSize());
		IntStream.range(0, result.getSize()).parallel()
				.forEach(row -> IntStream.range(0, result.getSize()).parallel().forEach(col -> {
					int sum = 0;
					for (int k = 0; k < a.getSize(); k++) {
						sum += a.core[row][k] * b.core[k][col];
					}
					result.core[row][col] = sum;
				}));
		return result;
	}

	public float[][] getMatrix() {
		return core.clone();
	}

	public float getValue(int row, int col) {
		return core[row][col];
	}

	public void setValue(int row, int col, float value) {
		core[row][col] = value;
	}

	public int getSize() {
		return this.size;
	}

	@Override
	public String toString() {
		return Arrays.deepToString(this.core);
	}
}
