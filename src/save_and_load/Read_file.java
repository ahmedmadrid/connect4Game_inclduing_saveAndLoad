package save_and_load;
import java.util.*;
import java.io.*;

public class Read_file {
	// access file by scanner
	Scanner scanner;
	public void openFile() {
		try {
			scanner = new Scanner(new File("my_file.txt"));
		} catch(Exception e) {
			System.out.println("something is wrong, can't open your file to read");
		}
	}
	// read what's in there
	public void readFile() {
		Game gameObject = new Game();
		Board boardObject = new Board();
		int maxRow=0, maxCol=0, _maxPoints;
		while(scanner.hasNextInt()) {
			 maxRow = scanner.nextInt();
			 maxCol = scanner.nextInt();
			_maxPoints = scanner.nextInt();
			int mode = scanner.nextInt();
			int r = scanner.nextInt();
			int c = scanner.nextInt();
			int player = scanner.nextInt(); 
			// choose what you wanna do
			gameObject.setChar(r,c,mode,player,maxRow,maxCol,_maxPoints);
			
		}
	 
		boardObject.printNewArray(Game.arr, maxRow, maxCol);
		
	}
	
	// close it
	public void closeFile() {
		scanner.close();
	}
}
