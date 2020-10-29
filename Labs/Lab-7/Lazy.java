import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lazy<T extends Comparable<T>> {

    private final Supplier<T> s;
    private boolean isComputed = false;
    private T value;

    Lazy(Supplier<T> s) {
        this.s = s;
    }

    static public <T extends Comparable<T>> Lazy<T> of(T value) {

        Supplier<T> s = () -> (value);
        return Lazy.of(s);
    } 

    static public <T extends Comparable<T>> Lazy<T> of(Supplier<T> s) {

        Optional<Supplier<T>> optS = Optional.ofNullable(s);
        Optional<Supplier<T>> optE = Optional.empty();

        if (optS == optE) {
            throw new NoSuchElementException("No value present");
        }

        return new Lazy<>(s);
    }

    public boolean isComputed() {

        Optional<T> optValue = Optional.ofNullable(this.value);
        Optional<T> optNull = Optional.empty();

        if (optValue == optNull) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {

        if (isComputed()) {
            return this.value.toString();
        } else {
            return "?";
        }
    }

    public T get() {

        if (!isComputed()) {
            this.isComputed = true;
            this.value = this.s.get();
        } 

        Optional<T> optValue = Optional.ofNullable(this.value);
        Optional<T> optNull = Optional.empty();

        if (optValue == optNull) {
            throw new NullPointerException();
        } 
        
        return this.value;
    }

    public <U extends Comparable<U>> Lazy<U> map(Function<? super T, ? extends U> mapper) {
        
        Supplier<U> s = () -> mapper.apply(get());
        return Lazy.of(s);
    }

    public <U extends Comparable<U>> Lazy<U> flatMap(Function<? super T, ? extends Lazy<U>> mapper) {
        
        Supplier<U> suppResult = () -> mapper.apply(get()).get();
        Lazy<U> result = Lazy.of(suppResult);

        return result;
    }

    public <U extends Comparable<U>, Z extends Comparable<Z>> Lazy<U> combine(Lazy<Z> lazyObj, 
                                                                            BiFunction<T, Z, U> func) {
        
        Supplier<U> s = () -> func.apply(get(), lazyObj.get());
        return Lazy.of(s);
    }

    public Lazy<Boolean> test(Predicate<T> pred) {
        
        Supplier<Boolean> s = () -> (pred.test(get()));
        return Lazy.of(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Lazy) {

            Lazy<?> lazyObj = (Lazy<?>) obj;
            return get().equals(lazyObj.get());
        }

        return false;
    }

    public Lazy<Integer> compareTo(Lazy<T> lazyObj) {

        // Object objLazyObj = (Object) lazyObj.get();

        if (get().equals(lazyObj.get())) {

            Supplier<Integer> s = () -> (0);
            return Lazy.of(s);
            
        } else {

            Supplier<Integer> value = () -> get().compareTo(lazyObj.get());

            return Lazy.of(value);
        }
    }
}