import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class LazyList<T extends Comparable<?>> {

    private List<Lazy<T>> list;
    
    private LazyList(List<Lazy<T>> list) {
        this.list = list;
    }

    static <T extends Comparable<?>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {

        Lazy<T> lazySeed = Lazy.of(seed);
        
        List<Lazy<T>> list = Stream.iterate(lazySeed, x -> x.map(y -> f.apply(y)))
                                   .limit(n)
                                   .collect(Collectors.toList());


        return new LazyList<T>(list);
    }

    public List<Lazy<T>> getList() {
        return this.list;
    }

    public T get(int i) {
        return this.list.get(i).get();
    }

    public int indexOf(T v) {
        return this.list.indexOf(Lazy.of(v));
    }
}