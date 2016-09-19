package mk.ukim.finki.np.exams.aug2016;

import java.util.*;

/**
 * User groups problem
 */
public class UserGroupsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserGroups userGroups = new UserGroups();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] users = new String[n];
        String[] groups = new String[n];
        for (int i = 0; i < n; ++i) {
            String[] parts = scanner.nextLine().split(":");
            users[i] = parts[0];
            groups[i] = parts[1];
        }
        userGroups.addUsers(users, groups);
        n = scanner.nextInt();
        scanner.nextLine();
        String[] groups1 = new String[n];
        String[] parentGroups = new String[n];
        for (int i = 0; i < n; ++i) {
            String[] parts = scanner.nextLine().split(":");
            groups1[i] = parts[0];
            parentGroups[i] = parts[1];
        }
        userGroups.setParentGroups(groups1, parentGroups);
        System.out.println("--- USERS ---");
        userGroups.printUsers();
        System.out.println("--- GROUPS ---");
        userGroups.printGroups();
        scanner.close();
    }
}

class UserGroups {
    Map<String, User> users;
    Map<String, Set<User>> groups;

    public UserGroups() {
        users = new HashMap<>();
        groups = new TreeMap<>();
    }

    public void addUsers(String[] users, String[] groups) {
        for (int i = 0; i < users.length; ++i) {
            User user = new User(users[i]);
            user.addGroup(groups[i]);
            this.users.put(users[i], user);
            Set<User> groupUsers = this.groups.get(groups[i]);
            if (groupUsers == null) {
                groupUsers = new TreeSet<>();
            }
            groupUsers.add(user);
            this.groups.put(groups[i], groupUsers);
        }
    }

    public void setParentGroups(String[] groups, String[] parentGroups) {
        for (int i = 0; i < groups.length; ++i) {
            Set<User> users = this.groups.get(groups[i]);
            Set<User> pgUsers = this.groups.get(parentGroups[i]);
            if (users != null && pgUsers != null) {
                pgUsers.addAll(users);
                for (User user : users) {
                    user.groups.add(parentGroups[i]);
                }
            }
        }
    }

    public void printUsers() {
        TreeSet<User> users = new TreeSet<>(this.users.values());
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void printGroups() {
        for (String group : groups.keySet()) {
            System.out.print(group);
            System.out.print(" : ");
            int i = 0;
            Set<User> users = groups.get(group);
            for (User user : users) {
                System.out.print(user.name);
                if (++i != users.size()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}

class User implements Comparable<User> {
    String name;
    int number;
    Set<String> groups;

    public User(String name) {
        this.name = name;
        number = Integer.parseInt(name.split("_")[1]);
        this.groups = new TreeSet<>();
    }

    public void addGroup(String name) {
        this.groups.add(name);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(name);
        res.append(" : ");
        for (String g : groups) {
            res.append(g);
            res.append(", ");
        }
        res.delete(res.length() - 2, res.length());
        return res.toString();
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(number, o.number);
    }
}

