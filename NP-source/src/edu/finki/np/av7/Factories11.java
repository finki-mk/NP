package edu.finki.np.av7;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 * 
 * @author gjorgji.madjarov
 */
public class Factories11 {
	private List<String> fooList;

	// do stuff
	public List<String> getFooList() {
		if (fooList == null) {
			fooList = new ArrayList<String>();
		}

		return fooList;
	}
}
