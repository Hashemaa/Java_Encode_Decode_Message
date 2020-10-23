/**
 * Program Name: HA_Super_Caesar.java
 * Purpose: put something descriptive here!
 * Coder: Hashem Abou Ahmad	-	0961218
 * Date: Apr. 3, 2020
 */

public class HA_Super_Caesar
{
	
	public static void main(String[] args)
	{	
		//Declares and assigns a CONSTANT boolean variable to true.
		final boolean IS_SUPER_CAESAR = true;
		//Titles the program.
		System.out.println("Prototype for a Super Caesar Shift Encoding Application.");
		//Explains program.
		System.out.println("\nThis program will help you encipher a message or decipher a coded message using the Super Caesar Shift.");
		
		//Calls the method encipherOrDecipher() in the HA_Project_Methods class that asks the user to enter 1 to encipher or 2 to decipher a message, then validates user's input.
		//Passes the boolean variable 'IS_SUPER_CAESAR' to check if the program is seeking the Super Caesar program or the Regular Caesar program.
		HA_Project_Methods.encipherOrDecipher(IS_SUPER_CAESAR);

		//Closes the input Scanner in the helper class.
		HA_Project_Methods.input.close();

		//Checks if the program successfully reached this point.
		System.out.println("\nEnd of program.");
	}//End main.
	
}//End class.