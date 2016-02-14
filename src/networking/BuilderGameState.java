package networking;

import java.io.Serializable;

public class BuilderGameState implements Serializable {
	
	public BuilderGameState(String button2, int x2, int y2) {
		// TODO Auto-generated constructor stub
		this.button = button2;
		this.x = x2;
		this.y = y2;
	}
	
	String button;
	int x;
	int y;
	
}
