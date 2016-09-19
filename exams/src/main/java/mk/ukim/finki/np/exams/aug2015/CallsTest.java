package mk.ukim.finki.np.exams.aug2015;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * August 2015
 */
public class CallsTest {
    public static void main(String[] args) {
        Calls calls = new Calls();
        File file = new File("a.x");
        System.out.println("PATH: " + file.getAbsolutePath());
        file.delete();
        /*calls.read(System.in);
        System.out.println("=== INPUT CALLS ===");
        calls.write(System.out, "I");
        System.out.println("=== OUTPUT CALLS ===");
        calls.write(System.out, "O");*/
    }
}

class Calls {
    List<Call> calls;

    public Calls() {
        calls = new ArrayList<Call>();
    }

    public void read(InputStream inputStream) {

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.length() == 0) return;
            Call call = new Call();
            if (call.parse(line)) {
                calls.add(call);
            }
        }
    }

    public void write(OutputStream outputStream, String type) {
        List<Call> ofType = new ArrayList<Call>();
        for (Call call : calls) {
            if (call.type.equals(type)) {
                ofType.add(call);
            }
        }
        PrintWriter writer = new PrintWriter(outputStream);
        Collections.sort(ofType);
        for (Call call : ofType) {
            writer.printf("%-15s%-10s%10d\n", call.number, call.time(), call.time);
        }
        writer.flush();
    }
}

class Call implements Comparable<Call> {
    String number;
    int time;
    String type;

    public Call() {
    }

    public boolean parse(String str) {
        String[] parts = str.split("\\s+");
        number = parts[0];
        String ts = parts[1];
        type = parts[2];
        if (number.length() != 9) return false;
        if (!number.startsWith("07")) return false;
        String[] t = ts.split(":");
        if (t.length != 3) return false;
        int h = Integer.parseInt(t[0]);
        int m = Integer.parseInt(t[1]);
        int s = Integer.parseInt(t[2]);
        time = h * 3600 + m * 60 + s;
        return true;
    }

    @Override
    public int compareTo(Call call) {
        if (this.time == call.time) {
            return number.compareTo(call.number);
        }
        return Integer.compare(call.time, time);
    }

    String time() {
        return String.format("%02d:%02d:%02d", time / 3600, (time % 3600) / 60, time % 60);
    }
}
