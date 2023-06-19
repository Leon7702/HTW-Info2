package lab09;

import java.util.Collection;

public interface Set<I> {
    boolean add(I item);
    boolean contains(I item);
    boolean remove(I item);
    void clear();
    int size();
    boolean isEmpty();
    void addAll(Collection<? extends I> collection);
    boolean retainAll(Collection<?> collection);
}
