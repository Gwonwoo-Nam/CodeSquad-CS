import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public CountSet append(K element) {
        List<K> arr = elements.stream().collect(Collectors.toList());
        List<Integer> cnt = counts.stream().collect(Collectors.toList());
        arr.stream()
            .filter(e -> e == element)
            .findAny()
            .ifPresentOrElse(
                value -> {
                    int idx = arr.indexOf(value);
                    cnt.set(idx, cnt.get(idx) + 1);
                },
                () -> {
                    arr.add(element);
                    cnt.add(1);
                }
            );

        return new CountSet(arr, cnt);
    }

    public CountSet remove(K element) {
        List<K> arr = elements.stream().collect(Collectors.toList());
        List<Integer> cnt = counts.stream().collect(Collectors.toList());

        arr.stream()
            .filter(e -> element.equals(e))
            .findAny()
            .ifPresent(
                value -> {
                    int idx = arr.indexOf(value);
                    cnt.set(idx, cnt.get(idx) - 1);
                });

        return filterZero(arr, cnt);
    }

    public int countFor(K element) {
        List<K> arr = Collections.unmodifiableList(elements);
        List<Integer> cnt = counts.stream().collect(Collectors.toList());

        return arr.stream()
            .filter(e -> element.equals(e))
            .map(value -> cnt.get(arr.indexOf(value)))
            .findAny()
            .orElse(0);
    }


    public CountSet sum(CountSet other) {
        List<K> arr = elements.stream().collect(Collectors.toList());
        List<K> otherArr = Collections.unmodifiableList((List<K>) other.getElements());

        List<Integer> cnt = counts.stream().collect(Collectors.toList());
        List<Integer> otherCnt = Collections.unmodifiableList((List<Integer>) other.getCounts());

        otherArr.stream().forEach(otherVal -> {
            int otherIndex = otherArr.indexOf(otherVal);

            arr.stream()
                .filter(arrVal -> otherVal.equals(arrVal)) //other의 value와 일치하는 arr의 value를 찾는다.
                .map(arrVal -> arr.indexOf(arrVal)) //value가 일치하는 arr의 index를 찾는다.
                .findFirst()
                .ifPresentOrElse(arrIndex -> {
                    cnt.set(arrIndex, cnt.get(arrIndex) + otherCnt.get(otherIndex)); //일치하는 값이 있으면 합치기
                }, () -> {
                    arr.add(otherArr.get(otherIndex)); //일치하는게 없으면 새로 추가하기
                    cnt.add(otherCnt.get(otherIndex));
                });

        });
        return new CountSet(arr, cnt);
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
            }
        });

        return filterZero(arr, cnt);
    }

    private CountSet filterZero(List<K> arr, List<Integer> cnt) {
        List<Integer> cntFiltered = cnt.stream()
            .filter(e -> e != 0)
            .collect(Collectors.toUnmodifiableList());
        List<K> arrFiltered = arr.stream()
            .filter(e -> cnt.get(arr.indexOf(e)) != 0)
            .collect(Collectors.toUnmodifiableList());
        return new CountSet(arrFiltered, cntFiltered);
    }


    public CountSet intersect(CountSet other) {
        List<K> arr = Collections.unmodifiableList(elements);
        List<K> otherArr = Collections.unmodifiableList((List<K>) other.getElements());

        return new CountSet(arr.stream()
            .filter(otherArr::contains)
            .distinct()
            .collect(Collectors.toUnmodifiableList())
            , arr.stream()
            .filter(otherArr::contains)
            .distinct()
            .mapToInt((val)->1)
            .boxed()
            .collect(Collectors.toUnmodifiableList()));
    }

    public void resultAll() {
        List<K> arr = Collections.unmodifiableList(elements);
        List<Integer> cnt = counts.stream().collect(Collectors.toList());

        arr.stream().forEach(e -> {
            System.out.print(e + " : " + cnt.get(arr.indexOf(e)) + " , "); //값 출력 출력
        });
        System.out.println();
    }

    public List<K> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public List<Integer> getCounts() {
        return Collections.unmodifiableList(counts);
    }
}
