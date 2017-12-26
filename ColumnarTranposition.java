/**
 * Encrypt and Decrypt a message 
 * takes the message from an input.txt file and reads it to an output.txt file. 
 * @Eli Kleinworm
 * CS 313
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

public class ColumnarTranposition {
    public static char[][] storeMessage;
    public static int holderIndex; 
    public static int NumValue; 
    public static BufferedWriter Bw; 

    public static void main(String[] args) throws IOException{
	/*
	 * This method uses file I/O and can throw this exception.
	 */
	try{
	    Bw = new BufferedWriter(new FileWriter ("output.txt", true));
	    Scanner in = new Scanner(new FileReader("input.txt"));
	    while (in.hasNextLine()){
	    	String message = in.nextLine();
	    	message = message.replace(" ", " ");
	    	String[] SendMessage;
		
	    	while("".equals(message)){
	    		message = in.nextLine();
	    	}
	    	SendMessage = message.split("\\*\\.");
	    	//Split loop
	    	for(int i=0; i<SendMessage.length; i++){
	    		if(SendMessage[i].charAt(0) == 'E'){
	    			Encrypt(SendMessage[i], Bw);
		    } 
		    else if(SendMessage[i].charAt(0) == 'D'){
		    	Decrypt(SendMessage[i], Bw);
		    }
	      }
	    }
	    in.close();
	    Bw.close();
	} 
		catch (Exception p){
			p.printStackTrace();
		}
    }

    private static void Encrypt(String message, BufferedWriter Bw) throws IOException{
	// TODO Auto-generated method stub
	holderIndex = 2;
	NumValue = Character.getNumericValue(message.charAt(1)); 
	message = message.replace(" ", " ");
	storeMessage = new char[((message.length()-2)/NumValue) + 1][NumValue];
	
	for(int j = 0; j<((message.length() - 2)/ NumValue) + 1; j++){
	    for(int m = 0; m<NumValue; m++){
		if(holderIndex != message.length()){
		    storeMessage[j][m] = message.charAt(holderIndex++);
		}
	    }
	}
	
	// r=row,  c=column
	for(int c = 0; c<storeMessage[0].length; c++){
	    for (int r = 0; r<storeMessage.length; r++){
		
		if (storeMessage[r][c] == 0) {
		    Bw.append('Q');
		} else {
		    /*
		     * this will append the message to the new file rather then overwrite everything 
		     */
		    Bw.append(storeMessage[r][c]);
		}
	    }
	}
	
	storeMessage = null;
	Bw.newLine();
    }
    
    private static void Decrypt(String message, BufferedWriter Bw) throws IOException{
	// TODO Auto-generated method stub
	holderIndex = 2;
	NumValue = Character.getNumericValue(message.charAt(1)); 
	message = message.replace(" ", "");
	storeMessage = new char[((message.length()-2)/NumValue) + 1][NumValue];
	
	for(int m = 0; m<NumValue; m++){
	    for(int j = 0; j<((message.length() - 2)/ NumValue); j++){
		if(holderIndex < message.length()){
		    storeMessage[j][m] = message.charAt(holderIndex++);
		}
	    }
	}
	
	
	// r=row, c=column
	for (int r = 0; r<storeMessage.length; r++){
	    for(int c = 0; c<storeMessage[0].length; c++){
		Bw.append(storeMessage[r][c]);
	    }
	}
	
	storeMessage = null;
	Bw.newLine();
    }

}