/**
 *	MVCipher - A program that encrypts or decrypts a file based on a specific code 
 *  given by the user
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Atul Ummaneni
 *	@since	September 19, 2021
 */
import java.util.Scanner;
import java.io.PrintWriter;

public class MVCipher {
	
	private FileUtils FileUtils; // FileUtils object
	private Prompt Prompt; // Prompt object
		
	/** Constructor */
	public MVCipher()
	 {
		FileUtils = new FileUtils();
		Prompt = new Prompt();
	 }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.runner();
	}
	
	/**
	 *	Method that asks the user for the key word, the files to read/write, and whether to encrypt/decrypt.
	 *  Whichever is chosen, the program encrypts/decrypts a file and writes it to another. 
	 *	
	 *  @param null No parameters
	 *	@return     No returns
	 */
	public void runner() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		String keyWord = Prompt.getString("Please input a word to use as key (letters only)");
		boolean isAlpha = false; // boolean for checking if the key word is made of alpha only
		int counter = 0; // to help check if there are alpha chars only
		while(isAlpha == false)
		{
			for(int i = 0; i<keyWord.length(); i++)
			{
				if((keyWord.charAt(i)<65)||(keyWord.charAt(i)>90 && keyWord.charAt(i)<97)||(keyWord.charAt(i)>122))
				{
					keyWord = Prompt.getString("Please input a word to use as a key (letters only)");
				}
				else counter++;
			}
			if(counter == keyWord.length()) isAlpha = true;
			counter = 0;
		}
		  
		/* Prompt for encrypt or decrypt */
		System.out.print("\n");
		int choice = Prompt.getInt("Encrypt or Decrypt? (1-2)");
		while(choice != 1 && choice !=2)
		{
			choice = Prompt.getInt("Encrypt or Decrypt? (1-2)");
		}
			
		/* Prompt for an input and output file name */
		String inputfile = ""; // name of the input file
		String outputfile = ""; //name of output file

		if(choice == 1)
		{
			inputfile = Prompt.getString("Name of file to encrypt");
			outputfile = Prompt.getString("Name of output file");
		}
		else
		{
			inputfile = Prompt.getString("Name of file to decrypt");
			outputfile = Prompt.getString("Name of output file");
		}
		
		/* Read input file, encrypt or decrypt, and print to output file */
		String line = ""; // the inputted file is stored by every line
		Scanner sc = null; // Scanner object for reading files
		int counter2 = 0; // character counter in each line
		char stringChar = ' '; // checks the line char that is being used
		int counter3 = 0; // increments charKey
		char charKey = ' '; // checks if the keyChar is the same
		PrintWriter writer = null; // PrintWriter Object to write to a file
		String upperKey = keyWord.toUpperCase(); //uppercase of keyWord
		if(choice == 1)
		{
			sc = FileUtils.openToRead(inputfile);
			writer = FileUtils.openToWrite(outputfile);
			while(sc.hasNextLine())
			{
				line = sc.nextLine();

				for(int i = 0; i<line.length(); i++)
				{
					if((line.charAt(i)>96 && line.charAt(i)<123) || (line.charAt(i)>64 && line.charAt(i)<91))
					{
						charKey = keyWord.charAt(counter2);
						if(counter2+1 >= keyWord.length()) counter2 = 0;
						else counter2++;

						if(charKey>96 && charKey<123)
						{
							counter3 = charKey-96;
						}
						else 
						{
							counter3 = charKey-64;
						}

						stringChar = line.charAt(i);

						
						if(line.charAt(i)>96 && line.charAt(i)<123)
						{
							while(counter3>0)
							{
								if(stringChar == 122)
								{
									stringChar = (char) 97;
									counter3--;
								}
								else
								{
									stringChar = (char) (stringChar + 1);
									counter3--;
								}
							}
						}

						else if(line.charAt(i)>64 && line.charAt(i)<91)
						{
							while(counter3>0)
							{
								if(stringChar == 90)
								{
									stringChar = (char)(65);
									counter3--;
								}
								else
								{
									stringChar = (char)(stringChar + 1);
									counter3--;
								}
							}
						}
						writer.write(stringChar);
					}
					else 
					{
						writer.write(line.charAt(i));
					}
				}
				writer.write("\n");
			}
			System.out.println("\nThe encrypted file " + outputfile + " has been created using the keyword -> " + upperKey + "\n");
			
		}

		int subtract = 0; // increments charKey
		int counter4 = 0; // counter for charKey

		if(choice == 2)
		{
			sc = FileUtils.openToRead(inputfile);
			writer = FileUtils.openToWrite(outputfile);

			while(sc.hasNextLine())
			{
				line = sc.nextLine();

				for(int i = 0;i<line.length();i++)
				{
					if((line.charAt(i)>96&&line.charAt(i)<123) ||(line.charAt(i)>64 && line.charAt(i)<91))
					{
						charKey = keyWord.charAt(counter4);
						if(counter4+1>=keyWord.length())counter4 = 0;
						else counter4++;

						if(charKey>96&&charKey<123)
						{
							subtract=charKey-96;
						}
						else 
						{
							subtract=charKey-64;
						}

						stringChar=line.charAt(i);

						if(line.charAt(i)>96&&line.charAt(i)<123)
						{
							while(subtract>0)
							{
								if(stringChar==97)
								{
									stringChar =(char)122;
									subtract--;
								}
								else
								{
									stringChar = (char)(stringChar - 1);
									subtract--;
								}
							}
						}

						else if(line.charAt(i)>64&&line.charAt(i)<91)
						{
							while(subtract>0)
							{
								if(stringChar==65)
								{
									stringChar=(char)(90);
									subtract--;
								}
								else
								{
									stringChar=(char)(stringChar - 1);
									subtract--;
								}
							}
						}
						writer.write(stringChar);
					}
					else
					{
						writer.write(line.charAt(i));
					}
				}
				writer.write("\n");
			}
			System.out.println("\nThe decrypted file " + outputfile + " has been created using the keyword -> " + upperKey + "\n");
		}
		
		/* Don't forget to close your output file */
		writer.close();
	}
	
	
}