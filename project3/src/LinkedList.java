// Written by selva053 and benkh008
// CSCI 1933
// Project 3
// Linked List Class

// creates a linkded  list with a dummy node as the head
public class LinkedList<T extends Comparable<T>> implements List<T> {
    Node dummy = new Node(-1000,null);
    private Node head;
    private  Boolean isSorted;
    public LinkedList() {
        this.head = dummy ;
        this.isSorted = true;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(55);
        list.add(501);
        list.add(545);
        list.add(888);
        list.remove(0);
        list.add(650);
        list.add(0,582);
        System.out.println(list.size());
        System.out.println(list.toString());
    }


    @Override
    /* check if the element that is being added is null if it is return false and do not add otherwise loop through the linked list until the last element is reached
    * and add the element there*/
    public boolean add(T element) {
        Node input = new Node(element,null);
        if (element == null) return false;

        else {
            Node current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(input);
        }
        isSorted = false;
        return true;
    }

    @Override
    /* if element is null or out of bounds return false. otherwise loop through the list and each time you loop increase the counter variable by one until counter equals index
    *when you reach the index you want to add to assign the next node of node at this specific index to a new node that contains the element that needs to be added and set the next node
    * of thew new node that was created to the node after the node at the current index*/
    public boolean add(int index, T element) {
        Node previous = null;
        Node input = null;
        Node next = null;
        int counter = 0;
        Node current = this.head;
        if (element == null) {
            return false;
        }
        if (index < 0 || index >= size()) {
            return false;
        }
        while (current.getNext() != null) {
            if (counter == index ) {
                previous = current;
                next = current.getNext();
                break;
            }
            counter++;
            current = current.getNext();
        }
        input = new Node(element,null);
        input.setNext(next);
        previous.setNext(input);
        isSorted = false;
        return true;
    }

    @Override
    // made head.setnext() to null. this way but cutting the head of the linked list you are essentially clearing the node as there is no way to loop through it
    public void clear() {
        head.setNext(null);
    }

    @Override
    // loop through the linked list until you find the element that you want to verify if it is in the linked list. if it is there return true if not return false
    public boolean contains(T element) {
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
            if (current.getData().equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    /*if the index you are trying to get is out of bounds return null. otherwise loop through the linked list until the number of iterations matched the index and return the data
    of the node at that index*/
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        Node current = head;
        for (int i = 0; i <= index ; i++) {
            current = current.getNext();
        }
        return (T) current.getData();
    }

    @Override
    // if element is null return - 1. otherwise loop through the linked and updating a counter variable that gets increased by one every iteration. once the loop reaches
    // the desired element return the counter variable that now represents the location of the element. if element is not found return -1
    public int indexOf(T element) {
        int counter = 0;
        Node current = this.head;
        if (element == null) {
            return -1;
        }
        while (current.getNext() != null) {
            current = current.getNext();
            if (current.getData().equals(element) ) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    @Override
    // if the value of the element next to the head node is null than return true. otherwise return false
    public boolean isEmpty() {
        if (this.head.getNext() == null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    /*loop through the linked list. each time you find an element than matches the element that you want to find set the variable last_index to that value
    * at the end return the last_index variable if the element is not found return -1 instead*/
    public int lastIndexOf(T element) {
        int counter = 0;
        int last_index = 0;
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
            if (current.getData().equals(element)) {
                last_index = counter;

            }
            counter ++;
        }
        if (last_index == 0) {
            return -1;
        }
        else {return last_index;}
    }

    @Override
    /* if the index is out of bound or the element in null return null otherwise loop through, each iteration update a counter variable that keeps track of the index of each node
    * once you reach the desired node change the value of that node, by replacing  it with a new node that contains the element that you want to set*/
    public T set(int index, T element) {
        Node replaced = null;
        Node input = new Node(element,null);
        Node previous = null;
        Node next = null;
        int counter = 0;
        Node current = this.head;
        if (index < 0 || index >= size() || element == null) {
            return null;
        }
        while (current.getNext() != null) {
            if (counter == index) {
                previous = current;
                replaced = current.getNext();
                next = current.getNext().getNext();

            }
            current = current.getNext();
            counter++;
        }
        previous.setNext(input);
        input.setNext(next);
        return (T)replaced.getData();
    }

    @Override
    public int size() {
        /*if the head.next value is null return 0 because it is empty. otherwise loop through the whole list until you reach the last element. each loop increase a variable
        * counter by one and return it at the end */
        Node current = this.head;
        int counter = 0;
        if (this.head.getNext()== null) {
            return 0;
        }
        else {
            while (current.getNext() != null) {
                current = current.getNext();
                counter ++;
            }
        }
        return counter;
    }

    @Override
    /*use bubble sort by using two nested while lopps to go through the loop and putting elements in their current location next to each other until all elements are in thr right spot
    * if the order is true sort the list in an increasing order. if it is false sort the list in a decreasing order */
    public void sort(boolean order) {
        T temp;
        if (order == true && isSorted == true) {
            ;
        }
        else if (order == true) {
            for (int i = 0; i < size() - 1; i++) {
                for (int j = 0; j < size() - 1; j++) {
                    if (this.get(j).compareTo(this.get(j + 1)) > 0) {
                        temp = this.get(j);
                        this.set(j, this.get(j + 1));
                        this.set(j + 1, temp);
                    }

                }
                isSorted = true;

            }
        }
        else if (order == false) {
            for (int i = 0; i < size() - 1 ; i++) {
                for (int j = 0; j < size() - 1 ; j++) {
                    if (this.get(j).compareTo(this.get(j+1)) < 0) {
                        temp = this.get(j);
                        this.set(j, this.get(j + 1));
                        this.set(j + 1, temp);
                    }

                }

            }
        }
    }

    @Override
    /*loop through the linked list until you find the element that you want to remove and set the previous element to the node after the node that you want to remove.
    * if you are able to remove the desired element return true else return false*/
    public boolean remove(T element) {
        Node next = null;
        Node previous = null;
        Node current = this.head;
        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
            if (current.getData() == element) {
                next = current.getNext();
                previous.setNext(next);
                return true;
            }
       }
        return false;
    }

    @Override
    /*if the index is out of bound return null, otherwise loop through the list increasing the counter variable by one to kepp track of the indexes and once you
    * reach the desired index remove the node there. the same way it was removed in the previous remove method. if the node was removed return true else return false*/
    public T remove(int index) {
        Node next;
        int counter = -1;
        Node previous;
        Node current = head;
        if (index < 0 || index >= size()) {
            return null;
        }
        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
            counter++;
            if (counter == index) {
                next = current.getNext();
                previous.setNext(next);
                break;
            }

        }
        return (T) current.getData();
    }

    /*loop through the list and each time add the value of each node to the string v. at the end print the string v*/
    public String toString() {
        String v = "";
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
            v += current.getData() + System.lineSeparator();
        }
        return v;
    }


}