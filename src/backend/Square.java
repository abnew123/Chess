package backend;

/**
 * class to represent a single square on a chessboard
 * IMPORTANT: ranks and files are NOT 0 indexed, but 1 indexed in accordance with common chess nomenclature
 * @author shichengrao
 *
 */
public class Square implements Comparable{
	
	private int file;
	private int rank;
	/**
	 * constructs square 
	 * @param file
	 * @param rank
	 */
	public Square(int file, int rank) {
		if(1 > rank || 1 > file || rank > 8 || file > 8) {
			throw new InvalidSquareException("" + ((char)('a' + file - 1)) + rank);
		}
		this.file = file;
		this.rank = rank;
	}
	/**
	 * constructs square from PGN notation
	 * @param algebraicForm
	 */
	public Square(String algebraicForm) {
		if(!algebraicForm.matches("[a-hA-H]\\d")) {
			throw new InvalidSquareException(algebraicForm);
		}
		rank = Integer.parseInt(algebraicForm.substring(1));
		if(1 > rank || rank > 8) {
			throw new InvalidSquareException(algebraicForm);
		}
		file = algebraicForm.charAt(0) - 'a' + 1;
	}
	/**
	 * returns file of square
	 * @return
	 */
	public int getFile() {
		return file;
	}
	/**
	 * returns rank of square
	 * @return
	 */
    public int getRank() {
    		return rank;
    }
    /**
     * returns PGN notation for square
     */
    public String toString() {
    		return "" + ((char)('a' + file - 1)) + rank;
    }
    
    /**
     * calculates taxi-cab distance to another square
     * @param otherSquare
     */
    public int distanceToOther(Square otherSquare) {
    		return Math.abs(file - otherSquare.getFile()) + Math.abs(rank - otherSquare.getRank());
    }
    /**
     * assigns each square a hashcode from 1 to 64
     */
    @Override
    public int hashCode() {
    		return file + (rank - 1) * 8;
    }
    /**
     * compares two squares by rank and then by file
     * returns > 0 higher up the board, or farther right and equally up the board
     */
	@Override
	public int compareTo(Object o) {
		if(! (o instanceof Square)) {
			return -1;
		}
		Square other = (Square) o;
		if(other.getRank() != rank) {
			return rank - other.getRank();
		}
		return file - other.getFile();
	}
    /**
     * returns whether o is the same as the current sqaure
     */
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Square)) {
			return false;
		}
		return o.toString().equals(toString());
	}
}
