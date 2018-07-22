package backend.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import backend.HalfTurn;
import backend.Position;

public class FinishedGame{
	private String winner;
	private List<HalfTurn> moves;
	public static List<String> outcomes = new ArrayList<String>() {{add("1-0"); add("1/2-1/2"); add("0-1");}};
	public FinishedGame(String winner, List<HalfTurn> moves) {
		this.winner = winner;
		this.moves = moves;
	}
	public FinishedGame(UnfinishedGame game) {
		this(game.getPGN());
	}
	public FinishedGame(String PGN) {
		moves = new ArrayList<>();
		String[] lines = PGN.split("(\\r\\n|\\r|\\n)");
		System.out.println(Arrays.asList(lines));
		boolean actualMoves = false;
		Position position = new Position();
		boolean turn = true;
		for(int i = 0; i < lines.length; i++) {
			if(lines[i].length() != 0 && lines[i].substring(0, 1).equals("1")) {
				actualMoves = true;
			}
			if(actualMoves) {
				List<String> moveList = Arrays.asList(lines[i].split("\\d+\\.|\\{.*\\}|\\!|\\?|;.*|\\.+"));
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
						System.out.println(position.toSimplifiedFEN());
					}
					
				}
			}
		}
		if(winner == null) {
			winner = "UNKNOWN";
		}
	}
	public String getWinner() {
		return winner;
	}

	public List<HalfTurn> getTurns() {
		return moves;
	}
	 
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

}
