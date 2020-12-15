// Names: Hamza Benkhalifa & Rishi Selvakumaran
// x500s: benkh008  && selva053
// Last Edited: May 4th 2020

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
// Class that implements a hash table to store data of NGen type
public class Hash<T> {
    NGen<T>[] array;
    public Hash(){
        array = (NGen<T>[]) new NGen[101];
    }
    //
    public void add(T item) {
        boolean found = false;
        int row = hash(item);
        NGen current = array[row];
        NGen input = new NGen();
        input.setData(item);
        if (current == null) { // if current is an empty space in the array, then the new space is et to be of NGen type
            array[row] = input;
        }
        else {// if current is not an empty space, then search until you find a empty space and add the item to it.
            while (current.getNext() != null) { //
                if (current.getData().equals(item)) { // checks if there are any duplicate strings in the linkedList
                    found = true;
                }
                current = current.getNext();
            }
            if (current.getData().equals(item)) { // If found then then don't add it to the linkedlist
                found = true;
            }
            if (found == false){ // else add it
                current.setNext(input);
            }//Once the item is added to the List then the node is set to point to the next element
        }
    }
    // Loops through each row in the hash table then loop through each linkedList in each row
    // Then print out the strings in each row plus the number of strings in each row
    // Also keeps track of the longest and shortest chains in the hash table
    public void display(){
        int longest = 0;
        int shortest = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                ;
            }
            else {
                String v = "row " + i + " : ";
                NGen current = new NGen();
                current = array[i];
                int length = 0;
                while (current.getNext()!= null) {
                    v += current.getData() + " ";
                    current = current.getNext();
                    length++;
                }
                length++; // used to determine the number of items in the linkedlist
                v+= current.getData() + " " + length;
                System.out.println(v);
                // if the length of the LinkedList is longer than the longest list, set the chain as the longest
                if (length > longest) {
                    longest = length;
                }
                // if this is the first chain, then this is the longest and shortest
                if (i == 0 ) { //
                    shortest = longest;
                }
                // if the chain is shorter than the shortest, then set the chain to be the shortest
                else if( length < shortest) {
                    shortest = length;
                }
            }
        }
        System.out.println(" the longest chain is " + longest);
        System.out.println("the shortest chain is " + shortest);
    }
    //Use this hash function for the most efficient testing
    private int hash(T key){ //The most efficient Hash function that has the fewest longest chain
        // Get the the unicode of each letter in a string add them together add the length of the string and also add
        // the first letter in the string and module all of that by the length of the array
        int number = 1;
        for (int i = 0; i < key.toString().length(); i++) {
            number +=  (int)key.toString().charAt(i) + key.toString().length() + (int)key.toString().charAt(0);
        }
        number =(number)  % (array.length);
        return number;
    }

    private int hash2(T key){
        // Get the length of the string at the specific key
        // and then you multiply the number in the sequence key.toString().charAt(i) *32^(key.string.length-1) for all the strings
        // to form a random unique key number from it by modulating it with the length of the array
        int number = 0;
        int key_length = key.toString().length();
        for (int i = 0; i < key.toString().length(); i++) {
            number += (int)(key.toString().charAt(i)*Math.pow(31,key_length--));
            number = number % (array.length);
        }
        return number;
    }
    private int hash3(T key){
        // For the length of the string at the specified key, Choose a random number between the length of the string
        // and then multiply the number in the sequence key.toString().charAt(i) *32^(key.string.length-1) for all the random numbers
        // Then you try to form a random unique key number from it by modulating it with the length of the array
        Random random = new Random();
        int number = 0;
        int key_length = key.toString().length();
        for (int i = 0; i < key.toString().length(); i++) {
            int select = random.nextInt(key.toString().length());
            number += (int)(key.toString().charAt(select)*Math.pow(31,key_length--));
            number = number % (array.length);
        }
        return number;
    }

    private int hash4(T key){
        // a different version from hash that works almost the same with a slight difference
        // the difference being that we als added the hash code of the ast letter on the string in our hash algorithm
        // this improved the hash table for some text files but made it less efficient for other text files
        int number = 1;
        for (int i = 0; i < key.toString().length(); i++) {
            number +=  (int)key.toString().charAt(i) + key.toString().length() + (int)key.toString().charAt(0) + (int) key.toString().charAt(key.toString().length()-1);
        }
        number =(number)  % (array.length);
        return number;
    }

    // Come up with new hash functions with different algorithm and for 3-4 times

    // no duplicates

    // You want to read the sample files using textScan file and then store the words in the hash table
    // 'for loop' through the file and keep adding the words into it
    // Do this in main method

    public static void main(String[] args) throws FileNotFoundException {
        Hash table = new Hash<>();
        /* The algorithm below to read the file and add it to the hash table
         * was adapted from the TextScan.java file from the course materials
         * */
        String [] filename = new String[2];

        Scanner readFile = null;
        filename[0] = "canterbury.txt";
        String s;
        int count = 0;

        System.out.println();
        System.out.println("Attempting to read from file: " + filename[0]);
        try {
            readFile = new Scanner(new File(filename[0]));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + filename[0] + " not found");
            System.exit(1);
        }
        while (readFile.hasNext()) {
            s = readFile.next();
            table.add(s);
            count++;
        }
        table.display();
    }
}
