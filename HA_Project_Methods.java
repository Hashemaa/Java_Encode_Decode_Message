/**
 * Program Name: HA_Project_Methods.java
 * Purpose: This class stores all the methods used for the Caesar project, HA_Regular_Caesar.java
 * Coder: Hashem Abou Ahmad	-	0961218
 * Date: Mar. 28, 2020
 */

//Imports Scanner class from the utility package.
import java.util.Scanner;
import java.io.*;
public class HA_Project_Methods
{

	//Creates a global Scanner to be created once and used multiple times in different methods.
	static Scanner input = new Scanner(System.in);
	
	/**
	 * Method Name: encipherOrDecipher()
	 * Purpose: A public class method that checks if the user chose to either encipher or decipher a message by accepting only 1 or 2 indicating encipher or decipher.
	 * Accepts: A boolean type variable that will be used to determine if the program is seeking the Regular or Super Caesar Shift.
	 * Returns: Nothing.
	 * Date: March 28, 2020
	 */
	
	public static void encipherOrDecipher(boolean IS_SUPER_CAESAR) {
		
		//Creates a CONSTANT for encipher selection value and decipher selection value.
		final int ENCIPHER = 1;
		final int DECIPHER = 2;
		
		//Creates a String that holds the instructions that will be passed to the validateData() method.
		String instructions = "\nIf enciphering, enter 1 and press ENTER. \n"
												+ "If deciphering, enter 2 and press ENTER. \n"
												+ "Enter 1 or 2: ";
		
		//Creates an integer type variable to hold the integer returned value. Invokes the validateData() method and pass in the parameters.
		int userInput = HA_Project_Methods.validateData(ENCIPHER, DECIPHER, instructions);
		
		//Checks if user's input is either encipher or decipher.
		if(userInput == ENCIPHER || userInput == DECIPHER) {
			//Checks if the returned value is to encipher or decipher.
			if(userInput == ENCIPHER) {
				//Calls encipherProcess() method to run.
				HA_Project_Methods.encipherProcess(IS_SUPER_CAESAR);
				}else {
					//Calls the decipherKeyboardOrFile() method to run.
					HA_Project_Methods.decipherKeyboardOrFile(IS_SUPER_CAESAR);
					}//End of inner trailing-else.
			
			}//End of outer if block.
		
	}//End encipherOrDecipher() method.
	
	
	/**
	 * Method Name: validateData()
	 * Purpose: A public class method that validates integer entry of either 1 or 2.
	 * Accepts: Two integer parameters and one String parameter.
	 * Returns: An integer after validation.
	 * Date: March 28, 2020
	 */
	
	public static int validateData(int num1, int num2, String instructions) {
		
		//Creates a boolean type variable to be used in the LCC.
		boolean num1OrNum2;
		//Creates a int type variable to hold the final value of the return.
		int returnValue = 0;
		//Creates an integer type variable to hold user's input.
		int userInput;
		//Creates an indeterminate do-while loop to validate user's entry.
		do {
			
			//Outputs instructions passed.
			System.out.print(instructions);
			
			//Checks if the input is an integer.
			if(input.hasNextInt()) {
				//Assigns user's entry to the integer variable 'userInput'.
				userInput = input.nextInt();
				
				//Checks if user's input is either one of the integer parameters passed. 
				if(userInput == num1 || userInput == num2) {
					
					//Checks if user's input is equals to the first integer parameter.
					if(userInput == num1) {
						//Sets the returnValue to num1.
						returnValue = num1;
					}else {
						//Sets the returnValue to num2.
						returnValue = num2;
					}//End of inner trailing-else.
					
					//Assigns the boolean variable used in the LCC to true.
					num1OrNum2 = true;
					
				}else {
					//Explains the issue.
					System.out.println("Invalid entry, please try again by entering one of the two options.");
					//Assigns the boolean variable used in the LCC to false to stay in the loop.
					num1OrNum2 = false;
				}//End of middle trailing-else.
			}else {
				//Explains the issue.
				System.out.println("Invalid entry, please try again by entering a number.");
				//Assigns the boolean variable used in the LCC to false to stay in the loop.
				num1OrNum2 = false;
			}//End of outer trailing-else.
			
			//Flushes the keyboard buffer to avoid an infinite loop.
			input.nextLine();
			
		}while(!num1OrNum2);//End do-while loop.
		
		//Returns num1 or num2.
		return returnValue;
		
	}//End validateData() method.
	
	
	/**
	 * Method Name: encipherProcess()
	 * Purpose: A public class method that walks through the whole enciphering process.
	 * Accepts: A boolean type variable that will be used to determine if the program is seeking the Regular or Super Caesar Shift.
	 * Returns: Nothing.
	 * Date: March 28, 2020
	 */
	
