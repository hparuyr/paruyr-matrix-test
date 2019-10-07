package am.paruyr.matrix.test;

import org.junit.Assert;
import org.junit.Test;

import am.paruyr.math.MatrixUtils;
import am.paruyr.math.models.SquareMatrix;

/**
 * Testing SquareMatrix and MatrixUtils methods
 * 
 * @author paruyr
 *
 */
public class SquareMatrixTest {
	private final float[][] M1 = { { 1, 2 }, { 3, 4 } };
	private final float[][] M2 = { { 2, 0 }, { 1, 2 } };
	private final float[][] RESULT_M1_X_M2 = { { 4.0f, 4.0f }, { 10.0f, 8.0f } };

	private final float[][] M3 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
	private final float[][] M4 = { { 9, 8, 7 }, { 6, 5, 4 }, { 3, 2, 1 } };
	private final float[][] RESULT_M3_X_M4 = { { 30.0f, 24.0f, 18.0f }, { 84.0f, 69.0f, 54.0f },
			{ 138.0f, 114.0f, 90.0f } };

	private final float[][] M5 = { { 10, 14, 3, 26 }, { 22, 4, 5, 45 }, { 19, 4, 45, 46 }, { 7, 34, 37, 9 } };
	private final float[][] M6 = { { 34, 39, 22, 31 }, { 33, 6, 26, 23 }, { 32, 6, 48, 35 }, { 10, 12, 4, 36 } };
	private final float[][] RESULT_M5_X_M6 = { { 1158.0f, 804.0f, 832.0f, 1673.0f },
			{ 1490.0f, 1452.0f, 1008.0f, 2569.0f }, { 2678.0f, 1587.0f, 2866.0f, 3912.0f },
			{ 2634.0f, 807.0f, 2850.0f, 2618.0f } };

	@Test(expected = IllegalArgumentException.class)
	public void squareMatrixIncorrectSize() {
		new SquareMatrix(new float[1][2]);
	}

	@Test
	public void squareMatrixCheck() {
		SquareMatrix matrix = new SquareMatrix(5);
		Assert.assertTrue(matrix.getMatrix().length == 5);
	}

	@Test
	public void squareMatrixCheckGenerated() {
		float[][] matrix = MatrixUtils.generateFloatSquareMatrix(5);
		Assert.assertTrue(matrix.length == 5);

		SquareMatrix sqMatrix = new SquareMatrix(matrix);
		Assert.assertTrue(sqMatrix.getSize() == 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void squareMatrixMultiplyDifferentSize() {
		SquareMatrix a = new SquareMatrix(M1);
		SquareMatrix b = new SquareMatrix(M3);
		SquareMatrix.multiply(a, b);
	}

	@Test(expected = IllegalArgumentException.class)
	public void squareMatrixMultiplyDifferentSizeParralel() {
		SquareMatrix a = new SquareMatrix(M1);
		SquareMatrix b = new SquareMatrix(M3);
		SquareMatrix.multiplyParralel(a, b);
	}

	@Test
	public void squareMatrixMultiply1() {
		SquareMatrix a = new SquareMatrix(M1);
		SquareMatrix b = new SquareMatrix(M2);
		SquareMatrix result = SquareMatrix.multiply(a, b);

		Assert.assertArrayEquals(result.getMatrix(), RESULT_M1_X_M2);

		result = SquareMatrix.multiplyParralel(a, b);
		Assert.assertArrayEquals(result.getMatrix(), RESULT_M1_X_M2);
	}

	@Test
	public void squareMatrixMultiply2() {
		SquareMatrix a = new SquareMatrix(M3);
		SquareMatrix b = new SquareMatrix(M4);
		SquareMatrix result = SquareMatrix.multiply(a, b);

		Assert.assertArrayEquals(result.getMatrix(), RESULT_M3_X_M4);

		result = SquareMatrix.multiplyParralel(a, b);
		Assert.assertArrayEquals(result.getMatrix(), RESULT_M3_X_M4);
	}

	@Test
	public void squareMatrixMultiply3() {
		SquareMatrix a = new SquareMatrix(M5);
		SquareMatrix b = new SquareMatrix(M6);
		SquareMatrix result = SquareMatrix.multiply(a, b);

		Assert.assertArrayEquals(result.getMatrix(), RESULT_M5_X_M6);

		result = SquareMatrix.multiplyParralel(a, b);
		Assert.assertArrayEquals(result.getMatrix(), RESULT_M5_X_M6);
	}
}
