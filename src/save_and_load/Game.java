package save_and_load;

import java.util.Scanner;
import java.util.Random;

public class Game {
	private static Board boardObject = new Board();
	private static int player = 1;
	private static int mode;
	private static int r = 0, c;
	public static int maxRow, maxCol, maxPoints;
	public static char arr[][];
	private static Scanner scanner = new Scanner(System.in);

	// game intro
	public void intro() {
		System.out.println("Enter row dimension");
		maxRow = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter col dimension");
		maxCol = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter # of points to connect");
		maxPoints = scanner.nextInt();
		scanner.nextLine();
		arr = new char[maxRow][maxCol];
		boardObject.printNewArray(arr, maxRow, maxCol);
		// select a mode play
		System.out.println("Select a play mode\n" + "mode 1 : player VS player\n" + "mode 2 : player VS computer\n");
		mode = scanner.nextInt();
	}

	public void setChar(int _row, int _col, int _mode, int _player, int _maxRow, int _maxCol, int _maxPoints) {
		boolean isOccupied = false;
		Random randomObject = new Random();
		if(Main.choice == 2) {
			r = _row;
			c = _col; 
			player = _player;
			mode = _mode;
			maxPoints = _maxPoints;
			arr = new char [_maxRow][_maxCol];
		}
		if (Main.choice == 1) {
			while (true) {
				System.out.println("\nplayer " + player + " , Enter a column: ");
				// player VS player
				if (mode == 1) {
					c = scanner.nextInt() - 1;
					scanner.nextLine();
				} else {
					if (player == 1) {
						c = scanner.nextInt() - 1;
						scanner.nextLine();
					} else if (player == 2) {
						// from 0 up to maxCol, and maxCol is not included
						c = randomObject.nextInt(maxCol);
					}
				}
				// columns is out of border
				if (c >= maxCol) {
					System.out.println("Enter a valid place, try again");
					continue;
				} else { // location is occupied
					for (int i = maxRow - 1; i > -1; i--) {
						if (arr[i][c] == ' ') {
							isOccupied = false;
							r = i;
							break;
						} else {
							isOccupied = true;
						}
					}
					if (isOccupied) {
						System.out.println("location is occupied, try again!");
						continue;
					}
				}
				break;
			}
		}
		
		// empty and correct location
		if (player == 1) {
			arr[r][c] = 'x';
		} else {
			arr[r][c] = 'o';
		}
	}

	public static void changeTurns() {
		if (player == 1)
			player += 1;
		else {
			player -= 1;
		}
	}

	public static boolean isWinner(int R, int C) {

		Win winObject = new Win();
		boolean win_by_row = winObject.check_curr_row(arr, maxCol, maxPoints, R);
		boolean win_by_col = winObject.check_curr_col(arr, maxRow, maxPoints, C);
		boolean win_by_left_diagonal = winObject.check_curr_left_diagonal(arr, maxRow, maxCol, maxPoints, R, C);
		boolean win_by_right_diagonal = winObject.check_curr_right_diagonal(arr, maxRow, maxCol, maxPoints, R, C);

		if (win_by_row)
			return true;
		else if (win_by_col) {
			return true;
		} else if (win_by_left_diagonal) {
			return true;
		} else if (win_by_right_diagonal) {
			return true;
		}
		return false;
	}

	public void playGame() {
		Write_file writeObject = new Write_file();
		if(Main.choice == 1)
			writeObject.create_a_file();
		
		boardObject.loadMyArrayWithSapces(arr, maxRow, maxCol);

		while (true) {
			if (isWinner(r, c)) {
				if (player == 1) {
					System.out.println("player " + (player + 1) + " won the game :)");
				
				} else {
					System.out.println("player " + (player - 1) + " won the game :)");
				}
				// close data saving stream
				writeObject.close_a_file();
				break;
			} else {
				if (boardObject.isFilled(arr, maxCol)) {
					System.out.println("Oh, man! it's a tie situation!!");
					break;
				} else {
					// keep playing
					setChar(r,c,mode, player,maxRow,maxCol,maxPoints);
					boardObject.printNewArray(arr, maxRow, maxCol);
					changeTurns();
					// store data
					writeObject.write_in(maxRow, maxCol, maxPoints, mode, r, c, player);
				}
			}
		}
	}
}
