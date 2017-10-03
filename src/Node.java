
public class Node<T extends  Comparable<? super T>> {

    private T data;
    public Node<T> leftChild;
    public Node<T> rightChild;
    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return String.format(" Node{data=%s} ", this.data.toString());
    }
}