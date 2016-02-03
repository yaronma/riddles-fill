package com.algorithms.fill;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Implementation of an algorithm to fill a grid of 2^n size that has one
 * missing cell with L-shaped tiles.
 * 
 * The algorithms is a classic divide-and-conquer where we divide the rectangle
 * into four squares and adding the L-shaped tile in the
 * middle.
 * 
 * To make things somewhat interesting this algorithm is __NOT__ implemented in
 * the obvious, recursive way.
 * 
 * @author yaron
 *
 */
public class Algorithm {

	// The size of the matrix is 2^n
	int n;

	// The x-axis of the single painted cell
	int x;

	// The y-axis of the single painted cell
	int y;

	// The value of the cell of the painted tile
	private static final int PAINTED_TILE = 999;

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

	/**
	 * Executes the tiling algorithm
	 * 
	 * @return The number of iterations(tiles) needed to cover the entire matrix
	 */
	public int run() {

		// This is the starting matrix
		Matrix root = new Matrix(0, 0, 1 << n, 1 << n);

		// Mark the [ainted cell
		root.set(x, y, PAINTED_TILE);

		Stack<Matrix> stack = new Stack<Matrix>();
		stack.push(root);

		int iteration = 1;

		while (!stack.isEmpty()) {
			
			Matrix matrix = stack.pop();

			if (matrix.getWidth() == 1) {
				continue;
			}

			// The size of the matrix in the next step
			int size = matrix.width >> 1;
		
			// Holds the offset of the cells to paint. It is just a matter of convenience.
			int offset = size - 1;

			// Handle Upper-Left Matrix
			Matrix matrix1 = matrix.subMatrix(0, 0, size, size);
			stack.push(matrix1);
			if (matrix1.filled() == 0) {
				matrix1.set(offset, offset, iteration);
			}

			// Handle Upper-Right Matrix
			Matrix matrix2 = matrix.subMatrix(size, 0, size, size);
			stack.push(matrix2);
			if (matrix2.filled() == 0) {
				matrix2.set(0, offset, iteration);
			}

			// Handle Lower-Left Matrix
			Matrix matrix3 = matrix.subMatrix(0, size, size, size);
			stack.push(matrix3);
			if (matrix3.filled() == 0) {
				matrix3.set(offset, 0, iteration);
			}

			// Handle Lower Right Matrix
			Matrix matrix4 = matrix.subMatrix(size, size, size, size);
			stack.push(matrix4);
			if (matrix4.filled() == 0) {
				matrix4.set(0, 0, iteration);
			}

			print(iteration, root.getData());

			iteration++;
		}

		--iteration;
		
		// This part is just an assertion
		assertEquals("Failed to fill the entire matrix...", root.getWidth()*root.getHeight(), root.filled());
		
		// In each iteration we add 3 cells (L-Shape) plus the first colored cell
		assertEquals("Algorithm is not optimal!", iteration * 3 + 1, root.getWidth()*root.getHeight());
			
		return iteration; 
	}

	// Print the data in matrix form
	private void print(int iteration, int[][] data) {

		System.out.println("Iterration " + iteration + ':');

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				System.out.printf("%5d ", data[i][j]);
			}
			System.out.println();
		}
	}
}
