package edu.finki.np.av8;

import java.util.Map;

public class ApplicationCache {
	private Map<String, Object> attributeMap;
	// Static instance
	private static ApplicationCache instance;

	// Static accessor method
	public static ApplicationCache getInstance(){
	      if(instance == null){
	         instance = new ApplicationCache();
	      }
	      return instance;
	   }

	// private Constructor
	private ApplicationCache() {
		// attributeMap = createCache(); // Initialize the cache
	}
}