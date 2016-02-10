package application;

import model.Position;
import model.State;

public class MainView {
	
	private int size;
	
	public MainView(int size) {
		this.size = size;
	}
	
	public void draw(State state) {
		
		String[][] stateDisplay = new String[size][size];
		
		state.getPawns().stream().forEach(e -> {
			int x = e.getPosition().getX();
			int y = e.getPosition().getY();
			stateDisplay[x][y] = e.getIcon();
		});
		
		Position kPos = state.getKnight().getPosition();
		stateDisplay[kPos.getX()][kPos.getY()] = state.getKnight().getIcon();
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				
				String s = stateDisplay[i][j] == null ? "_" : stateDisplay[i][j];
				if (i == 0 || i == size - 1 || j == 0 || j == size - 1) s = "X";
				System.out.print(s + " ");
				
			}
			
			System.out.println();
			
		}
	
	}

}
