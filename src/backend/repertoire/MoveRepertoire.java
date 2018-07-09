package backend.repertoire;

import java.util.ArrayList;

import backend.HalfTurn;

public class MoveRepertoire extends Repertoire {
	private HalfTurn move;
	public MoveRepertoire(HalfTurn initialMove){
		children = new ArrayList<>();
		move = initialMove;
	}
	@Override
	public String toString() {
		String result = move.toString();
		for(Repertoire child: children) {
			result += "    " + child.toString() + "\n";
		}
		return result;
	}
}
