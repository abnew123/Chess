package backend;

/**
 * class to represent a single square on a chessboard
 * IMPORTANT: ranks and files are NOT 0 indexed, but 1 indexed in accordance with common chess nomenclature
 * @author shichengrao
 *
 */
//TODO make new exception for invalid square
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
	public int getFile() {
		return file;
	}

    public int getRank() {
    		return rank;
    }

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
	@Override
	public int compareTo(Object o) {
		if(!o.getClass().equals(this.getClass())){
			return -1;
		}
		Square other = (Square) o;
		if(other.getRank() != rank) {
			return other.getRank() - rank;
		}
		return other.getFile() - file;
	}
}
