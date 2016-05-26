package edu.finki.np.av7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class CollectionsMethods {
	static String[] test = new String[] { "string", "pogolemVodolzina", "mal",
			"alfaba" };

	public static void main(String[] args) {
		List<String> lista = new ArrayList<String>(10);
		for(int i = 0; i < 10; ++i) {
			lista.add(new String());
		}
		fill(lista, "init_string");
		print(lista);
		lista = Arrays.asList(test);
		reverse(lista);
		print(lista);
	}
	
	public static int count(Collection<Collection<String>> list, String str) {
		int c = 0;
		for(Collection<String> strColl : list) {
			for(String s : strColl) {
				if(s.equals(str)) {
					++c;
				}
			}
		}
		Iterator<Collection<String>> it = list.iterator();
		while(it.hasNext()) {
			Collection<String> strColl = it.next();
			Iterator<String> strIt = strColl.iterator();
			while(strIt.hasNext()) {
				String s = strIt.next();
				if(s.equals(str)) {
					--c;
				}
			}
		}
		
		return c;
	}
	
	static Map<String, String> swapKeysAndValues1(Map<String, String> map) {
		Map<String, String> res = new TreeMap<String, String>();
		for(Map.Entry<String, String> entry : map.entrySet()) {
			if(res.containsKey(entry.getValue())) {
				throw new RuntimeException();
			}
			res.put(entry.getValue(), entry.getKey());
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static <T> void fill(List<T> coll, T elem) {
		for(int i = 0; i < coll.size(); ++i) {
			coll.set(i, elem);
		}
	}
	
	public static <T> void reverse(List<T> coll) {
		int n = coll.size();
		for(int i = 0; i < n / 2; ++i) {
			T temp = coll.get(i);
			coll.set(i, coll.get(n - 1 - i));
			coll.set(n - 1 - i, temp);
		}
	}

	public static <T> void print(Collection<T> coll) {
		for (T t : coll) {
			System.out.println(t);
		}
	}
	
	public static Map<String, String> swapKeysAndValues(Map<String, String> map) throws Exception {
		Map<String, String> res = new TreeMap<String, String>();
		for(String key : map.keySet()) {
			String value = map.get(key);
			if(res.containsKey(value)) {
				throw new Exception("Postoi takov kluch");
			} else {
				res.put(value, key);
			}
		}
		for(Entry<String, String> entry : map.entrySet()) {
			entry.getKey();
			entry.getValue();
		}
		return res;
	}
}



