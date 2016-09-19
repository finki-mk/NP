package mk.ukim.finki.np.exams.jun2016;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class LoggerTest {
  public static void main(String[] args) {
    SmartLogger smartLogger = new SmartLogger(System.out);
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line == null || line.length() == 0) break;
      String[] parts = line.split(":");
      if (parts[1].equalsIgnoreCase("error")) {
        smartLogger.log(new ErrorMessage(parts[0], parts[2]));
      } else {
        smartLogger.log(new InfoMessage(parts[0], parts[2]));
      }
    }
    scanner.close();
    System.out.println("====== Stats ======");
    smartLogger.stats();
  }

}

interface Logger<T extends LogMessage> {
  void log(T message);
}

class SmartLogger implements Logger<LogMessage> {

  PrintWriter writer;
  Set<LogMessage> messages;
  Map<MessageType, Integer> count;
  int duplicates;
  int total;

  public SmartLogger(OutputStream outputStream) {
    writer = new PrintWriter(outputStream);
    messages = new HashSet<>();
    count = new TreeMap<>();
    for (MessageType type : MessageType.values()) {
      count.put(type, 0);
    }
    duplicates = 0;
    total = 0;
  }

  @Override
  public void log(LogMessage message) {
    ++total;
    int c = count.get(message.getType());
    ++c;
    count.put(message.getType(), c);
    if (messages.contains(message)) {
      ++duplicates;
      writer.println(String.format("Duplicate message: %s", message.getId()));
    } else {
      writer.println(String.format("%s: [%s] %s", message.getId(), message.getType(), message.getMessage()));
    }
    writer.flush();
    messages.add(message);
  }

  public void stats() {
    System.out.println(String.format("Total messages: %d", total));
    System.out.println(String.format("Duplicates: %d", duplicates));
    for (Map.Entry<MessageType, Integer> entry : count.entrySet()) {
      System.out.println(String.format("%s -> %d", entry.getKey(), entry.getValue()));
    }
  }


}

enum MessageType {
  ERROR,
  INFO
}

interface LogMessage {
  String getId();

  MessageType getType();

  String getMessage();
}

class GenericMessage implements LogMessage {
  String id;
  String message;
  MessageType type;

  public GenericMessage(String id, String message, MessageType type) {
    this.id = id;
    this.message = message;
    this.type = type;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public MessageType getType() {
    return type;
  }

  @Override
  public String getMessage() {
    return message.toLowerCase();
  }

  @Override
  public boolean equals(Object o) {
    GenericMessage that = (GenericMessage) o;
    return type == that.type && message.equalsIgnoreCase(that.message);
  }

  @Override
  public int hashCode() {
    int result = message.toLowerCase().hashCode();
    result = 31 * result + type.hashCode();
    return result;
  }
}

class ErrorMessage extends GenericMessage implements LogMessage {

  public ErrorMessage(String id, String message) {
    super(id, message, MessageType.ERROR);
  }

}

class InfoMessage extends GenericMessage implements LogMessage {

  public InfoMessage(String id, String message) {
    super(id, message, MessageType.INFO);
  }

}