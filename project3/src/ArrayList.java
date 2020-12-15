// Written by selva053 and benkh008
// CSCI 1933
// Project 3
// ArrayList Class

public class ArrayList<T extends Comparable<T>> implements List<T> {
	private T[] a;
	private T[] b;
	private  Boolean isSorted = false;

	//Constructor method
	public ArrayList() {
		a = (T[]) new Comparable[2];	//new array with size of 2 initialized
	}
	// Main Method
	public static void main(String[] args) {
		ArrayList arraylist = new ArrayList();
		System.out.println(arraylist);
	}
	/*
	 * Add an element to end of the list. If element is null,
	 * it will NOT add it and return false.  Otherwise, it
	 * will add it and return true. Updates isSorted to false.
	 */

	public boolean add(T element){	//Method to add element to the array
		if (element == null){
			return false;
		}
		int emptyspace = 0;
		for (int i = 0; i < a.length-1; i++) {//Checks for empty space in the Array
			if(a[i] == null){ //if empty, then adds to number of emptyspaces
				emptyspace++;
			}
		}
		if (emptyspace == 0){//if no emptyspaces in array
			growArray();	//Then calls the growArray
		}

		for (int i = 0; i < a.length-1; i++) {	//Checks for empty slot in the array following the first element to add the incoming element
			if(a[i] == null){
				a[i] = element;
				break;	//break the loop after adding the element
			}
		}
		isSorted = false;
		return true;
	}

