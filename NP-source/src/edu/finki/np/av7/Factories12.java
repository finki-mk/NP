package edu.finki.np.av7;

import java.util.*;

/**
 * 
 * @author gjorgji.madjarov
 */
public class Factories12 {
	private List<String> fooList;

	// do stuff
	public List<String> getFooList() {
		if (fooList == null) {
			fooList = Collections.emptyList();
		}

		return fooList;
	}
}
