package wumpus.logic;

import org.json.simple.parser.ParseException;

public class Game {
	
//	private String player;

	public static void main(String[] args) throws ParseException {
		Dungeon dungeon = new Dungeon("FirstMap.json");
		dungeon.drawDungeon();
		

	}

}
