package application;

public enum SquareType {
	
	PLAIN (1),
	DOUBLE_LETTER(2),
	TRIPLE_LETTER(3),
	DOUBLE_WORD(2),
	TRIPLE_WORD(3),
	CENTRAL_SQUARE(2);
	
	private final int multiplier;
	
	SquareType(int multiplier) {
		this.multiplier = multiplier;
	}
	
	public static void main(String[] args) {
		for(SquareType x : SquareType.values()) { // this method values() converts different types of value into strings
			System.out.printf("A %s cell has a multiplier of %d.%n", x, x.multiplier());
		}
	}
	
	private int multiplier() {
		return multiplier;
	}
	
}