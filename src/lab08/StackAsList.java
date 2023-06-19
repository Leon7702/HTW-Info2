package lab08;

public class StackAsList<T> implements Stack<T> {

    class Node {
        T value;
        Node next;
    }

    private Node head;

    public StackAsList() {
        head = new Node();
        head = null;
    }
    
    public StackAsList(T obj) {
        head = new Node();
        head.value = obj;
        head.next = null;
    }

    public void push(T x) throws OverflowException {
        Node newNode = new Node();
        newNode.value = x;
        newNode.next = head;
        head = newNode;
    }

    public void pop() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException("There is nothing to pop.");
        } else {
            head = head.next;
        }
    }

    public T top() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException("There is nothing.");
        } else {
            return head.value;

        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void makeEmpty() {
        head = null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack is empty.";
        } else {
            StringBuilder sb = new StringBuilder();
            Node current = head;
            sb.append("Stack: [");
            sb.append(current.value);

            while (current.next != null) {
                current = current.next;
                sb.append(", ");
                sb.append(current.value);
            }

            sb.append("]");
            return sb.toString();
        }
    }
}
