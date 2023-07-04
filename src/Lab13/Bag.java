package Lab13;

import java.util.*;

public class Bag<E> implements Iterable<E>{
    private List<E> elements;

    public Bag() {
        elements = new ArrayList<>();
    }

    public void add(E element) {
        elements.add(element);
    }

    public void addAll(Bag<E> otherBag) {
        elements.addAll(otherBag.elements);
    }

    public boolean remove(E element) {
        return elements.remove(element);
    }

    public boolean contains(E element) {
        return elements.contains(element);
    }

    public int count(E element) {
        int count = 0;
        for (E e : elements) {
            if (e.equals(element)) {
                count++;
            }
        }
        return count;
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    public Set<E> toSet() {
        return new HashSet<>(elements);
    }
}

