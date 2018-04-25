package wumpus.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Dungeon {
	private static List<List<Tile>> dungeon;
	private static long size;

	public static List<List<Tile>> getDungeon() {
		return dungeon;
	}

	public static void setDungeon(List<List<Tile>> dungeon) {
		Dungeon.dungeon = dungeon;
	}

	public static long getSize() {
		return size;
	}

	public static void setSize(long size) {
		Dungeon.size = size;
	}

	public Dungeon() {

	}

	public Dungeon(String mapName) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		List<Tile> rows = new ArrayList<>();
		List<List<Tile>> table = new ArrayList<List<Tile>>();
		long rowNumber = 0;
		long colNumber = 0;
		try {
			Object object = jsonParser.parse(
					new FileReader("C:\\Users\\James\\eclipse-workspace\\wumpus\\src\\main\\resources\\" + mapName));
			JSONObject jsonObject = (JSONObject) object;
			Dungeon.size = (long) jsonObject.get("size");
			JSONArray array = (JSONArray) jsonObject.get("dungeon");
			for (Object object2 : array) {
				if (rowNumber == size - 1) {

					rows.add(new Tile(rowNumber, colNumber, (long) object2));
					table.add(rows);
					rows = new ArrayList<>();
					rowNumber = 0;
					colNumber = 0;
				} else {
					rows.add(new Tile(rowNumber, colNumber, (long) object2));
					rowNumber += 1;
					colNumber += 1;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dungeon.dungeon = table;
	}

	public void drawDungeon() {
		for (List<Tile> list : dungeon) {
			for (Tile tile : list) {
				System.out.print(tile.getValue() + "  ");
			}
			System.out.println("");
		}
	}

}
