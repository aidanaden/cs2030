import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class InfiniteList<T> implements InfiniteListImpl<T> {

    private T value;
    private final Supplier<? extends T> supplier;


    static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return new InfiniteList<T>(supplier);
    }

    static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteList<T>(seed, f);
    }

    InfiniteList(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    InfiniteList(T seed, UnaryOperator<T> f) {
        
        Supplier<? extends T> supp = () -> f.apply(this.value);
        this.value = seed;
        this.supplier = supp;
    }


    public Optional<T> get() {

        Optional<T> current = Optional.ofNullable(this.value);
        this.value = supplier.get();
        return current;
    }
    
}
