
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
        if (this.isEmpty()) {
            return false;
        }// else I am not empty doNothing();

        Node<T> current = this.root;
        Node<T> parent = this.root;
        boolean isLeftChild = false;
        while (current.getData().compareTo(element) != 0) {
            parent = current;
            if (element.compareTo(current.getData()) < 0) {
                isLeftChild = true;
                current = current.leftChild;
            }
            else{
                isLeftChild = false;
                current = current.rightChild;
            }

            if (current == null) {
                return false;
            }//else, we still have nodes, doNothing();
        }

        //CASE 1 - I am a leaf (on the wind).
        if (current.leftChild == null && current.rightChild == null) {
            if (current == this.root) {
                this.root = null;
            }
            else if (isLeftChild) {
                parent.leftChild = null;
            }
            else {
                parent.rightChild = null;
            }
            this.size--;
            return true;
        }
        else if (current.rightChild == null) {
            //CASE 2A if no right children, replace with the entire (ALL OF IT) left subtree!
            if (current == this.root) {
                this.root = current.leftChild;
            }
            else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            }
            else {
                parent.rightChild = current.leftChild;
            }
            this.size--;
            return true;
        }
        else if (current.leftChild == null) {
            //CASE 2B if no left children, replace the entire right subtree!
            if (current == this.root) {
                this.root = current.rightChild;
            }
            else if (isLeftChild) {
                parent.rightChild = current.rightChild;
            }
            else {
                parent.rightChild = current.rightChild;
            }
            this.size--;
            return true;
        }
        else {
            //CASE 3 Two children, so replace with "inorder" candidate
            Node<T> candidate = this.getCandidate(current);
            if (current == this.root) {
                this.root = candidate;
            }
            else if (isLeftChild) {
                parent.leftChild = candidate;
            }
            else {
                parent.rightChild = candidate;
            }
            // IMPORTANT - connect candidate to current's left child!
            candidate.leftChild = current.leftChild;
            this.size--;
            return true;
        }
    }

    private Node<T> getCandidate(Node<T> toDelete) {
        Node<T> candidateParent = toDelete;
        Node<T> candidate = toDelete;
        Node<T> current = toDelete.rightChild;

        while (current != null) {
            candidateParent = candidate;
            candidate = current;
            current = current.leftChild;
        }

        if (candidate != toDelete.rightChild) {
            candidateParent.leftChild = candidate.rightChild;
            candidate.rightChild = toDelete.rightChild;
        }//else, we are not at the first right, doNothing();
        return candidate;
    }

    @Override
    public int size() {
        return this.size;
    }
}
