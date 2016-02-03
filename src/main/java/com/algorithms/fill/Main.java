package com.algorithms.fill;

/**
 * Simple algorithm executer
 * 
 * @author yaron
 *
 */
public class Main {

	public static void main(String[] args) {
		
		int matrixSize = 3;
	
		// Run the algorithm on 8x8 matrix with cell 1,1 colored
		Algorithm algorithm = new Algorithm(matrixSize, 1, 1);
		
		algorithm.run();
	}
}