	public static void encipherProcess(boolean IS_SUPER_CAESAR) {
		
		//Prompts user to enter the desired message that seeks encoding.
		System.out.print("\nEnter the plaintext message you wish to encipher: ");
		
		//Stores user's input in a String type variable 'plaintext'.
		String plaintext = input.nextLine();
		
		//Checks if message does not exist.
		if(plaintext.isBlank()) {
			//Explains the issue.
			System.out.println("Message is empty, try again by entering a message.");
			//Starts over by invoking the encipherProcess() method which will prompt the user to enter the plaintext message again.
			HA_Project_Methods.encipherProcess(IS_SUPER_CAESAR);
		}else {
		
		//Calls the getText() method that converts text to UPPER CASE CHARACTERS and stores it in a String type variable catcher called 'plaintextUpperCase'.
		String plaintextUpperCase = HA_Project_Methods.getText(plaintext);		
		
		//Calls the keyword() method and then catch the keyword entered by the user.
		String keyword = HA_Project_Methods.keyword();
		
		//Creates a variable of type int called 'shiftValue' and stores the number returned by the getShift() method which passed the keyword String variable.
		int shiftValue = HA_Project_Methods.getShift(keyword, IS_SUPER_CAESAR);
		//Passes the plaintextUpperCase variable and the shiftValue variable to the encipher() method that returns a String.
		String encipheredMsg = HA_Project_Methods.encipher(plaintextUpperCase, shiftValue);
		
		//Outputs the plaintext and the enciphered message.
		System.out.println("\nThe plaintext and enciphered text are as follows:");
		System.out.println(plaintextUpperCase);
		System.out.println(encipheredMsg);
		
		//Informs the user that the enciphered message will be written to a text file.
		System.out.println("\nEnciphered text will now be written to a text file.");
		
		//Creates a String variable to hold user's entry.
		String fileName = null;
		//Creates a boolean type variable to be used in the LCC.
		boolean isBlank = true;
		
		//Creates an indeterminate do-while loop to make sure a message is passed.
		do {
		//Prompts user to enter a file name.
		System.out.print("Enter a file name followed by the extension '.txt' (example: secret.txt): ");
		
		//Holds user's entry.
		fileName = input.nextLine();
		
		//Checks if the file name is blank.
		if(fileName.isBlank()) {
			//Explains to user the issue.
			System.out.println("File name is empty, try again by entering a valid file name.");
			//Assigns the boolean variable 'isBlank' to true to stay in the loop and re-ask the user to enter a valid file name.
			isBlank = true;
		}else {
			//Assigns the boolean variable 'isBlank' to false to break out of the loop and proceed.
			isBlank = false;
		}//End of trailing-else.
		
		}while(isBlank);//End do-while loop.
		
		//Invokes a method called writeFile() which accepts two parameters as Strings.
		HA_Project_Methods.writeFile(fileName, encipheredMsg);
		
		//Informs user that the enciphered message has been written to a file.
		System.out.println("Ciphertext has been written to local directory as " + fileName);
		
		}//End of trailing-else.
		
	}//End of encipher() method.
	
	/**
	 * Method Name: decipherKeyboardOrFile()
	 * Purpose: A public class method that walks through the whole deciphering process.
	 * Accepts: A boolean type variable that will be used to determine if the program is seeking the Regular or Super Caesar Shift.
	 * Returns: Nothing.
	 * Date: March 28, 2020
	 */
	
