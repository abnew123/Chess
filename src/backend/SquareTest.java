package backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
/**
 * JUnit test for the Square class
 * @author shichengrao
 *
 */
public class SquareTest {

	@Test
	public void testConstructor1() {
		Square square = new Square(2,1);
		assertEquals(square.getFile(), 2);
	}
	@Test
	public void testConstructor2() {
		Square square = new Square("h7");
		assertEquals(square.getRank(), 7);
	}
	@Test
	public void testToString() {
		Square square = new Square("h7");
		assertEquals(square.toString(), "h7");
	}
	@Test
	public void testException() {
		try {
			@SuppressWarnings("unused")
			Square square = new Square(23, 1);
			fail("missed exception");
		} catch(InvalidSquareException e) {
			System.out.println(e);
		}
		
	}

}
