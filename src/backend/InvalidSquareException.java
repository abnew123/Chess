package backend;

public class InvalidSquareException extends RuntimeException {
/**
 * occurs when a square cannot exist on a normal chessboard
 * @param square
 */
    public InvalidSquareException(String square) {
        super(square);
    }
}