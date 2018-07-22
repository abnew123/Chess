package frontend.views;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MoveListView extends TitledPane implements View {
	private ListView<HBox> myInfo;
	private int movenumber;
	public MoveListView(String PGN) {
		movenumber = 1;
		myInfo = new ListView<>();
		setUpInfo(PGN);
		setContent(myInfo);
		setText("Move List");
		setExpanded(true);
	}
	private HBox makeBox(String[] moves, int index) {
		HBox move = new HBox();
		move.getChildren().addAll(
				new Text(moves[index]){{setWrappingWidth(50);}},
				new Text(moves[index + 1]){{setWrappingWidth(150);}},
				new Text((index+2 == moves.length)? "": moves[index + 2]){{setWrappingWidth(150);}}
				);
		move.setAlignment(Pos.CENTER);
		return move;
	}
	
	private void setUpInfo(String PGN) {
		String[] moves = PGN.split(" |\n");
		List<HBox> items = new ArrayList<>();
		for(int i = 0; i < moves.length; i+=3) {
			HBox move = makeBox(moves, i);
			items.add(move);
		}
		myInfo.getItems().setAll(items);
	}
	public void incrementMove() {
		movenumber += (movenumber % 3 == 2)? 2:1;
		update();
	}
	public void decrementMove() {
		movenumber -= (movenumber % 3 == 2)? 1:2;
		update();
	}
	private void update() {
		
	}
}