	public static void decipherKeyboardOrFile(boolean IS_SUPER_CAESAR) {
		
		//Informs user that a message will be decoded.
		System.out.println("We will be deciphering a message.");
		
		//Creates a String type variable that will hold the instructions passed to the validateData() method.
		String instructions ="\nIf ciphertext will be entered via keyboard, enter 1. "
												+ "\nIf ciphertext will be entered from a file, enter 2. "
												+ "\nEnter 1 or 2: ";
		
		//Creates a CONSTANT for keyboard selection value and file selection value.
		final int KEYBOARD = 1;
		final int FILE = 2;
		
		//Creates an integer type variable that will hold the returned value of the validateData() method that received the passed variables.
		int userInput = HA_Project_Methods.validateData(KEYBOARD, FILE, instructions);
		
				//Checks if the value entered is either 1 or 2.
				if(userInput == KEYBOARD || userInput == FILE) {
					//Checks if entry is seeking keyboard entry or file entry.
					if(userInput == KEYBOARD) {
						//Calls decipherProcess() method to run.
						HA_Project_Methods.decipherProcess(IS_SUPER_CAESAR);
						}else {
							//Calls the readFile() method to run and assigns it to the String type variable 'fileContents'.
							String fileContents = HA_Project_Methods.readFile();
							
							//Checks if fileContents is empty maybe because an FileNotFoundException was thrown or because the file had no contents.
							if(fileContents.isBlank()) {
								System.out.println("\nFile is empty.\n");
								
								//Goes back to the method to make sure user wanted to enter a ciphertext via keyboard or file.
								HA_Project_Methods.decipherKeyboardOrFile(IS_SUPER_CAESAR);
								}else {
							
							//Calls the keyword() method and then catch the keyword entered by the user.
							String keyword = HA_Project_Methods.keyword();
							
							//Creates a variable of type int called 'shiftValue' and stores the number returned by the getShift() method which passed the keyword String variable.
							int shiftValue = HA_Project_Methods.getShift(keyword, IS_SUPER_CAESAR);
							
							//Passes the ciphertextUpperCase variable and the shiftValue variable to the decipher() method that returns a String.
							String decipheredMsg = HA_Project_Methods.decipher(fileContents, shiftValue);
							
							//Outputs the plaintext and the enciphered message.
							System.out.println("\nThe ciphertext and deciphered plaintext are as follows:");
							System.out.println(fileContents);
							System.out.println(decipheredMsg);
							}//End of inner trailing-else.
							
						}//End of middle trailing-else.
					
				}//End of outer if block.
				
	}//End of decipherKeyboardOrFile() method.
	
	
	/**
	 * Method Name: getText()
	 * Purpose: A public class method that converts text to UPPER CASE characters WITHOUT using the toUpperCase() method!
	 * Accepts: A String.
	 * Returns: A UPPER CASE String.
	 * Date: March 28, 2020
	 */
	
	public static String getText(String text) {
		
		//Declares and initializes a CONSTANT variable of type int to hold the difference ASCII value between a lower case character and a UPPER CASE character.
		final int LOWERCASE_UPPERCASE = 32;
		//Declares and initializes an empty String variable that will hold the UPPER CASE text.
		String upperCase = "";
		//Creates a determinate for loop to check every character in the text.
		for(int i = 0; i < text.length(); i++) {
			//Checks if the character of index i is greater than or equals to 'a' that holds an ASCII value of 97 or less than or equals to 'z' that holds an ASCII value
			//of 122.
			if(text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
				//Subtracts the lower case character from the value of 32 to gives us the UPPER CASE character of that lower case character and stores it in the upperCase String variable.
				upperCase += (char)(text.charAt(i) - LOWERCASE_UPPERCASE);
			} else {
				//If the character is already UPPER CASE or is a space or a punctuation mark for an example, it will add it to the upperCase String variable without adjusting that character. 
				upperCase += text.charAt(i);
			}//End of trailing-else.
			
		}//End of for loop.
		
		//Returns the upperCase variable.
		return upperCase;
		
	}//End of getText() method.
	
	
	/**
	 * Method Name: keyword()
	 * Purpose: A public class method that validates user's keyword entry to make sure there is only letters.
	 * Accepts: Nothing.
	 * Returns: A String with only letters, no digits, spaces, or punctuation marks.
	 * Date: March 28, 2020
	 */
	
