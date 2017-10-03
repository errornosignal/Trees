
public class BST<T extends Comparable<? super T>> implements Tree<T> {

    private Node<T> root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }
    public boolean isEmpty() {
        return this.root == null;
        //return this.size == 0;
    }

    @Override
    public boolean add(T element) {
        Node<T> newLeaf = new Node<>(element);
        if (this.isEmpty()) {
            this.root = newLeaf;
            this.size++;
            return true;
        }
        else
        {   //well, I am not empty!
            Node<T> current = this.root;
            Node<T> parent = null;
            while(true) {
                parent = current;
                if(element.compareTo(current.getData()) < 0) {
                    //hold on, we are going left!
                    current = current.leftChild;
                    if (current == null) {
                        //we found a home for you
                        parent.leftChild = newLeaf;
                        this.size++;
                        return true;
                    }//else, leftChild is not null, keep looing oNothing();
                }
                else if (element.compareTo(current.getData()) > 0) {
                    //we are going right
                    parent.rightChild = newLeaf;
                    this.size++;
                    return true;
                }//else, rightChild is not null, keep looking doNothing();
                else {
                    //element is equal to current. no duplicates
                    return false;
                }
            }
        }
    }

    @Override
    public boolean contains(T element) {
        if (this.isEmpty()) {
            return false;
        } //else, we at least have a root doNothing();
        Node<T> current = this.root;
        while (current.getData().compareTo(element) != 0) {
            if(current.getData().compareTo(element) < 0) {
                current = current.rightChild;
            }
            else {
                current = current.leftChild;
            }
            if (current == null) {
                return false;
            } //else, the key may still exist, doNothing();
        }
        return true;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }
}
