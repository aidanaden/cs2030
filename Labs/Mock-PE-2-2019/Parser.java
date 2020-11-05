import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Parser {

    private final List<?> list;

    Parser(List<?> list) {

        this.list = list; 
    }

    static public Parser parse(List<?> list) {

        return new Parser(list);
    }

    public String toString() {

        String str = Stream.iterate(0, x -> x + 1)
                           .limit(list.size())
                           .map(x -> list.get(x).toString())
                           .collect(Collectors.joining(System.lineSeparator()));
        return str;
    }

    public String linecount() {
        return String.format("%d", this.list.size());
    }

    public int wordcount() {

        int intWordCount = Stream.iterate(0, x -> x + 1)
                           .limit(list.size())
                           .map(x -> list.get(x).toString())
                           .collect(Collectors.joining(" "))
                           .split(" ")
                           .length;

        return intWordCount;
    }
}