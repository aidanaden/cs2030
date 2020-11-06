import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

interface InfiniteListImpl<T> {

    abstract Optional<T> get();
}