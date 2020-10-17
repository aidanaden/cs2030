import java.util.function.Function;

interface Logger<T> {

    static <T> LoggerImpl<T> make(T thing){

        if (thing instanceof Logger) {

            throw new IllegalArgumentException("already a Logger");
        
        } else if (thing == null) {

            throw new IllegalArgumentException("argument cannot be null");

        } else {

            return new LoggerImpl<>(thing);
        }
    }

    void printlog();
    
    // boolean test(Predicate<? super T> pred);
    
    <U> LoggerImpl<U> map(Function<? super T, ? extends U> mapper);
    
    <U> LoggerImpl<U> flatMap(Function<? super T, ? extends Logger<? extends U>> mapper);
}