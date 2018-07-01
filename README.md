Chess engine:

From non coding perspective, want:

1. A chessboard
2. The ability to play games 
3. The ability to load games
4. The ability to play vs ai and vs people
5. Rule checking in game
6. Ability to store repertoire 
7. The ability to save a game
8. Timer for play

From coding perspective need:

Front end:
* all chess piece graphics
* all visual components (load game, save game, new game)
* various sub screens (new game should open up its own screen)
* allow drag + click interactivity with chess pieces
* “fancy features” (highlighting possible moves?, board themes?) - do last

Backend:
* all chess rules coded in
* conversion of game states into strings (PGN/FEN, probably both)
* building a half decent ai
* save/load locations
* Repertoire structure

Classes needed: 

Backend: 
* Piece superclass/interface
	* Pawn, knight, bishop, rook, queen, king
	* methods: move, capture
	* state: location, owner(?)
* Board
	* methods: initialize
	* state: players, timer
* Rule set (To allow for fisher random?)
* Load 
* Save
* Repertoire (Serializable?)
	* Move stored
	* List of repertoires
* Move
	* methods: toString
	* state: piece, source, destination, capture (boolean)
* Location
	* state: row + column

Frontend: 
* Start screen (for new game)
* main screen
* Menu (With save/load/etc… buttons)
* Displays (for timer, pgn/fen)

