package mk.ukim.finki.np.av8;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LuckySuitor {
    private final List<Integer> positions;

    public LuckySuitor(int n) {
        positions = IntStream.rangeClosed(1, n)
                .mapToObj(Integer::new)
                .collect(Collectors.toList());
    }


    public int getWinner() {
        ListIterator<Integer> listIterator = positions.listIterator();
        boolean toRight = true;
        while (positions.size() != 1) {
            int last = -1;
            for (int i = 0; i < 3; ++i) {
                if (listIterator.hasNext() && toRight) {
                    last = listIterator.next();
                    if (!listIterator.hasNext()) {
                        toRight = false;
                        listIterator.previous();
                    }
                    //System.out.println("->: " + last);
                } else {
                    if (listIterator.hasPrevious()) {
                        last = listIterator.previous();
                        if (!listIterator.hasPrevious()) {
                            toRight = true;
                            listIterator.next();
                        }
                        //System.out.println("<-: " + last);
                    }
                }
            }
            //System.out.println("Remove: " + last);
            listIterator.remove();
            //System.out.println("DIR: " + (toRight ? "->" : "<-"));
        }
        return positions.get(0);
    }

    public static void main(String[] args) {
        LuckySuitor luckySuitor = new LuckySuitor(5);
        System.out.println("Winner: " + luckySuitor.getWinner());
    }
}
