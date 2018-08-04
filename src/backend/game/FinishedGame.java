package backend.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import backend.HalfTurn;
import backend.Position;
/**
 * models a game for which the full game has already been played.
 * TODO: add info for things like white and black players, location (PGN tags)
 * @author shichengrao
 *
 */
public class FinishedGame extends Game{
	private String winner;
	public static List<String> outcomes = new ArrayList<String>() {{add("1-0"); add("1/2-1/2"); add("0-1");}};
	/**
	 * constructs a game from a list of moves and the winner.
	 * @param winner
	 * @param moves
	 */
	public FinishedGame(String winner, List<HalfTurn> moves) {
		this.winner = winner;
		this.moves = moves;
		positions = new ArrayList<>();
		positions.add(new Position());
		for(HalfTurn move: moves) {
			Position nextPosition = positions.get(positions.size() - 1);
			nextPosition.update(move);
			positions.add(nextPosition);
		}
	}
	/**
	 * constructs a game based on an unfinished game
	 * probably wont be used?
	 * TODO: delete if not used
	 * @param game
	 */
	public FinishedGame(UnfinishedGame game) {
		this(game.getPGN());
	}
	/**
	 * constructs a game based on a PGN
	 * @param PGN
	 */
	public FinishedGame(String PGN) {
		moves = new ArrayList<>();
		//the next line is to maintain windows, linux, and old MacOS compatibility.
		String[] lines = PGN.split("(\\r\\n|\\r|\\n)");
		boolean actualMoves = false;
		Position position = new Position();
		boolean turn = true;
		for(int i = 0; i < lines.length; i++) {
			if(lines[i].length() != 0 && lines[i].substring(0, 1).equals("1")) {
				actualMoves = true;
			}
			if(actualMoves) {
				//the next two lines normalize and separate the moves in a PGN, strips off either type of comment, and removes the most common annotation glyps of ? and !
				List<String> moveList = Arrays.asList(lines[i].split("\\d+\\.|\\{[^}]*\\}|\\!|\\?|;.*|\\.+"));
				List<String> realMoves = moveList.stream().flatMap(
						Pattern.compile(" ") ::splitAsStream).filter(a -> !a.equals("")).collect(
								Collectors.toList());
				for(int j = 0; j < realMoves.size(); j++) {
					if(outcomes.contains(realMoves.get(j))) {
						if(outcomes.get(0).equals(realMoves.get(j))) {
							winner = "WHITE";
						}
						if(outcomes.get(1).equals(realMoves.get(j))) {
							winner = "DRAW";
						}
						if(outcomes.get(2).equals(realMoves.get(j))) {
							winner = "BLACK";
						}
					}
					else {
						HalfTurn ply = new HalfTurn(realMoves.get(j), new Position(position), turn);
						turn = !turn;
						moves.add(ply);
						position.update(ply);
					}
					
				}
			}
		}
		if(winner == null) {
			winner = "UNKNOWN";
		}
	}
	/**
	 * returns the winner of the game
	 * @return
	 */
	public String getWinner() {
		return winner;
	}

}