	private void growArray(){// method used to grow and copy array
		b = (T[]) new Comparable[a.length*2];	//creates a new array to twice the length of old array
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i];	//Copies elements from old array to new array
		}
		a = b;	// sets the new array to the existing array
	}

	/*
	 *  Add an element at specific index. This method should
	 * also shift the element currently at that position (if
	 * any) and any subsequent elements to the right (adds
	 * one to their indices). If element is null, or if index
	 * index is out-of-bounds (index < 0 or index >= size_of_list),
	 * it will NOT add it and return false. Otherwise it will
	 * add it and return true. See size() for the definition
	 * of size of list. Also updates isSorted variable to false.
	 */
	public boolean add(int index, T element){
		if (element == null || index < 0 || index >= size()){	// This statement is explained above
			return false;
		}
		int emptyspace = 0; // variable used for finding empty spaces in array
		for (int i = 0; i < a.length; i++) {//Loop Checks for empty space in the Array
			try{
				if(a[i] == null){ //if empty, then adds to number of emptyspaces
					emptyspace++;
				}
			}
			catch (NullPointerException e){ // Try and catch block used to prevent Nullpointer Exception Error
				continue;
			}
		}
		if (emptyspace == 0){//if there are no emptyspaces in array
			growArray();	// Then call the growArray method and grow the arrayspaces
		}
		for (int i = a.length-1; i > index; i--) { // shift the existing elements in the array down
			a[i] = a[i-1];
		}
		a[index] = element;// assign the element at the specified index
		isSorted = false; // set isSorted to be false
		return true;
	}



	/*
	 * Remove all elements from the list.
	 */
	public void clear(){

		for (int i = 0; i < a.length; i++) {
			a[i] = null;
		}

	}

	/*
	 * Return true if element is in the list and false
	 * otherwise. If isSorted is true, uses the ordering
	 * of the list to increase the efficiency of the search.
	 */
	public boolean contains(T element){
		for (int i=0; i<a.length; i++){
			try{
				if (a[i].equals(element)){
					return true;
				}
			}
			catch(NullPointerException e){ // Try and catch block used to prevent Nullpointer Exception Error
				continue;
			}
		}
		return false;
	}

	/*
	 *  Return the element at given index. If index is
	 * out-of-bounds, it will return null.
	 *
	 */
	public T get(int index){
		if ((index >a.length) || (index<0)){
			return null;
		}
		return a[index];
	}

	/*
	 * Return the first index of element in the list. If element
	 * is null or not found in the list, return -1. If isSorted is
	 * true, uses the ordering of the list to increase the efficiency
	 * of the search.
	 */
	public int indexOf(T element){
		if (element == null){ // If element is null the array will return -1
			return -1;
		}
		for (int i=0; i<a.length; i++){
			try{
				if (a[i].equals(element)){
					return i;	//returns the index where the element is located
				}
			}
			catch(NullPointerException e){ // Try and catch block used to prevent Nullpointer Exception Error
				continue;
			}
		}
		return -1;	// If the element is not found the method will return -1
	}

	/*
	 * Return true if the list is empty and false otherwise.
	 */
	public boolean isEmpty(){
		for (int i=0; i<a.length; i++){
			try{
				if (!a[i].equals(null)){	//If there is a element present in the array it will return false
					return false;
				}
			}
			catch(NullPointerException e){ // Try and catch block used to prevent Nullpointer Exception Error
				continue;
			}
		}
		return true;
	}

	/*
	 * Same as indexOf(), except it will return the last index
	 * of element. If isSorted is true, uses the ordering
	 * of the list to increase the efficiency of the search.
	 */
	public int lastIndexOf(T element){
		for (int i=a.length-1; i>=0; i--){
			try{
				if (a[i].equals(element)){
					return i;
				}
			}
			catch(NullPointerException e){	// Try and catch block used to prevent Nullpointer Exception Error
				continue;
			}
		}
		return -1;
	}

	/*
	 * Replace the element at index with element and return the
	 * element that was previously at index. If index is
	 * out-of-bounds or element is null, do nothing with element
	 * and return null.
	 */
	public T set(int index, T element){
		if ((index >= 0) && (index < a.length) && (element != null)){
			T p_ele = b[index];
			b[index] = element;
			return p_ele;
		}
		return null;
	}

	/*
	 * Return the number of elements in the list. For example, if
	 * 4 elements added to a list, size will return 4, while the
	 * last index in the list will be 3. Updates isSorted.
	 */
	public int size(){
		int count = 0;
		for (int i=0; i<a.length; i++){
			try{
				if (!a[i].equals(null)){ //if it not null
					count++;
				}
			}
			catch(NullPointerException e){ // Try and catch block used to prevent Nullpointer Exception Error
				continue;
			}
		}
		return count;
	}

	/*
	 * Sort the elements of the list. If order is true, sort the
	 * list in increasing (alphabeticaly) order. If order is
	 * false, sort the list in decreasing (reverse alphabetical)
	 * order. Note: only set isSorted to true if sorted in ASCENDING
	 * order.
	 * If isSorted is true, and the order is true, do NOT resort.
	 * Hint: Since T extends Comparable, you will find it useful
	 * to use the public int compareTo(T o) method.
	 */
	public void sort(boolean order){
		if(!isSorted){	// only initiate method if the is Sorted is false
			if (order){
				for (int i = 0; i < a.length - 1; i++) {
					for (int j = 0; j < a.length -i - 1; j++) {
						try {
							if (a[j].compareTo(a[j + 1]) > 0) { // Uses Bubble sort method to sort the list alphabetically
								T temp = a[j];
								a[j] = a[j + 1];
								a[j + 1] = temp;
							}
						} catch (NullPointerException e) { // Try and catch block used to prevent Nullpointer Exception Error
							continue;
						}
					}
				}
			}
			if (!order){
				for (int i = 0; i < a.length - 1; i++) {
					for (int j = 0; j < a.length -i - 1; j++) {
						try{
							if (a[j].compareTo(a[j+1]) < 0) {
								T temp = a[j];
								a[j] = a[j+1];
								a[j+1] = temp;
							}
						}
						catch(NullPointerException e){ // Try and catch block used to prevent Nullpointer Exception Error
							continue;
						}
					}
				}
			}
		}
		isSorted = true;
	}

	/*
	 * Remove the first instance of element from the list. This
	 * method should also shifts any subsequent elements to the
	 * left (subtracts one from their indices). If successful,
	 * return true. If element is not found in the list, return
	 * false.
	 */
	public boolean remove(T element){
		for (int i = 0; i < a.length-1; i++) {
			try{
				if (a[i].equals(element)){// Once when the element is found in the array, then shift the existing elements up in array
					for (int j = i; j < a.length-1; j++) {	// for loop is used to shift the existing elements in the array up
						a[j] = a[j+1];
					}
					return true;
				}
			}
			catch(NullPointerException e){ // Try and catch block used to prevent Nullpointer Exception Error
				continue;
			}
		}
		return false;
	}

	/*
	 * Remove whatever is at index index in the list and return
	 * it. If index is out-of-bounds, return null. Shift the
	 * indices as in the other remove.
	 */
	public T remove(int index){
		if (index < 0 || index >=a.length) {
			return null;
		}
		T temp = a[index];
		a[index] = null;	//set the existing element to be null
		for (int j = index; j < a.length-1; j++) {	// shifts the existing elements up
			a[j] = a[j+1];
		}
		return temp;
	}

	/*
	 * Note that this method exists for debugging purposes.
	 * It calls the toString method of the underlying elements in
	 * the list in order to create a String representation of the list.
	 * The format of the toString should appear as follows:
	 * Element1
	 * Element2
	 * .
	 * .
	 * .
	 * Elementn
	 * Watch out for extra spaces or carriage returns. Each element
	 * should be printed on its own line.
	 */
	public String toString(){
		String array = "";
		for (int i = 0; i < a.length; i++) {
			array += a[i] + "\n";
		}
		return array;
	}
}