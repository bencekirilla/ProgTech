package hu.wumpus.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Json állományokat kezelő osztály.
 * 
 * @author James
 *
 */
public class DataBaseManager {
	/**
	 * Logolásért felelős adattag.
	 */
	private static Logger logger = LoggerFactory.getLogger(DataBaseManager.class);

	/**
	 * Kiolvassa a paraméterül megadott állományból a kazamata nagyságát.
	 * 
	 * @param fileName
	 *            az olvasandó állomány neve
	 * @return kazamata mérete
	 */
	public int getSizeRowFromJson(String fileName) {

		JSONParser jsonParser = new JSONParser();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			Object object = jsonParser.parse(new FileReader(classLoader.getResource(fileName + ".json").getFile()));

			JSONObject jsonObject = (JSONObject) object;
			String size = (String) jsonObject.get("size");
			return Integer.parseInt(size);

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		return 0;
	}

	/**
	 * Kiolvassa a paraméterül megadott állományból a kazamata elemeit.
	 * 
	 * @param fileName
	 *            az olvasandó állomány neve
	 * @return kazamata mátrix
	 */
	public String[][] getDungeonFromJson(String fileName) {

		JSONParser jsonParser = new JSONParser();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			Object object = jsonParser.parse(new FileReader(classLoader.getResource(fileName + ".json").getFile()));
			JSONObject jsonObject = (JSONObject) object;
			String dungeon = (String) jsonObject.get("dungeon");
			int NumberOfTiles = getSizeRowFromJson(fileName);
			String[][] valueMatrix = new String[NumberOfTiles][NumberOfTiles];
			int j = -1;
			for (int i = 0; i < dungeon.split(",").length; i++) {
				if (i % NumberOfTiles == 0) {
					j += 1;
				}
				valueMatrix[i % NumberOfTiles][j] = dungeon.split(",")[i];
			}
			return valueMatrix;

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Kiolvassa a paraméterül megadott állományból az adott kazamata területhez
	 * tartozó leírást.
	 * 
	 * @param value
	 *            olvasandó terület értéke
	 * @return a leírás
	 */
	public String getTilesLog(int value) {
		JSONParser jsonParser = new JSONParser();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			Object object = jsonParser.parse(new FileReader(classLoader.getResource(value + ".json").getFile()));
			JSONObject jsonObject = (JSONObject) object;
			String[] rawString = ((String) jsonObject.get("text")).split("'");
			StringBuilder rv = new StringBuilder();
			for (String string : rawString) {
				rv.append(string).append('\n').toString();
			}
			return rv.toString();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Kiolvassa a paraméterül megadott állományból az eredménytáblát.
	 * 
	 * @param value
	 *            reprezentálja, hogy melyik értéket akarjuk olvasni
	 * @return a kiolvasott adatok vagy pontok vagy nevek
	 */
	public String[] getLeaderBoardValues(String value) {
		JSONParser jsonParser = new JSONParser();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			Object object = jsonParser.parse(new FileReader(classLoader.getResource("leaderBoard.json").getFile()));
			JSONObject jsonObject = (JSONObject) object;
			return ((String) jsonObject.get(value)).split("'");

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Kiírja az állományba a frissített eredménytáblát.
	 * 
	 * @param name
	 *            kiírandó játékos neve
	 * @param score
	 *            kiírandó játékos pontja
	 */
	public void makeLeaderBoardFile(String name, Integer score) {
		JSONObject obj = new JSONObject();
		String[] names = getLeaderBoardValues("names");
		String[] scores = getLeaderBoardValues("scores");
		for (int i = 0; i < scores.length; i++) {
			if (score >= Integer.parseInt(scores[i])) {
				int scoreSwapper = Integer.parseInt(scores[i]);
				scores[i] = score.toString();
				score = scoreSwapper;
				String nameSwapper = names[i];
				names[i] = name;
				name = nameSwapper;
			}
		}
		obj.put("names", stringMaker(names));
		obj.put("scores", stringMaker(scores));
		ClassLoader classLoader = getClass().getClassLoader();
		try (FileWriter file = new FileWriter(classLoader.getResource("leaderBoard.json").getFile())) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		logger.info("Sikeres eredménytáblázat elkészítése és lementése.");
	}

	/**
	 * Kiolvassa az aktuális játékos nevét.
	 * 
	 * @return játékos neve
	 */
	public String getName() {
		JSONParser jsonParser = new JSONParser();
		try {

			ClassLoader classLoader = getClass().getClassLoader();
			Object object = jsonParser.parse(new FileReader(classLoader.getResource("name.json").getFile()));
			JSONObject jsonObject = (JSONObject) object;
			return (String) jsonObject.get("names");

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Kiírja az aktuális játékos nevét.
	 * 
	 * @param name
	 *            a játékos neve
	 */
	public void setName(String name) {
		JSONObject obj = new JSONObject();

		obj.put("names", name);

		ClassLoader classLoader = getClass().getClassLoader();

		try (FileWriter file = new FileWriter(classLoader.getResource("name.json").getFile())) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		logger.info("Sikeres név megadás és lementés.");

	}

	/**
	 * Segédfüggvény, a kiíráshoz formátumára alakítja az adott String tömböt.
	 * 
	 * @param rawString
	 *            az átalakítandó String tömb
	 * @return kiírásra alkalmas szöveg
	 */
	public String stringMaker(String[] rawString) {
		StringBuilder rv = new StringBuilder();
		for (String string : rawString) {
			rv.append(string).append("'").toString();
		}
		return rv.toString();
	}

}