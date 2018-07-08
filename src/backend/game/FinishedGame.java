package backend.game;

import java.util.List;
import java.util.stream.Collectors;

import backend.HalfTurn;

public class FinishedGame {
	private String winner;
	private List<HalfTurn> moves;

	public FinishedGame(String winner, List<HalfTurn> moves) {
		this.winner = winner;
		this.moves = moves;
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
				result += i/2;
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
