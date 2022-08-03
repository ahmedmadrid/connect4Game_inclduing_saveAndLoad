package save_and_load;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Write_file {
	// create a file
	private Formatter my_file;
	public void create_a_file() {
		try {
			my_file = new Formatter("my_file.txt");
		} catch(Exception e) {
			System.out.println("File is not created");
		}
	}
	
	// write in a file
	public void write_in(int maxRow, int maxCol, int maxPoints, int mode, int r, int c, int player) {
		my_file.format("%d %d %d %d %d %d %d\n", maxRow, maxCol, maxPoints, mode, r, c, player);
	}
	
	// close my file
	public void close_a_file() {
		my_file.close();
	}
}
