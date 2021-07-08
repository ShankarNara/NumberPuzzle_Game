import java.awt.*;

class CustomNumberPuzzleControl extends NumberPuzzleControl {
	public int getWidth() {
		return 200;
	}
	public int getHeight() {
		return 250;
	}
	public int getXPosition() {
		return 200;
	}
	public int getYPosition() {
		return 200;
	}
	public String getTitle(){
		return "Number Puzzle";
	}
	public int getShuffleButtonFontSize() {
		return 12;
	}
	public int getNumbersFontSize() {
		return 12;
	}
	public Color getEmptyButtonColor() {
		return Color.WHITE;
	}
	public String getWinnerMessage() {
		return "Congrats, you have won!";
	}

	// The following three methods have to be written by the participants...

	public int handleButtonClicked(NumberPuzzleGame game){
		int emptyCellId = game.getEmptyCellId();
		Button buttonClicked = game.getButtonClicked();
		Button[] buttons = game.getButtons();
		
		String button_clicked = buttonClicked.getLabel();
		int button_no_clicked = Integer.parseInt(button_clicked);
		
		//Your logic here	
//		System.out.println(buttonClicked.getLabel());
		//Find location of new empty cell
		int ecd=0;
		for (int i=0; i<16;i++) {
			if(buttons[i] == buttonClicked) {
				ecd = i;
				break;
			}
		}
		
		int empty_ind=0;
		for (int i=0;i<16;i++) {
//			System.out.println(buttons[i]);
			if (buttons[i].getLabel().equals("  ")) {
				empty_ind=i;
				break;
			}	
		}
		
		boolean valid_bool = false;
		if(ecd%4 != 0 && ecd-1==empty_ind) {
			valid_bool = true;
		} else if(ecd%4 != 3 && ecd+1==empty_ind) {
			valid_bool=true;
		} else if(!(ecd>=0 && ecd<=3) && ecd-4==empty_ind) {
			valid_bool=true;
		} else if(!(ecd>=12 && ecd<=15) && ecd+4==empty_ind) {
			valid_bool=true;
		}
		
		if(valid_bool) {
			swapButton(buttons[empty_ind], buttonClicked);
			emptyCellId = ecd;
		}
		
		
		return emptyCellId;

	}
	
	public int[] getRandomNumbersForGrid() {
		int arr[] = new int[15];
		boolean check[] = new boolean[15];
		int k=0;
		//Your logic here
		while(k<15) {
			int a = getRandomNumber();
			int rval = a%16;
		//	System.out.println("Random number : "+rval);
			if(rval>=1 && rval<16 && !check[rval-1]) {
				check[rval-1]=true;
				arr[k] = rval;
				k+=1;
			}
			
		}
		
		return arr;
	}
	public boolean checkForWinner(Button[] buttons)
	{
		boolean winner = true;
		
		// Your Logic here
		int[] buttonIds = getIntegerArrayOfButtonIds(buttons);
		
		int order=1;
		for(int i=0;i<15;i++) {
//			System.out.print(buttonIds[i]+" ");
			if(order != buttonIds[i]) {
				winner=false;
				break;
			}
			order+=1;
		}
		return winner;
	}
}