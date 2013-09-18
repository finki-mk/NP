package edu.finki.np.av6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SocialNetwork {
	private Map<String, User> users;

	public SocialNetwork() {
		this.users = new HashMap<String, User>();
	}

	public void read(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String name1 = parts[0];
			String name2 = parts[1];
			User first = users.get(name1);
			User second = users.get(name2);
			if (first == null) {
				first = new User(name1);
				users.put(name1, first);
			}
			if (second == null) {
				second = new User(name2);
				users.put(name2, second);
			}
			first.addFriend(second);
		}
		scanner.close();
	}

	public void findFriends(String name, int n) {
		if (this.users.containsKey(name)) {
			this.users.get(name).findFriends(n);
		}
	}

	public void print() {
		
		for (String key : this.users.keySet()) {
			System.out.println("USER: " + key);
			System.out.println("FRIENDS: ");
			User u = this.users.get(key);
			for (User user : u.getFriends()) {
				System.out.println(user);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		InputStream inputStream = new FileInputStream(new File("friends.txt"));
		SocialNetwork socialNetwork = new SocialNetwork();
		socialNetwork.read(inputStream);
		socialNetwork.print();
		//Scanner scanner = new Scanner(System.in);
		//String name = scanner.nextLine();
		//socialNetwork.findFriends(name, 2);
	}
}

class User {
	private String name;
	private Set<User> friends;
	private static Set<String> found;

	public User(String name) {
		this.name = name;
		this.friends = new HashSet<User>();
	}

	public boolean addFriend(User friend) {
		return this.friends.add(friend);
	}

	public void findFriends(int n) {
		User.found = new HashSet<String>();
		User.found.add(this.name);
		this.findFriendsOfFriends(this.name, n);
	}

	private void findFriendsOfFriends(String name, int n) {
		if (n == 0) {
			for (User user : this.friends) {
				if (!User.found.contains(user.name)) {
					System.out.println(user);
				}
			}
			return;
		}
		for (User user : this.friends) {
			if (!User.found.contains(user.name)) {
				user.findFriendsOfFriends(name, n - 1);
				User.found.add(user.name);
			}
		}
	}

	public Set<User> getFriends() {
		return this.friends;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
