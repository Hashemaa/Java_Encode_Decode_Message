/**
 * Program Name: H_A_Regular_Caesar.java
 * Purpose: This program will allow the user to either encipher/encode plaintext into a coded message, or read an enciphered message and decipher/decode it into
 * 					plaintext. All under a keyword used to determine the shift value.
 * Coder: Hashem Abou Ahmad	-	0961218
 * Date: Mar. 18, 2020
 * 
 * PSEUDOCODE:
 * 1) Title program.
 * 2) Explain program.
 * 3) Create a global Scanner to be created once and used multiple times in different methods throughout the program.
 * 4) Create a method to validate data between two integers.
 * 			• Accepts two integers as parameters and one String as instructions to be used in the loop and returns either of the two integer parameters.
 * 		  • Checks if user's input is either one of the integer parameters and returns the user's selection either the first integer parameter or the second integer
 * 				parameter ONLY.
 * 5) Create a method to either encipher (1) or decipher (2) then invoke that method.
 * 			• Prompt user to enter 1 to encipher or 2 to decipher.
 * 			• Invoke a method to validate user's entry. If user's entry is the first or second parameter, return that value. If user's entry is anything else other than 
 * 				the first or second parameter, prompt user to re-enter.
 *			• If first or second parameter is returned, execute an enciphering or deciphering from keyboard or file process method.
 * 6) Create a method to execute the enciphering process and a method to execute the deciphering from keyboard or file process method.
 * 7) Create a method to convert lower case characters to UPPER CASE characters WITHOUT USING THE toUpperCase() method!
 * 			• Accepts a String.
 * 			• Checks if that String has lower case characters and convert these lower case characters to UPPER CASE characters.
 * 			• Returns the new UPPER CASE String.
 * 8) Create a method to prompt user to enter a keyword of only letters, with no digits, spaces, or punctuation marks.
 * 			• Stores user's input in a String variable.
 * 			• Checks if user's input is blank, if so, explains the issue and re-ask the user to enter a keyword.
 * 			• Converts user's input to UPPER CASE characters by using step (6) method.
 * 			• Checks if characters entered are between the ASCII value greater or equals to 65 'A' or less than or equals to 90 'Z' and returns the keyword to the
 * 				catcher variable if true.
 * 9) Create a method to calculate the shift value of a passed keyword, then returns the shift value.
 *  		• Creates a variable of type String to hold the passed text that got converted to UPPER CASE characters using the step (6) method.
 *  		• Adds the ASCII code of each character in the passed text and stores it in a accumulator variable using a loop.
 *  		• Assigns the summation of the ASCII values generated % 26 to the shift value.
 *  		• Checks if the summation of the ASCII values is a multiple of 26 and if so, adds 1 to the shift value to avoid a shift value of 0.
 *  		• Returns the shift value to the catcher variable of type int.
 * 10) Create a method to encipher and a method to decipher text.
 * 			• Accepts two parameters, a String and an integer.
 *  		• Creates a loop that reads every character in the text.
 *  		• Creates a variable of type char to hold the text's index character and store it in the variable.
 *  		• Checks if the character stored in the variable is a letter.
 *  		• Checks if the character value is greater to 'Z' which holds the ASCII code 90 if enciphering. OR less than 'A' (65) if deciphering.
 * 			• Creates a formula to wrap back to the beginning or to the end depending on enciphering or deciphering.
 * 			• Checks if it is a letter and applies the formula, otherwise just adds it as it is to the String variable.
 * 			• Returns the ciphertext or plaintext.
 * 11) Create a method that creates a file in the project folder then writes text to that file created in the project folder.
 * 			• Accepts two String parameters, file name that will be created, and the text that will be written to that file.
 * 			• Creates a File object and passed it the file name desired.
 * 			• Creates a FileWrite object that creates a file depending on the file name.
 * 			• Creates a PrintWriter object that will be used to write in the file created.
 * 			• Writes the text passed to this method into the file created.
 * 
 * 11) If enciphering process is called:
 * 			• Prompt user to enter the plaintext they wish to encode.
 * 			• Store user's input in a String variable.
 * 			• Check if input is not blank. If blank, explain the issue then recall the same method to start over. If not blank, proceed.
 * 			• Pass user's plaintext to a method to return the same text but in UPPER CASE characters.
 * 			• Invoke a keyword method to prompt user to enter a keyword of specific restrictions, validates the entry, then return it to a catcher variable of type String.
 * 			• Pass the keyword to a method that will calculate the shift value of that keyword, then return the shift value to a catcher variable of type int.
 *			• Pass the UPPER CASE plaintext and the shift value to a encipher method that will shift every character number of times to the right depending on the 
 *				calculated shift value from the keyword, then return the ciphertext to a catcher variable of type String.
 *			• Outputs the UPPER CASE plaintext and the UPPER CASE ciphertext.
 *			• Prompt user to enter a file name with the extension to create and save the ciphertext to that file.
 *			• Checks if file name is blank, if so returns false and the loop will execute again, if not blank it will break out of
 *				the loop.
 *			• Invoke a method that creates the file named after user's choice and write the ciphertext to that file.
 *			• Output a message that informs the user that the ciphertext was written to the file.
 *
 *    If deciphering from keyboard/file process is called:
 *    	• Prompt user to enter 1 to decipher via keyboard or 2 to decipher via a file.
 *    	• Invoke a method to validate user's entry. If user's entry is the first or second parameter, return that value. If user's entry is anything else other than 
 * 				the first or second parameter, prompt user to re-enter.
 * 			• If first or second parameter is returned, execute a decipher process or a decipher from reading a file process method.
 * 					
 * 					If deciphering process via keyboard is called:
 * 						• Prompt user to enter the ciphertext they wish to decode.
 * 						• Store user's input in a String variable.
 * 						• Check if input is not blank. If blank, explain the issue then recall the same method to start over. If not blank, proceed.
 * 						• Pass user's ciphertext to a method to return the same text but in UPPER CASE characters.
 * 						• Invoke a keyword method to prompt user to enter a keyword of specific restrictions, validates the entry, then return it to a catcher variable of 
 * 							type String.
 * 						• Pass the keyword to a method that will calculate the shift value of that keyword, then return the shift value to a catcher variable of type int.
 *						• Pass the UPPER CASE ciphertext and the shift value to a decipher method that will shift every character number of times to the left depending on the 
 *							calculated shift value from the keyword, then return the plaintext to a catcher variable of type String.
 *						• Outputs the UPPER CASE ciphertext and the UPPER CASE plaintext.
 *
 *					If deciphering process via a file is called:
 *						• Prompt user to enter the file name that contains the ciphertext.
 *						• Store user's input in a String variable.
 *						• Check if input is not blank. If blank, explain the issue then recall the same method to start over. If not blank, proceed.
 *						• Create a File object that will pass to the Scanner.
 *						• Create a Scanner object that holds the File object reference.
 *						• Create a loop that will add characters from the file to the ciphertext String.
 *						• Close the Scanner object.
 *						• Catch the FileNotFoundException.
 *						• Pass the ciphertext to a method that will return the text in UPPER CASE characters (Just to make sure that the text is in UPPER CASE characters).
 *						• Return the UPPER CASE ciphertext to the catcher variable of type String.
 *						• Check if file contents is empty, if so, explains the issue and invokes the deciphering from keyboard/file process method to make sure user wanted to
 *							either decipher via keyboard or file. If file had contents, proceed.
 *						• Pass the keyword to a method that will calculate the shift value of that keyword, then return the shift value to a catcher variable of type int.
 *						• Pass the UPPER CASE ciphertext and the shift value to a decipher method that will shift every character number of times to the left depending on the 
 *							calculated shift value from the keyword, then return the plaintext to a catcher variable of type String.
 *						• Outputs the UPPER CASE ciphertext and the UPPER CASE plaintext.
 * 
 * 12) Close the input Scanner in the helper class.
 * 13) Outputs a "End of program" indicating that the program has successfully executed all the way.
 */
public class HA_Regular_Caesar
{
	
	public static void main(String[] args)
	{	
		//Declares and assigns a CONSTANT boolean variable to true.
		final boolean IS_SUPER_CAESAR = false;
		//Titles the program.
		System.out.println("Prototype for a Caesar Shift Encoding Application.");
		//Explains program.
		System.out.println("\nThis program will help you encipher a message or decipher a coded message.");
		
		//Calls the method encipherOrDecipher() in the HA_Project_Methods class that asks the user to enter 1 to encipher or 2 to decipher a message, then validates user's input.
		//Passes the boolean variable 'IS_SUPER_CAESAR' to check if the program is seeking the Super Caesar program or the Regular Caesar program.
		HA_Project_Methods.encipherOrDecipher(IS_SUPER_CAESAR);

		//Closes the input Scanner in the helper class.
		HA_Project_Methods.input.close();

		//Checks if the program successfully reached this point.
		System.out.println("\nEnd of program.");
	}//End main.
	
}//End class.