	public static String keyword() {
		
		//Creates a String variable to store the keyword.
		String keyword;
		//Creates a boolean type variable to be used in the loop.
		boolean valid = true;
		
		//Creates an Indeterminate do-while loop to make sure entry is valid.
		do {
			//Prompts user to enter a keyword of only letters, with no digits, spaces, or punctuation marks.
			System.out.print("Enter a keyword of only letters, with no digits, spaces, or punctuation marks: ");
			//Stores user's keyword input in a variable of type String called 'keyword'.
			keyword = input.nextLine();
			
			//Checks if the keyword is blank.
			if(keyword.isBlank()) {
				//Explains to user the issue.
				System.out.println("Invalid entry, make sure to enter a keyword.");
				//Sets the boolean variable 'valid' to false to stay in the loop and re-ask the user to enter a valid keyword.
				valid = false;
			}else {
				//Converts the keyword to UPPER CASE characters by calling getText() method for simplicity, otherwise, we have to validate ASCII values greater than 'Z' and less than 'a'.
				keyword = HA_Project_Methods.getText(keyword);
				//Is a Determinate for loop to check for digits, spaces, or punctuation marks.
				for(int i = 0; i < keyword.length(); i++) {
					//Checks if characters entered are between the ASCII value greater or equals to 65 'A' or less than or equals to 90 'Z'.
					if(keyword.charAt(i) >= 'A' && keyword.charAt(i) <= 'Z') {
						//Sets the boolean variable 'valid' to true.
						valid = true;
						} else {
							//Sets the boolean variable 'valid' to false.
							valid = false;
							//breaks out of the for loop, because further iterations are not necessary.
							break;
							}//End of inner trailing-else.
					
				}//End for loop.
				
			}//End of outer trailing-else.
			
		}while(!valid);//End of do-while loop.
		
		//Returns the keyword.
		return keyword;
		
	}//End of keyword() method. 
	
	
	/**
	 * Method Name: getShift()
	 * Purpose: A public class method that calculates the shift value using the keyword.
	 * Accepts: Two parameters, a String and a boolean type variable that will be used to determine if the program is seeking the Regular or Super Caesar Shift.
	 * Returns: An integer between 1 and 25.
	 * Date: March 28, 2020
	 */
	
	public static int getShift(String text, boolean IS_SUPER_CAESAR) {
		
		//Creates a variable of type String to hold the passed text that got converted to UPPER CASE characters using the getText() Method.
		String textUpperCase = HA_Project_Methods.getText(text);
		
		//Creates a CONSTANT for the alphabets length.
		final int ALPHABET_LENGTH = 26;
		//Creates a variable of type int that will hold the shift value.
		int shiftValue = 0;
		//Creates an accumulator variable of type int that will hold the sum of all the ASCII values in the keyword.
		int sumOfAscii = 0;
		
		//Checks if the program is the Super Caesar or the Regular Caesar.
		if(IS_SUPER_CAESAR == true) {
			int[] superShiftValue = new int[textUpperCase.length()];
			
			for(int i = 0; i < superShiftValue.length; i++) {
				superShiftValue[i] = (textUpperCase.charAt(i) - 65);
			System.out.println("Value " + (int)textUpperCase.charAt(i) + " is NOW " + superShiftValue[i]);
			}//End for loop.
			
		}else {
		//Creates a for loop to check every ASCII value and add it to the running total 'sumOfAscii'.
		for(int i = 0; i < textUpperCase.length(); i++) {
			//Adds the ASCII code of each character in the passed text and stores it in the accumulator.
			sumOfAscii += textUpperCase.charAt(i);
		}//End for loop.
		
		//Assigns the sumOfAscii generated % 26 to the shiftValue.
		shiftValue = sumOfAscii % ALPHABET_LENGTH;
		//Checks if the sumOfAScii is a multiple of 26 and if so, adds 1 to the shift value to avoid a shift value of 0.
		if(shiftValue == 0) {
			shiftValue += 1;
		}//End of single-selection if block.
		}
		return shiftValue;
		
	}//End of getShift() method.
	
	
	/**
	 * Method Name: encipher()
	 * Purpose: A public class method that shifts the plaintext characters number of times to the right depending on the shift value generated by the getShift() method.
	 * Accepts: Two parameters, a String, and an integer.
	 * Returns: A String that got its characters shifted depending on the value passed.
	 * Date: March 28, 2020
	 */
	
