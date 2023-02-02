import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CountSet<K> {

    private final List<K> elements;
    private final List<Integer> counts;

    CountSet(Map<K, Integer> inputMap) {
        elements = new ArrayList<>();
        counts = new ArrayList<>();
        inputMap.entrySet().stream().forEach(kvEntry -> {
            elements.add(kvEntry.getKey());
            counts.add(kvEntry.getValue());
        });
    }

    CountSet(List<K> elements, List<Integer> counts) {
        this.elements = elements;
        this.counts = counts;
    }
/*
    public List<K> append(K element) {
        List<K> arr = elements.stream().collect(Collectors.toList());
        arr.add(element);

        return Collections.unmodifiableList(arr);
    }

    public List<K> remove(K element) {
        List<K> arr = elements.stream().collect(Collectors.toList());
        List<Integer> cnt = counts.stream().collect(Collectors.toList());

        arr.stream().filter(e -> e.equals(element)).forEach(e -> {
            if (cnt.get(arr.indexOf(e)) > 0) {
                cnt.
            }
        });
    }

    public int countFor(K element) {
        List<K> arr = Collections.unmodifiableList(elements);
        return (int) arr.stream().map(e -> element.equals(e)).count();
    }
*/

    public List<K> sum(CountSet other) {
        List<K> arr = Collections.unmodifiableList(elements);
        List<K> otherArr = Collections.unmodifiableList(other.getElements());

        return Stream.concat(arr.stream(), otherArr.stream()).collect(
            Collectors.toUnmodifiableList());
    }

    public CountSet complement(CountSet other) {
        List<K> arr = elements.stream().collect(Collectors.toList());
        List<K> otherArr = Collections.unmodifiableList((List<K>) other.getElements());

        List<Integer> cnt = counts.stream().collect(Collectors.toList());
        List<Integer> otherCnt = Collections.unmodifiableList((List<Integer>) other.getCounts());

        arr.stream().forEach(e -> {
            int index1 = arr.indexOf(e);
            int index2;
            try {
                index2 = otherArr.stream()
                    .filter(e2 -> e.equals(e2))
                    .map(e2 -> otherArr.indexOf(e2))
                    .findFirst().get();
            } catch (NoSuchElementException exception) {
                return;
            }
            if (cnt.get(index1) - otherCnt.get(index2) > 0) {
                cnt.set(index1, cnt.get(index1) - otherCnt.get(index2));
            } else {
                cnt.set(index1, 0);
                arr.set(index1, null);
            }
        });
        List<Integer> cntFiltered = IntStream.range(0, arr.size())
            .mapToObj(index -> cnt.get(index))
            .filter(e -> e != 0)
            .collect(Collectors.toList());
        List<K> arrFiltered = IntStream.range(0, arr.size())
            .mapToObj(index -> arr.get(index))
            .filter(e -> e != null)
            .collect(Collectors.toList());

        return new CountSet(arrFiltered, cntFiltered);
    }

    /*
        public List<K> intersect(SquadSet other) {
            List<K> arr = Collections.unmodifiableList(elements);
            List<K> otherArr = Collections.unmodifiableList((List<K>) other.getElements());

            return arr.stream().filter(otherArr::contains).distinct()
                .collect(Collectors.toUnmodifiableList());
        }
    */
    public void resultAll() {
        List<K> arr = Collections.unmodifiableList(elements);
        List<Integer> cnt = counts.stream().collect(Collectors.toList());

        arr.stream().forEach(e -> {
            System.out.println(e); //값 출력 출력
            System.out.println(cnt.get(arr.indexOf(e))); //count 출력
        });
    }

    public List<K> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public List<Integer> getCounts() {
        return Collections.unmodifiableList(counts);
    }


}
