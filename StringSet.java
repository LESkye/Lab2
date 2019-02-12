/**
 * @author Justin Eldridge
 * 
 */
public class StringSet {

	// Defining instance variables
	private String[] list;
	private int size;
	private int capacity;
	
	
	/**
	 * No arguments constructor. Sets the StringSet capacity to 2.
	 * 
	 */
	public StringSet() {
		capacity = 2;
		list = new String[capacity];
		size = 0;
	}
	
	
	/**
	 * Capacity argument constructor. Sets the StringSet capacity to _capacity.
	 * 
	 * @param _capacity The size for the StringSet to be set to. 
	 */
	public StringSet(int _capacity) {
		// Will only run if capacity is a positive value
		if (capacity < 0) 
			throw new IllegalArgumentException ("The initial capacity is negative: " + capacity);
			
		capacity = _capacity;
		list = new String[capacity];
		size = 0;
	}
	
	
	/**
	 * Copy constructor. The new StringSet will copy all the variables of given StringSet.
	 * 
	 * @param obj the StringSet to be copied.
	 * <dt><b>Precondition:</b><dd>
	 * obj is an instance of a StringSet and is not null. 
	 */
	public StringSet(StringSet obj) {
		if (obj != null) {
			if (obj instanceof StringSet) {
				size = obj.size();
				capacity = obj.capacity();
				list = new String[capacity];
				for(int i = 0; i < capacity; i++) {
					String copy = obj.getListElement(i);
					list[i] = copy;
				}
			}
		}
	}
	
	
	/**
	 * Assessor of StringSet List size.
	 * 
	 * @return 
	 * the size (how many Strings are in the StringSet).
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * Assessor of StringSet List size.
	 * 
	 * @return the capacity (how long the StringSet List is).
	 */
	public int capacity() {
		return capacity;
	}

	
	/**
	 * Will find the String at location i.
	 * 
	 * @param i the location of the string you want to find.
	 * @return 
	 * the String at location i in List.
	 */
	public String getListElement(int i) {
		return list[i];
	}
	
	
	/**
	 * Will add a string at the next available spot. If list is full it will double list's size then double it.
	 * 
	 * @param a the String to be added.
	 * <dt><b>Precondition:</b><dd>
	 * a is not null.
	 * <dt><b>Postcondition:</b><dd> 
	 * String a is added to List. If size would exceed capacity, capacity is doubled. 
	 */
	public void add(String a) {
		if(a != null) {
			if(size >= capacity)
				ensureCapacity(capacity*2);
			
			for (int i = 0; i < size; i++) {
				if(list[i] == a)
					this.remove(list[i]);
			}
			list[size] = a;
			size++;
		}
	}

	
	/**
	 * Will check if List contains String a.
	 * 
	 * @param a the String to be searched for.
	 * <dt><b>Postcondition:</b><dd> 
	 * checks for String a in List.
	 * @return 
	 * True if the String is found. False if a is null, or not found.
	 */
	public boolean contains(String a){
		if(a != null) {
			for(int i = 0; i < capacity; i++) {
				if(list[i] == a)
					return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Will remove String x from List, if it exists.
	 * 
	 * @param x the String to be removed.
	 * <dt><b>Postcondition:</b><dd> 
	 * x will be removed from List, if it was found.
	 * @return 
	 * True if String x was removed. False if it was not found or is null. 
	 */
	public boolean remove(String x) {
		if(x != null) {
			for (int i = 0; i < list.length; i++) {
				if(list[i] == x) {
					list[i] = list[size - 1];
					list[size - 1] = null;
					size--;
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Will make sure capacity is at least as big as minimumCapacity.
	 * 
	 * @param minimumCapacity the minimum allowed Capacity of List.
	 * <dt><b>Postcondition:</b><dd> 
	 * List will be expanded to minCap if current capacity is lower.  
	 */
	public void ensureCapacity(int minimumCapacity) {
		if(capacity < minimumCapacity) {
			String[] tmp = new String[minimumCapacity];
			for(int i = 0; i < capacity; i++) {
				tmp[i] = list[i];
			}
			list = tmp;
			capacity = minimumCapacity;
		}
	}
	
	
	/**
	 * Will return a String version of the StringSet.
	 * 
	 * <dt><b>Postcondition:</b><dd> 
	 * creates a string version of StringSet.
	 * @return 
	 * the String Version of StringSet.
	 */
	public String toString() {
		String toString = "Capacity: " + capacity + " Size: " + size + " List: " + String.join(", ", list);
		return toString;
	}
	
	
	/**
	 * Will add String a so that the elements of list are ordered ascending.
	 * 
	 * @param a the String to be added.
	 * @precondition 
	 * a is not null.
	 * <dt><b>Precondition:</b><dd> List is already ordered ascending.
	 * <dt><b>Postcondition:</b><dd>  String a will be added so the list is ordered ascendingly. If capacity is too small it will be doubled.
	 */
	public void addOrdered(String a) {
		if(a != null) {
			if(size >= capacity)
				this.ensureCapacity(capacity*2);
			for (int i = 0; i < capacity; i++) {
				if(list[i] == null) {
					list[i] = a;
					size++;
					break;
				}
				else if(a.compareTo(list[i]) == 0) {
					break;
				}
				else if(a.compareTo(list[i]) < 0) {
					
					String[] tmp = new String[this.capacity];
					for(int j = 0; j < i; j++) {
						tmp[j] = list[j];
					}
					tmp[i] = a;
					for(int k = i+1; k < capacity; k++) {
						tmp[k] = list[k-1];
					}
					list = tmp;
					size++;
					break;
				}
			}
		}
	}
	
	
	public static void main(String args[]) {
		//Testing the no parameters constructor
		StringSet testSet1 = new StringSet();
		System.out.println(testSet1);
		System.out.println();
		
		//testing the capacity set constructor
		StringSet testSet2 = new StringSet(5);
		System.out.println(testSet2);
		System.out.println();
		
		//testing the add function
		testSet1.add("Hello");
		System.out.println(testSet1);
		System.out.println();
		
		//seeing if the add function will double the capacity
		testSet1.add("World");
		testSet1.add("!");
		System.out.println(testSet1);
		System.out.println();
		
		//testing the copy constructor
		StringSet testSet3 = new StringSet(testSet1);
		System.out.println(testSet3);
		System.out.println();
		
		//testing to see if a string is added to test1 if it will affect the copied test3
		testSet1.add("How are you?");
		System.out.println(testSet1);
		System.out.println(testSet3);
		System.out.println();
		
		//creating an ordered string, to test addOrdered
		for(int i = 1; i <= 8; i=i+2) {
			String temp = "Hello" + i;
			testSet2.add(temp);
		}
		System.out.println(testSet2);
		System.out.println();
		
		//testing add ordered, adding elements to the beginning, middle, and end. Capacity should double to 10
		testSet2.addOrdered("Hello0");
		testSet2.addOrdered("Hello4");
		testSet2.addOrdered("Hello8");
		System.out.println(testSet2);
		System.out.println();
		
		//testing ensure capacity
		System.out.println(testSet3.capacity());
		testSet3.ensureCapacity(9);
		System.out.println(testSet3.capacity());
		System.out.println();
		
		//testing the contains method
		if(testSet1.contains("Hello"))
			System.out.println("testSet1 contains Hello.");
		System.out.println();
		
		//testing the removes method
		if(testSet1.remove("Hello"))
			System.out.println("Hello was removed from testSet1.");
		System.out.println();
		
		//Making sure hello is no longer in testSet1
		if(testSet1.contains("Hello"))
			System.out.println("testSet1 contains Hello.");
		System.out.println(testSet1);
		System.out.println();
		
		//Trying to add a String already contained in testSet1 to testSet1 again.
		testSet1.add("!");		
		System.out.println(testSet1);
		System.out.println();
		
		//Trying to use add ordered to add a string already in testSet2
		testSet2.addOrdered("Hello5");
		System.out.println(testSet2);
	}
}