package mk.ukim.finki.np.mt1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ArchiveStore {
	private ArrayList<Archive> items;
	private StringBuilder log;

	public ArchiveStore() {
		items = new ArrayList<>();
		log = new StringBuilder();
	}

	public void archiveItem(Archive item) {
		LocalDateTime now = LocalDateTime.now();
		item.archive(now);
		items.add(item);
		log.append(String.format("Item %d archived at %s\n", item.getId(), now));
	}

	public Archive openItem(int id) {
		for (Archive item : items) {
			if (item.getId() == id) {
				Date now = new Date();
				try {
					item.open(now);
				} catch (InvalidArchiveOpenException e) {
					log.append(e.getMessage());
					log.append("\n");
				}
				log.append(String.format("Item %d opened at %s\n",
						item.getId(), now));
				return item;
			}
		}
		return null;
	}

	public String getLog() {
		return log.toString();
	}
}

abstract class Archive {
	protected int id;
	protected boolean isArchived;
	protected LocalDateTime dateArchived;

	public void archive(LocalDateTime date) {
		this.dateArchived = date;
		this.isArchived = true;
	}

	public abstract Date open(Date date) throws InvalidArchiveOpenException;

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		Archive archive = (Archive) obj;
		return this.id == archive.id;
	}
}

class LockedArchive extends Archive {
	private Date dateToOpen;

	public LockedArchive(int id, Date dateToOpen) {
		this.id = id;
		this.dateToOpen = dateToOpen;
	}

	@Override
	public Date open(Date date) throws InvalidArchiveOpenException {
		if (date.before(dateToOpen))
			throw new InvalidArchiveOpenException(String.format(
					"Item %d cannot be opened before %s", id, dateToOpen));
		return date;

	}
}

class SpecialArchive extends Archive {
	private int countOpened;
	private int maxOpen;

	public SpecialArchive(int id, int maxOpen) {
		this.id = id;
		countOpened = 0;
		this.maxOpen = maxOpen;
	}

	@Override
	public Date open(Date date) throws InvalidArchiveOpenException {
		if (countOpened >= maxOpen)
			throw new InvalidArchiveOpenException(String.format(
					"Item %d cannot be opened more than %d times", id, maxOpen));
		++countOpened;
		return date;
	}

}

class InvalidArchiveOpenException extends Exception {
	public InvalidArchiveOpenException(String message) {
		super(message);
	}
}