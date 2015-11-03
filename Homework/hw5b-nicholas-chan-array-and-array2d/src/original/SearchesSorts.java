package original;
/**
 * Illustrates the required search and sort methods for IB common core.
 * 
 * @author Richard 
 * @version 041003
 */
public class SearchesSorts
{
	// basic data array
	private static final int MAX = 998;
	private static final int INTERVAL = 6;
	private static final int SIZE = 16;
	private int[] data = new int[SIZE];

	/**
	 * Constructor
	 */
  public SearchesSorts()
  {
    // comment out any un-needed method calls
    fillData(data);
    // (for binary search) fillSorted(data);
    showData(data);
    // bubbleSort(data);
    // selectionSort(data);
    insertionSort(data);
    showData(data);
    // (for search) int wanted = inputInt("Please input the item to search for: ");
    // linearSearch(data, wanted);
    // binarySearch(data, wanted);
  }
  /**
   * This method sorts the array data
   * 
   * @param the array to be sorted
   */
  private void bubbleSort(int[] data)
  {
    output("bubble sorting...");
    // top is the boundary between the sorted and unsorted portion
    for (int top = (SIZE - 1); top > 0; top = top - 1)
    {
      for (int upper = 1; upper <= top; upper = upper + 1)
      {
        // upper and lower are the neighbours being compared
        int lower = upper - 1; 
        if (data[upper] < data[lower])
        {
          //swap
          int temp = data[upper];
          data[upper] = data[lower];
          data[lower] = temp;
        }
      }
    }
  }
  /**
   * This method sorts the array data
   * 
   * @param the array to be sorted
   */
  private void selectionSort(int[] data)
  {
    output("selection sorting...");
    // pos is the boundary between sorted and unsorted parts of the array
    for (int pos = 0; pos < SIZE; pos = pos + 1)
    {
      // find the position of the smallest value in the unsorted part
      // then swap it with the value at the current position
      int smallPos = minValueAt(data, pos, SIZE - 1);
      int temp = data[smallPos];
      data[smallPos] = data[pos];
      data[pos] = temp;
    }
  }
/**
   * This method finds the element number of the smallest value in the 
   * array data between elements start and end
   * 
   * @param the array to be examined
   * @param the start element
   * @param the end element
   */
    private int minValueAt( int[] data, int start, int end)
    {
      int minSoFar = data[start];  // mimimum value found so far
      int minPos = start;          // position of minSoFar in array
      
      for(int pos = start + 1; pos <= end; pos = pos + 1)
      {
          if (data[pos] < minSoFar)
          {
            // found a new mimimum
            minSoFar = data[pos];
            minPos = pos;
          }
      }
      return minPos;
    }
  /**
   * This method sorts the array data
   * 
   * @param the array to be sorted
   */
  private void insertionSort(int[] data)
  {
    output("insertion sorting...");
	int cp = 0; // current position - value in unsorted list being examined
	int pt;     // pointer to number in sorted part being compared with cp
	int tm;     // temporary store for number to be inserted
	
	while ( cp != (SIZE - 1) )  //while not at end of array
	{
		cp = cp + 1;     // increment current position to first in unsorted
		pt = cp;         // pointer into sorted part - starts at top
        tm = data[cp];   // temp store for element to insert
        // while not at start, and next value is still too big,
        // shuffle up current element by 1
		while ( (pt > 0) && (data[pt-1] > tm) ) 
		{
		  data[pt] = data[pt - 1];
		  pt = pt - 1; 
		}
		// insert the temp value into the sorted part
		data[pt] = tm;
    }
  }
  /**
   * This method fills the array data with random ints
   * 
   * @param the array to be filled
   */
  private void fillData(int data[])
  {
    // Puts random values from 1 to MAX into the array		
	for (int i = 0; i < SIZE; i = i + 1)
	{
		data[i] = (int) (Math.random() * MAX) + 1;
	}
  }
  /**
   * This method fills the array data with random ints
   * 
   * @param the array to be filled
   */
  private void fillSorted(int data[])
  {
    // Puts ordered random values into the array
    data[0] = (int) (Math.random() * INTERVAL) + 1;
	for (int i = 1; i < SIZE; i = i + 1)
	{
		data[i] = data[i - 1] + (int) (Math.random() * INTERVAL) + 1;
	}
  }
  /**
   * This method shows the array contents on the screen
   * 
   * @param the array to be shown
   */
  private void showData(int data[])
  {
    StringBuffer line = new StringBuffer(SIZE*5);
    for (int i = 0; i < SIZE; i = i + 1)
	{
	  line.append(data[i] + " ");
	}
	output(new String(line));
  }
  /**
   * This method carries out a linear (sequential) search by examining
   * each item in the array in turn
   * 
   * @param the array to be searched
   * @param the wanted item
   */
  private void linearSearch(int data[], int wanted)
  {
    boolean found = false;  // flag to indicate end of search
    int pos = 0;            // current position in array
    
    while ( (pos < SIZE) && !found )
    {
      found = (data[pos] == wanted);
      pos = pos + 1;
    }
    if (found)
    {
       // NB: gives position in the list rather than array element
       //     (which would be (pos-1))!
       
       output("Found the item at position: " + pos);
    }
    else
    {
      output("the item " + wanted + " was not found.");
    }
  }
  /**
   * This method carries out a binary search by examining the mid element
   * in the list and then discarding half.
   * 
   * @param the array to be searched
   * @param the wanted item
   */
  private void binarySearch(int data[], int wanted)
  {
    boolean found = false;  // flag to indicate end of search
    int start = 0;          // first element of part to be searched
    int end = (SIZE - 1);   // last element of part to be searched
    
    // loop until found or no more elements to search
    while ( (end >= start) && !found )
    {
      // find mid point of current list
      int mid = (start + end) / 2;
      found = (data[mid] == wanted);
      if (found)
      {
        output("Found the item at element: " + mid);
      }
      else
      {
        // discard half of the array and try again
        if (data[mid] < wanted)
        {
          start = mid + 1;
        }
        else
        {
          end = mid - 1;
        }
      }
    }
    if (!found)
    {
      output("the item " + wanted + " was not found.");
    }
  }
  	/**
	 * IBIO methods, (c) International Baccalaureate 2003
	 * Computer Science Subject Guide, Appendix 2.
	 */
	
	 static void output(String info) {  System.out.println(info); }
     static void output(double info) {  System.out.println(info); }
     static void output(int info)    {  System.out.println(info); }
  
     static int inputInt(String Prompt)
     {  
         int result=0;
         try{result=Integer.parseInt(input(Prompt).trim());}
         catch (Exception e){result = 0;}
         return result;
     }
     static double inputDouble(String Prompt)
     {  
         double result=0;
         try{result=Double.valueOf(input(Prompt).trim()).doubleValue();}
         catch (Exception e){result = 0;}
         return result;
     }
     static String input(String prompt)
     {  
         String inputLine = "";
         System.out.print(prompt);
         try
         { 
             java.io.InputStreamReader sys = new java.io.InputStreamReader(System.in);
             java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
             inputLine = inBuffer.readLine();
         }
         catch (Exception e)
         { 
             String err = e.toString();
             System.out.println(err);
         }
         return inputLine;
      }
      static String input() { return input("");  }
}
