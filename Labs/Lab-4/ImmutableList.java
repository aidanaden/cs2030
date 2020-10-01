import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.lang.model.type.ArrayType;

import java.util.ArrayList;

class ImmutableList<T> {

    private final List<T> list;

    public List<T> getList() {
        return this.list;
    }

    ImmutableList(List<T> list) {

        List<T> listCopy = new ArrayList<T>();
        listCopy.addAll(list);

        this.list = listCopy;
    }

    @SafeVarargs
    ImmutableList(T... list) {

        T[] clonedArray = list.clone();

        this.list = new ArrayList<T>(Arrays.asList(clonedArray));
    }

    @Override
    public String toString() {
        return this.list.toString();
    }

    public ImmutableList<T> add(T newElement) {

        List<T> elementsArray = new ArrayList<T>();
        elementsArray.addAll(this.list);

        elementsArray.add(elementsArray.size(), newElement);

        ImmutableList<T> newImmutableList = new ImmutableList<T>(elementsArray);

        return newImmutableList;
    }

    public ImmutableList<T> remove(T oldElement) {

        List<T> elementsArray = new ArrayList<T>();
        elementsArray.addAll(this.list);

        List<Integer> indexesToBeRemoved = new ArrayList<>();
        
        for (int i=0; i<elementsArray.size(); i++) {

            T element = elementsArray.get(i);

            if (element == oldElement) {
                indexesToBeRemoved.add(i);
            }
        }

        for (int index: indexesToBeRemoved) {
            elementsArray.remove(index);
        }

        ImmutableList<T> newImmutableList = new ImmutableList<T>(elementsArray);

        return newImmutableList;
    }

    public ImmutableList<T> replace(T oldElement, T newElement) {

        List<T> elementsArray = new ArrayList<T>();
        elementsArray.addAll(this.list);

        for (int i=0; i<elementsArray.size(); i++) {

            T element = elementsArray.get(i);

            if (element == oldElement) {
                elementsArray.remove(i);
                elementsArray.add(i, newElement);
            }
        }

        ImmutableList<T> newImmutableList = new ImmutableList<T>(elementsArray);

        return newImmutableList;
    }

    public ImmutableList<T> limit(long limitLength) {
        
        List<T> elementsArray = new ArrayList<T>();
        elementsArray.addAll(this.list);

        List<T> subElementsArray = new ArrayList<T>();

        if (limitLength < 0) {
            
            throw new IllegalArgumentException("limit size < 0");

        } else if (limitLength < elementsArray.size()) {

            subElementsArray = elementsArray.subList(0, (int) limitLength);

        } else {

            subElementsArray = elementsArray;
        }      

        ImmutableList<T> newImmutableList = new ImmutableList<T>(subElementsArray);

        return newImmutableList;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof ImmutableList<?>) {
            ImmutableList<?> immutableListO = (ImmutableList<?>) o;
            return (immutableListO.getList().equals(this.list));
        } else {
            return false;
        }
    }    

    public ImmutableList<T> sorted(Comparator<T> comperator) {

        if (comperator != null) {

            List<T> copyList = new ArrayList<T>();
            copyList.addAll(this.list);

            Collections.sort(copyList, comperator);

            ImmutableList<T> newImmutableList = new ImmutableList<T>(copyList);

            return newImmutableList;
        }   
        
        else {
            throw new NullPointerException("Comparator is null");
        }
    }

    public Object[] toArray() {

        List<T> copyList = new ArrayList<T>();
        copyList.addAll(this.list);

        return copyList.toArray();
    }

    public <T> T[] toArray(T[] arrayType) {

        // if (arrayType == null) {
        //     throw new NullPointerException("Input array cannot be null");
        // }

        try {
            return this.list.toArray(arrayType);

        } catch (ArrayStoreException e) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        } catch (NullPointerException e) {
            throw new NullPointerException("Input array cannot be null");
        }
    }

    public ImmutableList<T> filter(Predicate<? super T> predicateFunction) {

        Stream<T> filteredStream = this.list.stream().filter(predicateFunction);
        List<T> filteredList = new ArrayList<>(filteredStream.collect(Collectors.toList()));

        return new ImmutableList<T>(filteredList);
    }

    public <R> ImmutableList<R> map(Function<? super T, ? extends R> function) {

        Stream<R> mappedStream = this.list.stream().map(function);
        List<R> mappedList = new ArrayList<R>(mappedStream.collect(Collectors.toList()));

        return new ImmutableList<R>(mappedList);
    }
}



