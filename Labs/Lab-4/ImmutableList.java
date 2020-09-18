import java.util.Arrays;
import java.util.Collections;
import java.util.List; 
import java.util.ArrayList;

class ImmutableList<T> {

    private final List<T> list;

    ImmutableList(List<T> list) {

        List<T> listCopy = new ArrayList<T>();
        listCopy.addAll(list);

        this.list = listCopy;
    }

    @SafeVarargs
    ImmutableList(T... list) {

        T[] clonedArray = list.clone();

        this.list = Arrays.asList(clonedArray);
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

        if (limitLength < elementsArray.size()) {

            subElementsArray = elementsArray.subList(0, (int) limitLength);

        } else {

            subElementsArray = elementsArray;
        }      

        ImmutableList<T> newImmutableList = new ImmutableList<T>(subElementsArray);

        return newImmutableList;
    }


    
}