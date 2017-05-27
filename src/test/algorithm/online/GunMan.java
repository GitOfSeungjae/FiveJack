package test.algorithm.online;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GunMan {

	static int MAXGUNMAN = 0;
	static int MAXPLACEWAY = 0;
	
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new FileInputStream("/home/leeseungjae/workspace/FiveJack/src/gunmanInput.txt"));
		
		/*
		 * read Inputs
			8 8
			□■□■□■□■
			□□□□□■□□
			■□■□□■□■
			□□□□□□□□
			□□□■□□□□
			□□□□□■□■
			□■□□□□□□
			□□□□■□■□
		 */
		int rownum = sc.nextInt();
		int colnum = sc.nextInt();
		char[][] inputs = new char[rownum][colnum];
		
		for (int i = 0; i < rownum; i++) {
			String inputLine = sc.next();
			for (int j = 0; j < colnum; j++) {
				char input = inputLine.charAt(j);
				inputs[i][j] = input;
			}
		}
		
		int[] nextpoint = getNextPoint(inputs, 0, 0);
		placeGunMan(inputs, nextpoint[0], nextpoint[1], 0, true);
		placeGunMan(inputs, nextpoint[0], nextpoint[1], 0, false);
		
		System.out.println("Max Number of Placed Gunmans : " + MAXGUNMAN);
		System.out.println("Max Number of Place Gunman Ways : " + MAXPLACEWAY);
	}
	
	
	/*
	 * Method : placeGunMan
	 * Function Description : calculate maximum number of possible ways to place gunmans
	 * Parameters : inputs    - inputted map + placed gunman
	 *              placedNum - number of placed gunmans
	 *              place     - flag to place gunman to (row, col)           
	 * return : void
	 */
	static void placeGunMan(char[][] inputs, int row, int col, int placedNum, boolean place){
	
		// place gunman
		if(place){
			inputs[row][col] = '♂';
			placedNum++;
		} 
		
		int[] nextpoint = getNextPoint(inputs, row, col+1);
		
		int tempMax = 0;
		
		if(nextpoint[0] < inputs.length) {
			placeGunMan(inputs, nextpoint[0], nextpoint[1], placedNum, true);
			placeGunMan(inputs, nextpoint[0], nextpoint[1], placedNum, false);
		}
		else {
			if(placedNum > MAXGUNMAN){
				MAXGUNMAN = placedNum;
				MAXPLACEWAY = 1;
			} else if(placedNum == MAXGUNMAN){
				MAXPLACEWAY++;
			}
			tempMax = placedNum;
		}
		
		// recover placed gunman to calculate next step.
		if(place){
			inputs[row][col] = '□';			
		}
	}

	/*
	 * Method : placeable
	 * Function Description : return possibility to place gunman in (row, col)
	 * return : boolean
	 */
	static boolean placeable(char[][] inputs, int row, int col){
		if(inputs[row][col] != '□') return false;
		for (int i = row; i < inputs.length; i++) {
			if(inputs[i][col] == '♂') return false;
			if(inputs[i][col] == '■') break;
		}
		for (int i = row; i >= 0; i--) {
			if(inputs[i][col] == '♂') return false;
			if(inputs[i][col] == '■') break;
		}
		for (int j = col; j < inputs[0].length; j++) {
			if(inputs[row][j] == '♂') return false;
			if(inputs[row][j] == '■') break;
		}
		for (int j = col; j >= 0; j--) {
			if(inputs[row][j] == '♂') return false;
			if(inputs[row][j] == '■') break;
		}
		return true;
	}
	
	/*
	 * Method : getNextPoint
	 * Function Description : find next available point to place gunman 
	 * return : int[2]
	 *          int[0] - next available point row number
	 *          int[1] - next available point column number
	 */
	static int[] getNextPoint(char[][] inputs, int row, int col){
		int[] rtn = new int[2];
		if(col == inputs[0].length) {
			row++;
			col = 0;
		}
		while(true){
			if(row == inputs.length) break;
			
			if(placeable(inputs, row, col)){
				break;
			}else {
				col++;
				if(col == inputs[0].length){
					row++;
					col = 0;
				}
			}
		}
		rtn[0] = row; rtn[1] = col;
		return rtn;
	}
	
	/*
	 * Method : printGunMan
	 * Function Description : show gunmans position for debugging
	 */
	static void printGunMan(char[][] inputs){
		System.out.println();
		for (int i = 0; i < inputs.length; i++) {
			for (int j = 0; j < inputs[0].length; j++) {
				 System.out.print(inputs[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
