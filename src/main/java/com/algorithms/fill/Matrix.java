package com.algorithms.fill;

public class Matrix {

	int xOffset;
	int yOffset;
	int width;
	int height;

	int[][] data;

	public int getxOffset() {
		return xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[][] getData() {
		return data;
	}

	public Matrix(int xOffset, int yOffset, int width, int height, int[][] data) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
		this.data = data;

		if (this.data == null) {
			this.data = new int[width][height];
		}
	}

	public Matrix(int xOffset, int yOffset, int width, int height) {
		this(xOffset, yOffset, width, height, null);
	}

	public Matrix subMatrix(int xOffset, int yOffset, int width, int height) {

		// Check that the sub matrix range is valid
		if (xOffset + width > this.width || yOffset + height > this.height) {
			throw new ArrayIndexOutOfBoundsException();
		}

		return new Matrix(this.xOffset + xOffset, this.yOffset + yOffset, width, height, data);
	}

	public int set(int x, int y, int value) {
		int oldValue = data[xOffset + x][yOffset + y];

		data[xOffset + x][yOffset + y] = value;

		return oldValue;
	}

	public int get(int x, int y) {
		return data[xOffset + x][yOffset + y];
	}

	/**
	 * Returns the number of cells that has value that is not zero
	 * 
	 * @return the number of cells that has value that is not zero
	 */
	public int filled() {
		
		int count = 0;
		
		for (int x = xOffset; x < xOffset + width; x++) {
			for (int y = yOffset; y < yOffset + height; y++) {
				if (data[x][y] != 0) {
					count++;
				}
			}
		}
		
		return count;
	}
}
