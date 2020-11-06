import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
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

    static int gcd(int a, int b) {

        // System.out.println(Stream.of(a, b).reduce((x, y) -> y%x));
        // return Stream.of(a, b).reduce((x, y) -> y%x).get().intValue();

        if (a == 0) {
            return b;
        } else {
            return gcd(b%a, a);
        }
    }

    static long countRepeats(int... array) {

        Predicate<Integer> pred = x -> (array[x] == array[x - 1]);
        return Stream.iterate(1, x -> x + 1).limit(9).filter(pred).toArray().length;
    }

    static double normalizedMean(Stream<Integer> stream) {

        // int max = 0;
        // int min = 0;
        // int sum = 0;
        // int count = 0;

        // Consumer<Integer> checkMinMax = x -> {
        //     if (x > max) {
        //         max = x;
        //     } 
        //     if (x < min) {
        //         min = x;
        //     }
        //     sum += x;
        //     count++;
        // };

        // stream.forEach(checkMinMax);
        List<Integer> list = stream.collect(Collectors.toList());
        Double max = (double) list.stream().mapToInt(Integer::intValue).max().getAsInt();
        Double min = (double) list.stream().mapToInt(Integer::intValue).min().getAsInt();
        Double average = list.stream().mapToInt(Integer::intValue).average().getAsDouble();

        System.out.println("max : " + max);
        System.out.println("min : " + min);
        System.out.println("average : " + average);

        return (average - min) / (max - min);
    }
}