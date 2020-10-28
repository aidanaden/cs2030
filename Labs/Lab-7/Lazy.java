import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lazy<T extends Comparable<?>> {

    private final Supplier<T> s;
    private boolean isComputed = false;
    private T value;

    Lazy(Supplier<T> s) {
        this.s = s;
    }

    static public <T extends Comparable<?>> Lazy<T> of(T value) {

        Supplier<T> s = () -> (value);
        return Lazy.of(s);
    } 

    static public <T extends Comparable<?>> Lazy<T> of(Supplier<T> s) {

        if (Objects.isNull(s)) {
            throw new NoSuchElementException("No value present");
        }

        return new Lazy<>(s);
    }

    public boolean isComputed() {

        if (Objects.isNull(this.value)) {
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
        
        return Objects.requireNonNull(this.value);
    }

    public <U extends Comparable<?>> Lazy<U> map(Function<? super T, ? extends U> mapper) {
        
        Supplier<U> s = () -> (mapper.apply(get()));
        return Lazy.of(s);
    }

    public <U extends Comparable<?>> Lazy<U> flatMap(Function<? super T, ? extends Lazy<U>> mapper) {
        
        Lazy<U> result = mapper.apply(get());
        return result;
    }

    public <U extends Comparable<?>, Z extends Comparable<?>> Lazy<U> combine(Lazy<Z> lazyObj, 
                                                                            BiFunction<T, Z, U> func) {
        
        Supplier<U> s = () -> (func.apply(get(), lazyObj.get()));
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

        if ((Integer) get() < (Integer) lazyObj.get()) {

            Supplier<Integer> s = () -> (-1);
            return Lazy.of(s);

        } else if ((Integer) get() > (Integer) lazyObj.get()) {

            Supplier<Integer> s = () -> (1);
            return Lazy.of(s);

        } else {
            
            // else if ((Integer) get() == (Integer) lazyObj.get())
            Supplier<Integer> s = () -> (0);
            return Lazy.of(s);
        }
    }
}