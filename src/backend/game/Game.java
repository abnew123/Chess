package backend.game;

import java.util.List;

import backend.HalfTurn;
import backend.Position;
/**
 * represents a chess game
 * @author shichengrao
 *
 */
public class Game {
	protected List<HalfTurn> moves;
	protected List<Position> positions;
	/**
	 * returns all moves in game
	 * @return
	 */
	public List<HalfTurn> getTurns() {
		return moves;
	}
	/**
	 * returns PGN of game
	 * TODO add in tags, and winner
	 * @return
	 */
	public String getPGN() {
		//return moves.stream().map(HalfTurn::toString).collect(Collectors.joining( " " ));
		String result = "";
		for(int i = 0; i < moves.size(); i++) {
			if(i%2 == 0) {
				if(i!=0) {
					result +="\n";
				}
				result += 1+ (i/2);
				result+= ". ";
			}
			else {
				result += " ";
			}
			result+= moves.get(i).toString();
		}
		return result;
	}
	/**
	 * gets the position of the game at moveNumber
	 * @param moveNumber
	 * @return
	 */
	public Position getPosition(int moveNumber) {
		return positions.get(moveNumber);
	}
}
