package fr.sii.survival.core.util;

import java.util.StringJoiner;

import com.google.common.base.Strings;

/**
 * Data structure that handles simple matrix. A matrix has a number of rows and
 * a number of columns.
 * 
 * @author aurelien
 *
 * @param <T>
 *            The type of the values contained in the matrix
 */
public class Matrix<T> {
	private T[] arr;

	private int rows;

	private int cols;

	@SuppressWarnings("unchecked")
	public Matrix(int cols, int rows) {
		this((T[]) new Object[rows * cols], cols, rows);
	}

	protected Matrix(T[] arr, int cols, int rows) {
		super();
		this.arr = arr;
		this.rows = rows;
		this.cols = cols;
	}

	/**
	 * Change the value of the matrix entry.
	 * 
	 * @param col
	 *            the zero-based index of the column
	 * @param row
	 *            the zero-based index of the row
	 * @param element
	 *            the new value
	 * @throws IllegalArgumentException
	 *             when the row or column values are out of matrix range
	 */
	public void set(int col, int row, T element) {
		int idx = position(col, row);
		checkPosition(idx);
		arr[idx] = element;
	}

	/**
	 * Get the value at the provided position.
	 * 
	 * @param col
	 *            the zero-based index of the column
	 * @param row
	 *            the zero-based index of the row
	 * @return the value in the matrix at the provided position
	 */
	public T get(int col, int row) {
		int idx = position(col, row);
		checkPosition(idx);
		return arr[idx];
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	private int position(int col, int row) {
		checkArgs(row, col);
		int rowIdx = (int) Math.floor(row * cols);
		int idx = rowIdx + col;
		return idx;
	}

	private void checkPosition(int idx) {
		if (idx < 0 && idx >= arr.length) {
			throw new IllegalArgumentException("Position is out of the matrix");
		}
	}

	private void checkArgs(int row, int col) {
		if (row < 0 || row >= rows) {
			throw new IllegalArgumentException("Invalid row: row is " + row + " but number of rows is " + rows);
		}
		if (col < 0 || col >= cols) {
			throw new IllegalArgumentException("Invalid row: col is " + col + " but number of cols is " + cols);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int max = 0;
		for (int i = 0, l = arr.length; i < l; i++) {
			String content = String.valueOf(arr[i]);
			if (content.length() > max) {
				max = content.length();
			}
		}
		StringJoiner joiner = new StringJoiner(" | ");
		builder.append(Strings.padEnd("", cols * (max + 3)-3, '_'));
		for (int i = 0, l = arr.length; i < l; i++) {
			if (i % cols == 0) {
				builder.append(joiner.toString());
				builder.append(System.getProperty("line.separator"));
				joiner = new StringJoiner(" | ");
			}
			joiner.add(Strings.padEnd(String.valueOf(arr[i]), max, ' '));
		}
		builder.append(joiner.toString());
		builder.append(System.getProperty("line.separator"));
		builder.append(Strings.padEnd("", cols * (max + 3)-3, '⎺'));
		return builder.toString();
	}
}
