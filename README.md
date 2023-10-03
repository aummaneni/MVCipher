# MVCipher
This is the readme for the MVCipher program, which is a Java application that can encrypt or decrypt a file based on a keyword given by the user.

* The program uses the MV Cipher algorithm, which is a variation of the Vigenère cipher that shifts each letter in the plaintext by a number of positions corresponding to the letter in the keyword.

* The program can encrypt or decrypt any text file that contains only letters and spaces. The program preserves the case and punctuation of the original file.

* The program prompts the user for a keyword, which must consist of only letters. The program converts the keyword to uppercase before applying the algorithm.

* The program prompts the user for an input file name and an output file name. The program reads the input file, encrypts or decrypts it, and writes the result to the output file.

* The program displays a message indicating the completion of the encryption or decryption process and the keyword used.

  
**Usage**

* To run the program, you need to have Java installed on your system. You also need to have the Prompt and FileUtils classes in your classpath.

* To compile the program, use the command javac MVCipher.java.

* To run the program, use the command java MVCipher.

* Follow the instructions on the screen to enter a keyword, choose between encryption or decryption, and provide the input and output file names.

* Enjoy your encrypted or decrypted file!
