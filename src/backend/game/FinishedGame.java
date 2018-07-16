package backend.game;

import java.util.List;

import backend.HalfTurn;

public class FinishedGame{
	private String winner;
	private List<HalfTurn> moves;

	public FinishedGame(String winner, List<HalfTurn> moves) {
		this.winner = winner;
		this.moves = moves;
	}
	public FinishedGame(UnfinishedGame game) {
		this(game.getPGN());
	}
	public FinishedGame(String PGN) {
		//TODO add compatibility to normal games
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
