package backend.game;

import java.util.ArrayList;
import java.util.List;

import backend.HalfTurn;
import backend.Position;

public class UnfinishedGame {
	private List<HalfTurn> moves;
	private Position currentPosition;
	
	public UnfinishedGame() {
		moves = new ArrayList<>();
		currentPosition = new Position();
	}
	
	public void addPly(HalfTurn ply) {
		moves.add(ply);
		//TODO update position based on ply
	}
	
	public String getWinner() {
		return "UNKNOWN";
	}

	public List<HalfTurn> getTurns() {
		return moves;
	}
	public String currentFEN() {
		String result = currentPosition.toFEN();
		
		
		return result;
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