	public static String encipher(String text, int value) {
		
		//Creates a String type variable to hold the ciphertext.
		String ciphertext = "";
		//Creates a MAX VALUE CONSTANT for the maximum number of English alphabets.
		final int MAX_VALUE = 26;
		
		//Creates a for loop that reads every character in the text.
		for(int i = 0; i < text.length(); i++) {
			//Creates a variable of type char to hold the text's index character and store it in the variable.
			char textCharacter = text.charAt(i);
			//Checks if the character stored in textCharacter is a letter.
			if(textCharacter >= 'A' && textCharacter <= 'Z') {
				
				//Creates a temporary variable of type char to hold the character after the shift, 
				//to see if the shift resulted in a character outside of the alphabets scope, we can use that in the next step in the if statement.
				char temp = (char)(textCharacter + value);
				
				//Checks if the textCharacter's value is greater to 'Z' which is the ASCII code 90.
				if(temp > 'Z') {
					//Creates a formula to wrap back to the beginning. For example: if the textCharacter is 'Z' (90) and the value is 1, the temp will hold a value of 91,
					//which is greater than 'Z' so the underneath formula will result in 'A' since (char)(90 - (26 - 1)) = 65 which is the ASCII value for 'A'.
					ciphertext += (char)(textCharacter - (MAX_VALUE - value));
				}else {
					//If the temp's value is not greater than 90, the temp's value will be added to the ciphertext String.
					ciphertext += temp;
				}//End of inner trailing- else.
			}else {
				//If it is not a letter, a space for example, it will just store it in ciphertext without adding a value.
				ciphertext += textCharacter;
			}//End of outer-trailing else.
			
		}//End for loop.
		
		//Returns the ciphertext.
		return ciphertext;
		
	}//End of encipher() method.
	
	
	/**
	 * Method Name: writeFile()
	 * Purpose: A public class method that creates a file in the project folder then writes text to that file created in the project folder.
	 * Accepts: Two String parameters.
	 * Returns: Nothing
	 * Date: March 28, 2020
	 */
	
	public static void writeFile(String name, String text){
		
		//Creates a File object.
		File fileName = new File(name);
		//Declares a FileWrite class and initialize it to null to always provide something for the PrintWriter object.
		FileWriter fileWriter = null;
		//Creates a try-catch block to catch the exception if thrown, which will not crash the program.
		try
		{
			//Creates a FileWrite object that creates a file depending on the fileName.
			fileWriter = new FileWriter(fileName);
		}/*End try block*/catch (IOException ex) //Catches the exception and assign it to ex.
		{
			//Displays the IOException message block.
			ex.printStackTrace();
		}//End catch block.
		//Creates a PrintWriter object that will be used to write in the file created.
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		//Writes the text passed to this method into the file created.
		printWriter.write(text);
		
		//Closes printWriter.
		printWriter.close();
		
	}//End of writeFile() method.
	
	
	/**
	 * Method Name: decipherProcess()
	 * Purpose: A public class method that walks you through the whole deciphering process.
	 * Accepts: A boolean type variable that will be used to determine if the program is seeking the Regular or Super Caesar Shift.
	 * Returns: Nothing.
	 * Date: March 28, 2020
	 */
	
	public static void decipherProcess(boolean IS_SUPER_CAESAR) {
		
			//Prompts user to enter the desired message that seeks decoding.
			System.out.print("\nEnter the ciphertext message you wish to decipher: ");
			
			//Stores user's ciphertext message.
			String ciphertext = input.nextLine();
			
			//Checks if there is no message.
			if(ciphertext.isBlank()) {
				//Explains the issue.
				System.out.println("Message is empty, try again by entering a message.");
				//Starts over by invoking the decipherProcess() method which will prompt the user to enter the ciphertext message again.
				HA_Project_Methods.decipherProcess(IS_SUPER_CAESAR);
				}else {
		
		//Calls the getText() method that converts text to UPPER CASE CHARACTERS and stores it in a String type variable catcher called 'ciphertextUpperCase'.
		String ciphertextUpperCase = HA_Project_Methods.getText(ciphertext);
		
		//Calls the keyword() method and then catch the keyword entered by the user.
		String keyword = HA_Project_Methods.keyword();
		
		//Creates a variable of type int called 'shiftValue' and stores the number returned by the getShift() method which passed the keyword String variable.
		int shiftValue = HA_Project_Methods.getShift(keyword, IS_SUPER_CAESAR);
		
		//Passes the ciphertextUpperCase variable and the shiftValue variable to the decipher() method that returns a String.
		String decipheredMsg = HA_Project_Methods.decipher(ciphertextUpperCase, shiftValue);
		
		//Outputs the ciphertext and the deciphered message.
		System.out.println("\nThe ciphertext and deciphered plaintext are as follows:");
		System.out.println(ciphertextUpperCase);
		System.out.println(decipheredMsg);
		
				}//End trailing-else.
		
	}//End of decipherProcess() method.
	
	
	/**
	 * Method Name: decipher()
	 * Purpose: A public class method that shifts the ciphertext characters number of times to the left depending on the shift value generated by the getShift() method.
	 * Accepts: Two parameters, a String, and an integer.
	 * Returns: A String that got its characters shifted depending on the value.
	 * Date: March 28, 2020
	 */
	
