package frontend.views;

import backend.HalfTurn;
import backend.Position;
import backend.Square;
import backend.game.UnfinishedGame;
import backend.piece.Piece;
import backend.user.User;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class PlayGameBoardView extends Group implements View {
	
	private UnfinishedGame game;
	private Position position;
	private boolean flag = false;
	private Piece selectedPiece = null;
	public static final int SQUARE_SIZE = 60;
	private Square source;
	private User user;

	public PlayGameBoardView(User user){
		position = new Position();
		game = new UnfinishedGame();
		
		this.user = user;
		initGrid();
		updatePosition();
		onMouseClick();
	}
	
	public void initGrid() {
		getChildren().clear();
		Group boardTiles = user.makeBoard(SQUARE_SIZE);
		getChildren().add(boardTiles);
		
	}
	
	public void updatePosition() {
		getChildren().clear();
		initGrid();
		getChildren().add(user.makePieces(SQUARE_SIZE, position));
	}
	
	// works when clicking top left corner. need to do better.
	public void onMouseClick() {
		for(Node n: getChildren()){
			if(((Group)n).getChildren().size() == 64){
				for(Node node: ((Group)n).getChildren()) {
					if(node.getClass().equals((new Rectangle()).getClass())) {
						double xPos = node.getBoundsInLocal().getMinX();
						double yPos = node.getBoundsInLocal().getMinY();
						node.setOnMousePressed(new EventHandler<MouseEvent>(){
							@Override public void handle(MouseEvent e){
								if(flag) {
									Square destination = new Square(1 + (int)xPos/SQUARE_SIZE, 8 - (int)yPos/SQUARE_SIZE);
									HalfTurn move = new HalfTurn(selectedPiece, null, source, destination, position);
									if(game.addPly(move)) {
										position.update(move);
										initGrid();
										updatePosition();
										onMouseClick();
									}
									flag = false;
								}
								else {
									source = new Square(1 + (int)xPos/SQUARE_SIZE, 8 - (int)yPos/SQUARE_SIZE);
									System.out.println(position.getPieceOnSquare(source));
									if(position.getPieceOnSquare(source) != null) {
										selectedPiece = position.getPieceOnSquare(source);
										//highlights the squares that the piece can go
										//addHighlights(selectedPiece, source);
										flag = true;
									}
								}	
							}
						});
					}
				}
				}
			}
			
	}
//	private void addHighlights(Piece piece, Square source) {
//		List<Square> possibleMoves = piece.possibleMovesFull(game.getPosition(), source);
//		for(Square possibility: possibleMoves) {
//			ImageView image = new ImageView();
//			image.setFitHeight(SQUARE_SIZE);
//			image.setFitWidth(SQUARE_SIZE);
//			image.setX((possibility.getFile() - 1) * SQUARE_SIZE);
//			image.setY((8 - possibility.getRank())*SQUARE_SIZE);
//			image.setImage(new Image("images/rcd.png"));
//			getChildren().add(image);
//		}
//	}
}
