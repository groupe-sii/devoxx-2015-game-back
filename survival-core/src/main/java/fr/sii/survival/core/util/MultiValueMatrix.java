package fr.sii.survival.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Data structure based on {@link Matrix} that helps to handle multiple values
 * in same matrix position.
 * 
 * @author Aurélien Baudet
 *
 * @param <T>
 *            The type of the values contained in the matrix
 */
public class MultiValueMatrix<T> {

	private Matrix<List<T>> matrix;

	public MultiValueMatrix(int cols, int rows) {
		super();
		matrix = new Matrix<List<T>>(cols, rows);
		init(cols, rows);
	}

	private void init(int cols, int rows) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix.set(j, i, new ArrayList<T>());
			}
		}
	}

	/**
	 * Reset the whole value of the matrix with the provided new value. As the
	 * matrix handles multiple values, the matrix cell targeted with provided
	 * column and row is a list. This list is cleared and the element is now the
	 * only content of the list.
	 * 
	 * @param row
	 *            the zero-based index of the row
	 * @param col
	 *            the zero-based index of the column
	 * @param element
	 *            the element that replaces all other values
	 */
	public void set(int row, int col, T element) {
		List<T> elements = matrix.get(col, row);
		elements.clear();
		elements.add(element);
		matrix.set(col, row, elements);
	}

	/**
	 * Get the list of values at the provided position.
	 * 
	 * @param col
	 *            the zero-based index of the column
	 * @param row
	 *            the zero-based index of the row
	 * @return the list of matrix values
	 */
	public List<T> get(int col, int row) {
		return matrix.get(col, row);
	}

	/**
	 * Get one element at the provided position in the matrix and the provided
	 * position in the list.
	 * 
	 * @param col
	 *            the zero-based index of the column
	 * @param row
	 *            the zero-based index of the row
	 * @param elementIdx
	 *            the zero-based index of the element in the list of values
	 * @return the element
	 */
	public T get(int col, int row, int elementIdx) {
		return get(col, row).get(elementIdx);
	}

	/**
	 * Add the element at the provided matrix position. As the matrix contains
	 * list of values, the element is added to this list.
	 * 
	 * @param col
	 *            the zero-based index of the column
	 * @param row
	 *            the zero-based index of the row
	 * @param element
	 *            the element to add
	 */
	public void add(int col, int row, T element) {
		get(col, row).add(element);
	}

	/**
	 * Remove the element at the provided matrix position. As the matrix
	 * contains list of values, the element is removed from this list if exists.
	 * 
	 * @param col
	 *            the zero-based index of the column
	 * @param row
	 *            the zero-based index of the row
	 * @param element
	 *            the element to remove
	 */
	public void remove(int col, int row, T element) {
		get(col, row).remove(element);
	}

	/**
	 * Remove the element at the provided matrix position. As the matrix
	 * contains list of values, the element is removed from this list according
	 * to the provided element index.
	 * 
	 * @param col
	 *            the zero-based index of the column
	 * @param row
	 *            the zero-based index of the row
	 * @param elementIdx
	 *            the index of the element to remove
	 */
	public void remove(int col, int row, int elementIdx) {
		get(col, row).remove(elementIdx);
	}

	public int getRows() {
		return matrix.getRows();
	}

	public int getCols() {
		return matrix.getCols();
	}

	@Override
	public String toString() {
		return matrix.toString();
	}
}
