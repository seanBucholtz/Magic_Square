import java.util.*;
/**
 * This application allows a user to create a square of an odd valued area that contains
 * a special sequence of values where the sum of the individual rows, columns, and 
 * two diagonal lengths are all of equal value.
 *
 * @version Assignment #1 - Magic Square
 * @author Sean Bucholtz
 */
public class OddMagicSquare {
	
	// declare multidimensional(MD) array for magic square values
	private int[][] mSquare;
	// declare variable for magic square size
	private int n;
	
	/**
	 * Parameterized constructor creates and displays a new magic square with the specified size.
	 * 
	 * @param n the side length of the magic square (<b>must be of odd order</b>)
	 */
	public OddMagicSquare(int n) {
		// if constructor argument is not an odd integer value
		if(n%2 == 0) {
			// throw an error
			throw new UnsupportedOperationException("Unsupported Argument: value must be an odd");
		}
		// assign magic square size
		this.n = n;
		// instantiate a new MD array to the specified size
		mSquare = new int[n][n];
		// fill MD with values
		populateMagicSquare();
		// display magic square
		System.out.println("\n" + toString());
	}
	
	/**
	 * Method populates the array with the magic square values.
	 */
	private void populateMagicSquare() {
		// declare and assign row variable to start location
		int x = n/2;
		// declare and assign column variable to start location
		int y = n-1;
		// create the values contained in the square
		for(int num = 1; num <= n*n; num ++) {
			// insert value into square
			mSquare[x][y] = num;
			// prevents out of bounds exception
			if(num == n*n) {
				break;
			}
			// if value is less than the length of the row
			if(x < n-1) {
				// increment value
				x ++;
			// if the value is the final value in the row
			} else {
				// reset value to beginning of the row
				x = 0;
			}
			// if value is less than the length of the column
			if(y < n-1) {
				// increment value
				y ++;
			// if the value is the final value in the column
			} else {
				// reset value to beginning of the column
				y = 0;
			}
			// while the [x,y] location of the magic square has a value other than 0 
			while(mSquare[x][y] != 0) {
				// if x-value is not the first row
				if(x > 0) {
					// decrement value
					x --;
				// if x-value is the first row
				} else {
					// reset x-value to last row
					x = n-1;
				}
				// if y-value is not the first or second column
				if(y > 1) {
					// subtract 2 from the y-value
					y -= 2;
				// if y-value is the second column
				} else if (y == 1) {
					// reset y-value to end of column
					y = n-1;
				// if y-value is the first column
				} else if (y == 0) {
					// reset y-value to second to last column 
					y = n-2;
				}
			}
		}
	}
	
	/**
	 * Method creates a square shaped string representation of the magic square values.
	 * 
	 *  @return the string representation
	 */
	public String toString() {
		// declare and assign an empty string to return variable 
		String square = "";
		// loop through rows
		for(int row = 0; row < n; row ++) {
			// loop through columns in row
			for(int col = 0; col < n; col ++) {
				// add and concatenate spacing and row/col value to return variable
				square += " " + mSquare[row][col] + " ";
			}
			// add new line to return variable 
			square += "\n";
		}
		// return the complete square
		return square;
	}
	
	/**
	 * Method tests array of magic square values to ensure that they conform to magic 
	 * square specifications
	 * 
	 * @param arr the array being tested
	 * @return returns false if the values do not conform to magic square specifications; 
	 * otherwise, returns true
	 */
	public static boolean isMagic(int[][] arr) {
		// declare and assign general sum to 0
		int gSum = 0;
		// loop through rows
		for(int row = 0; row < arr.length; row ++) {
			// declare and assign row sum to 0
			int sum = 0;
			// loop through column value in row
			for(int col = 0; col < arr[row].length; col ++) {
				// add value to sum
				sum += arr[row][col];
			}
			// if the row being summed is the first row
			if(row == 0) {
				//assign row sum to general sum
				gSum = sum;
			}
			// if row sum is not equal to general sum
			if(sum != gSum)
				return false;
			}
		// loop through columns
		for(int col = 0; col < arr.length; col ++) {
			// declare and assign row sum to 0
			int sum = 0;
			// loop through row value in column
			for(int row = 0; row < arr[col].length; row ++) {
				// add value to sum
				sum += arr[row][col];
			}
			// if column sum is not equal to general sum
			if(sum != gSum) {
				return false;
			}
		}
		// declare and assign left-to-right (L-R) diagonal sum variable to 0
		int sum = 0;
		// declare and assign right-to-left (R-L) diagonal sum variable to 0
		int sum2 = 0;
		// loop diagonally 
		for(int i = 0; i < arr.length; i ++) {
			// add L-R value to L-R sum
			sum += arr[i][i];
			// add R-L value to R-L sum
			sum2 += arr[arr.length-1-i][arr.length-1-i];
		}
		// if either L-R sum or R-L sum does not equal general sum
		if(sum != gSum | sum2 != gSum) {
			return false;
		}
		// if all tests pass
		return true;
		}

	/**
	 * The application method.
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// declare and instantiate new scanner object
		Scanner scan = new Scanner(System.in);
		// declare input sentinel variable and assign arbitrary value 
		int num = 2;
		// welcome user
		System.out.println("Welcome to Magic Square!\n");
		// while input sentinel is not equal to 0
		while(num != 0) {
			// display instructions
			System.out.print("Please enter an odd whole number or 0 to quit: ");
			// prompt user for input
			input:
			while(scan.hasNext()) {
				// if user input is not an integer value
				while(!scan.hasNextInt()) {
					// remove junk value from input stream
					String junk = scan.next();
					// notify user of error
					System.out.println("\n\"" + junk + "\" is not an odd whole number.\n");
					System.out.print("Please enter an odd whole number or 0 to quit: ");
				}
				// assign valid user input input sentinel
				num = scan.nextInt();
				if(num != 0) {
					// if input is not odd
					if(num%2 == 0) {
						// notify user of error
						System.out.println("\n\"" + num + "\" is not an odd whole number.\n");
						// exit current input sequence
						break input;
					}
					// instantiate new magic square
					new OddMagicSquare(num);
					// exit current input sequence
					break input;
				// if exit command is entered	
				} else {
					break;
				}
			}
		}
		// close scanner object
		scan.close();
		// exit message
		System.out.println("\nThank you for using Magic Square. Goodbye!");
	}
}
