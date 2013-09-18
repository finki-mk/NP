package edu.finki.np.ex1;

public abstract interface Filter<T1, T2> {
	public abstract boolean isFiltered(T1 elem, T2 criterium);
}
