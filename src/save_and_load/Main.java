package save_and_load;
import java.util.Scanner;
public class Main {
	public static int choice;
	public static void main(String args[]) {
				
		Scanner scanner = new Scanner(System.in);
		System.out.println("1) start a new game\n" + "2) resume game");
		choice = scanner.nextInt();
		
		Game play = new Game();
		if(choice == 1) { 
			play.intro();
		} else {
			// read data 
			Read_file readObject = new Read_file();
			readObject.openFile();
			readObject.readFile();
			readObject.closeFile();
		}
		play.playGame();
		scanner.close();
		
	}
} 
