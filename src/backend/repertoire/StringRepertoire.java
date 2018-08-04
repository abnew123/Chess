package backend.repertoire;

import java.util.ArrayList;

import backend.HalfTurn;
/**
 * tentative concept for a repertoire
 * @author shichengrao
 *
 */
public class StringRepertoire extends Repertoire {
	private String move;
	
	public StringRepertoire(HalfTurn move) {
		children = new ArrayList<>();
		this.move = move.toString();
	}
	
	public StringRepertoire(String move) {
		children = new ArrayList<>();
		this.move = move;
	}
	@Override
	public String toString() {
		String result = move;
		for(Repertoire child: children) {
			if(!child.equals(children.get(0))) {
				result += shift(child.toString())+ "\n";
			}
			else {
				for(int i = 0; i < (4-move.length());i++) {
					result+= " ";
				}
				result += shift(child.toString()).substring(4) + "\n";
			}
		}
		return result;
	}
	
	private String shift(String string) {
		String result = "";
		String[] byRow = string.split("\\n");
		for(String row: byRow) {
			result+="    " + row + "\n";
		}
		return result.substring(0, result.length() - 1);
	}

}
