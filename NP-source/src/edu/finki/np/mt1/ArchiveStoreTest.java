package edu.finki.np.mt1;

import java.util.Date;
import java.util.Scanner;

public class ArchiveStoreTest {
	public static void main(String[] args) {
		ArchiveStore store = new ArchiveStore();
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int i;
		for (i = 0; i < n; ++i) {
			int days = scanner.nextInt();
			Date dateToOpen = new Date(new Date().getTime() + days * 24 * 60
					* 60 * 1000);
			LockedArchive lockedArchive = new LockedArchive(i + 1, dateToOpen);
			store.archiveItem(lockedArchive);
		}
		n = scanner.nextInt();
		for (; i < n; ++i) {
			int maxOpen = scanner.nextInt();
			SpecialArchive specialArchive = new SpecialArchive(i + 1, maxOpen);
		}
		int open = scanner.nextInt();
		store.openItem(open);
		open = scanner.nextInt();
		store.openItem(open);
		System.out.println(store.getLog());
	}
}
