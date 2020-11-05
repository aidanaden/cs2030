import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int[] twinPrimes(int n) {

        Predicate<Integer> pred = x -> isPrime(x); 
        List<Integer> predProcessed = new ArrayList<Integer>();
        
        List<Integer> list = Stream.iterate(1, x -> x + 1)
                                   .limit(n)
                                   .filter(pred)
                                   .map(x -> x.intValue())
                                   .collect(Collectors.toList());

                                   System.out.println(list);

        Stream.iterate(0, x -> x + 1)
              .limit(list.size()-1)
              .forEach(x -> {
                  if (list.get(x+1) - list.get(x) == 2) {
                    predProcessed.add(list.get(x));
                  } else if (x > 0) {

                    if (list.get(x) - list.get(x-1) == 2) {
                        predProcessed.add(list.get(x));
                    }
                  }
                });

        return predProcessed.stream().mapToInt(x -> x).toArray();
    }

    static public boolean isPrime(int n) {

        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}