	public static String decipher(String text, int value) {
		
		//Creates a String type variable to hold the ciphertext.
		String plaintext = "";
		//Creates a MAX VALUE CONSTANT for the maximum number of English alphabets.
		final int MAX_VALUE = 26;
		
		//Creates a for loop that reads every character in the text.
		for(int i = 0; i < text.length(); i++) {
			//Creates a variable of type char to hold the text's index character and store it in the variable.
			char textCharacter = text.charAt(i);
			//Checks if the character stored in textCharacter is a letter.
			if(textCharacter >= 'A' && textCharacter <= 'Z') {
				//Creates a temporary variable of type char to hold the character after the shift, 
				//to see if the shift resulted in a character outside of the alphabets scope, we can use that in the next step in the if statement.
				char temp = (char)(textCharacter - value);
				
				//Checks if the temp's value is less than 'A' which is the ASCII code 65.
				if(temp < 'A') {
					//Creates a formula to wrap back to the ending. For example: if the temp character is 'A' (65) and the value is 1, the temp will hold a value of 64,
					//which is less than 'A' so the underneath formula will result in 'Z' since (char)(65 + (26 - 1)) = 90 which is the ASCII value for 'Z'.
					plaintext += (char)(textCharacter + (MAX_VALUE - value));
				}else {
					//If the textCharacter's value is not less than 65, the textCharacter's value will be added with the value and then explicitly casted to a char.
					//Then it will be added to the String variable plaintext.
					plaintext += temp;
				}//End of inner trailing- else.
			}else {
				//If it is not a letter, a space for example, it will just store it in plaintext without adding a value.
				plaintext += textCharacter;
			}//End of outer-trailing else.
			
		}//End for loop.
		
		//Returns the plaintext.
		return plaintext;
		
	}//End of decipher() method.
	
	
	/**
	 * Method Name: readFile()
	 * Purpose: A public class method that deciphers a message from a file.
	 * Accepts: Nothing.
	 * Returns: A String.
	 * Date: March 28, 2020
	 */
	
	public static String readFile() {
		
		//Prompts user to enter the name of the file containing the encoded message.
		System.out.print("Enter the name of the file containing the ciphertext: ");
		
		//Stores user's entry in a String type variable called 'fileName'.
		String fileName = input.nextLine();
		
		//Declares a String type variable to hold the text.
		String ciphertext = "";
		
		//Creates a File object that will pass to the Scanner.
		File file = new File(fileName);
		
		//Declares a Scanner variable that will hold the File object reference.
		Scanner fileReader;
		
		try {
			//Creates a Scanner object that holds the File object reference.
			fileReader = new Scanner(file);
			
			//Sets up a loop to read in the contents in the file.
			while(fileReader.hasNextLine()) {
				ciphertext += fileReader.nextLine();
			}//End of while loop.

			//Closes the fileReader Scanner.
			fileReader.close();
			
		}/*End try block*/catch(FileNotFoundException ex) { //Catches the exception and assign it to ex.
			
			//Outputs explanation of why an exception was thrown.
			System.out.println("\nException thrown, file not found on disk.");
			//Outputs the message thrown by the exception which is held by ex.
			System.out.println("\nMessage in exception object is: " + ex.getMessage() + ".");
			//Explains to user the issue.
			System.out.println("File name is not found, please try again and enter a valid file name.");
			
			//Displays the FileNotFoundException message block.
			//ex.printStackTrace();
		
		}//End catch block.
		
		//Declares a type String variable and holds the conversion of the text in ciphertext to UPPER CASE characters.
		String ciphertextUpperCase = HA_Project_Methods.getText(ciphertext);
		
		//Returns ciphertextUpperCase.
		return ciphertextUpperCase;
		
	}//End of readFile() method.
	
}//End class