package edu.finki.np.av7;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CopyDemo {
	public static void main(String args[]) {
		// create two lists
		List<String> srclst = new ArrayList<String>(5);
		List<String> destlst = new ArrayList<String>(10);

		// populate two lists
		srclst.add("Java");
		srclst.add("is");
		srclst.add("best");

		destlst.add("C++");
		destlst.add("is");
		destlst.add("older");
		destlst.add("than");
		destlst.add("Java");

		// copy into dest list
		Collections.copy(destlst, srclst);

		System.out.println("Value of source list: " + srclst);
		System.out.println("Value of destination list: " + destlst);
	}
}