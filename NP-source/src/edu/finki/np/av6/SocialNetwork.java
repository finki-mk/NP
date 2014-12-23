package edu.finki.np.av6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class SocialNetwork {
	Map<String, User> users;

	public void loadNetwork(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream(filename));
		users = new HashMap<String, User>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String user = parts[0];
			String friend = parts[1];
			User userObj = users.get(user);
			if (userObj == null) {
				userObj = new User(user);
			}
			users.put(user, userObj);
			User friendObj = users.get(friend);
			if (friendObj == null) {
				friendObj = new User(friend);
			}
			users.put(friend, friendObj);
			userObj.addFriend(friendObj);
		}
		scanner.close();
	}

	public void print() {
		for (String key : users.keySet()) {
			System.out.println("KEY: " + key);
			System.out.println("VALUE: ");
			System.out.println(users.get(key));
		}
	}

	public void printFriends(String username, int level, HashSet<String> found) {

		User user = users.get(username);
		if (level == 1) {
			for (User friend : user.friends) {
				if (!found.contains(friend.username)) {
					System.out.println(friend.username);
					found.add(friend.username);
				}
			}
		} else {
			for (User friend : user.friends) {
				printFriends(friend.username, level - 1, found);
			}
		}

	}
	
	public void search(String username, int level) {
		HashSet<String> found = new HashSet<String>();
		found.add(username);
		printFriends(username, level, found);
	}

	public static void main(String[] args) throws FileNotFoundException {
		SocialNetwork sn = new SocialNetwork();
		sn.loadNetwork("friends.txt");
		// sn.print();
		
		sn.search("iba", 2);
	}
}

class User {
	String username;
	Set<User> friends;

	public User(String username) {
		this.username = username;
		this.friends = new HashSet<User>();
	}

	public void addFriend(User user) {
		this.friends.add(user);
	}

	@Override
	public boolean equals(Object obj) {
		User u = (User) obj;
		return u.username.equals(username);
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("User: %s\n", username));
		sb.append("Friends: \n");
		for (User user : friends) {
			sb.append(user.username);
			sb.append("\t");
		}
		return sb.toString();
	}
}