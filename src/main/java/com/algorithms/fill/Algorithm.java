package com.algorithms.fill;

import java.util.Stack;

public class Algorithm {

	int n;
	int x;
	int y;

	/**
	 * Initialized a new matrix with one colored cell
	 * 
	 * @param n
	 *            The size of the matrix is 2^n
	 * @param x
	 *            The x coordinates of the colored cell
	 * @param y
	 *            The y coordinates of the colored cell
	 */
	public Algorithm(int n, int x, int y) {
		this.n = n;
		this.x = x;
		this.y = y;
	}

	public void run() {

		Matrix root = new Matrix(0, 0, 1 << n, 1 << n);
		root.set(x, y, 999);

		Stack<Matrix> stack = new Stack<Matrix>();

		stack.push(root);

		int iteration = 1;

		while (!stack.isEmpty()) {
			Matrix matrix = stack.pop();

			if (matrix.getWidth() == 1) {
				continue;
			}

			int size = matrix.width >> 1;
			int index = size - 1;

			// Upper-Left
			Matrix matrix1 = matrix.subMatrix(0, 0, size, size);

			// Upper-Right
			Matrix matrix2 = matrix.subMatrix(size, 0, size, size);

			// Lower-Left
			Matrix matrix3 = matrix.subMatrix(0, size, size, size);

			// Lower Right
			Matrix matrix4 = matrix.subMatrix(size, size, size, size);

			stack.push(matrix1);
			stack.push(matrix2);
			stack.push(matrix3);
			stack.push(matrix4);

			if (matrix1.filled() == 0) {
				matrix1.set(index, index, iteration);
			}

			if (matrix2.filled() == 0) {
				matrix2.set(0, index, iteration);
			}

			if (matrix3.filled() == 0) {
				matrix3.set(index, 0, iteration);
			}

			if (matrix4.filled() == 0) {
				matrix4.set(0, 0, iteration);
			}

			print(iteration, root.getData());

			iteration++;
		}
	}

	public void print(int iteration, int[][] data) {

		System.out.println("------- Iterration " + iteration + "-------");
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				System.out.printf("%5d ", data[i][j]);
			}
			System.out.println();
		}
	}
}
