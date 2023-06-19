package lab09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SetAsList<I> implements Set<I> {
    
	private List<I> items;

    public SetAsList() {
        items = new ArrayList<>();
    }

    
    public boolean add(I item) {
        if (!contains(item)) {
            items.add(item);
            return true;
        }
        return false;
    }

    
    public boolean contains(I item) {
        for (I element : items) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }

    
    public boolean remove(I item) {
        return items.remove(item);
    }

    
    public void clear() {
        items.clear();
    }

    
    public int size() {
        return items.size();
    }

    
    public boolean isEmpty() {
        return items.isEmpty();
    }

    
    public void addAll(Collection<? extends I> collection) {
        for (I item : collection) {
            add(item);
        }
    }

    public boolean retainAll(Collection<?> collection) {
        List<I> retainedItems = new ArrayList<>();
        for (I item : items) {
            if (collection.contains(item)) {
                retainedItems.add(item);
            }
        }
        boolean modified = items.size() != retainedItems.size();
        items = retainedItems;
        return modified;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
            if (i < items.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

