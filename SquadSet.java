import java.util.List;
import java.util.Collections;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SquadSet <T,R> {
    private final List<T> elements;

    SquadSet(List<T> elements) {
        this.elements = elements;
    }

    public SquadSet map(Function<T,R> mapFunction) { // Type T->R
        List<T> arr = Collections.unmodifiableList(elements);
        return new SquadSet(arr.stream().map(mapFunction).collect(Collectors.toUnmodifiableList()));
    }

    public SquadSet filter(Predicate<T> filterFunction) {
        List<T> arr = Collections.unmodifiableList(elements);
        return new SquadSet(arr.stream().filter(filterFunction).collect(Collectors.toUnmodifiableList()));
    }

    public void display(BinaryOperator<T> reducer, Consumer<T> consumer) {
        List<T> arr = Collections.unmodifiableList(elements);
        arr.stream().reduce(reducer).ifPresent(consumer);
    }

    public List<T> sum(SquadSet other) {
        List<T> arr = Collections.unmodifiableList(elements);
        List<T> otherArr = Collections.unmodifiableList((List<T>)other.getElements());

        return Stream.concat(arr.stream(), otherArr.stream()).distinct().collect(
                Collectors.toUnmodifiableList());
    }

    public List<T> complement(SquadSet other) {
        List<T> arr = Collections.unmodifiableList(elements);
        List<T> otherArr = Collections.unmodifiableList((List<T>)other.getElements());

        return arr.stream().filter(e -> !otherArr.contains(e)).collect(Collectors.toUnmodifiableList());
    }

    public List<T> intersect(SquadSet other) {
        List<T> arr = Collections.unmodifiableList(elements);
        List<T> otherArr = Collections.unmodifiableList((List<T>)other.getElements());

        return arr.stream().filter(otherArr::contains).collect(Collectors.toUnmodifiableList());
    }

    public T[] resultAll() {
        List<T> arr = Collections.unmodifiableList(elements);

        return (T[])arr.stream().toArray();
    }

    public List<T> getElements() {
        return Collections.unmodifiableList(elements);
    }
}
