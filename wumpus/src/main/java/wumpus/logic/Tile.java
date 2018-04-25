package wumpus.logic;

public class Tile {

	private long rowNumber;
	private long colNumber;
	private long value;

	public long getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(long rowNumber) {
		this.rowNumber = rowNumber;
	}

	public long getColNumber() {
		return colNumber;
	}

	public void setColNumber(long colNumber) {
		this.colNumber = colNumber;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public Tile() {
		
	}
	
	public Tile(long rowNumber, long colNumber, long value) {
		super();
		this.rowNumber = rowNumber;
		this.colNumber = colNumber;
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (colNumber ^ (colNumber >>> 32));
		result = prime * result + (int) (rowNumber ^ (rowNumber >>> 32));
		result = prime * result + (int) (value ^ (value >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (colNumber != other.colNumber)
			return false;
		if (rowNumber != other.rowNumber)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value + " ";
	}

}
