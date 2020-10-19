import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

interface Logger<T> {

    static <T> Logger<T> make(T thing){

        if (thing instanceof Logger) {

            throw new IllegalArgumentException("already a Logger");
        
        } else if (thing == null) {

            throw new IllegalArgumentException("argument cannot be null");

        } else {

            ArrayList<Object> lastObjs = new ArrayList<Object>();
            lastObjs.add(thing);
            return new LoggerImpl<>(thing, lastObjs);
        }
    }

    void printlog();
    
    boolean test(Predicate<? super T> pred);
    
    <U> LoggerImpl<U> map(Function<? super T, ? extends U> mapper);
    
    <U> LoggerImpl<U> flatMap(Function<? super T, ? extends Logger<? extends U>> mapper);
}