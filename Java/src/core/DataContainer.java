package core;

import java.util.ArrayList;

/**
 * @author Valeriy Boiko
 * @version 1.0
 */
public class DataContainer {

	public int								rows;
	public int								columns;
	public int								min;
	public int								maxCells;
	public ArrayList<ArrayList<Integer>>	map;
	public ArrayList<Shape>					shapes;
	public ArrayList<ArrayList<Integer>>	pizzaSliced;

	public DataContainer() {

	}

	public DataContainer(int rows, int columns, int min, int maxCells, ArrayList<ArrayList<Integer>> map) {
		this.rows = rows;
		this.columns = columns;
		this.min = min;
		this.maxCells = maxCells;
		this.map = map;
	}
}
