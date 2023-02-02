import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SquadSet <T> {
    private final List<T> elements;

    SquadSet(List<T> elements) {
        this.elements = elements;
    }

    public List<T> sum(SquadSet other) {
        List<T> arr = Collections.unmodifiableList(elements);
        List<T> otherArr = Collections.unmodifiableList((List<T>)other.getElements());

        return Stream.concat(arr.stream(), otherArr.stream()).filter(e -> (arr.contains(e) && otherArr.contains(e))).collect(
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

        return Stream.concat(arr.stream(), otherArr.stream()).collect(Collectors.toUnmodifiableList());
    }

    public List<T> getElements() {
        return Collections.unmodifiableList(elements);
    }
}
