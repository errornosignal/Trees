
public class Main {

    public static void main(String[] args){
        BST<String> names = new BST<>();

        names.add("Reid");
        names.add("James");
        names.add("Lela");
        names.add("Daniel");

        System.out.println(names.contains("Reid"));
        System.out.println(names.contains("James"));
        System.out.println(names.contains("Bob"));
        System.out.println(names.contains("reid"));
    }
}