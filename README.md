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
9. Have separate profiles for each player

From coding perspective need:

Front end:
* all chess piece graphics
* all visual components (load game, save game, new game)
* various sub screens (new game should open up its own screen)
* allow drag + click interactivity with chess pieces
* “fancy features” (highlighting possible moves?, board themes?) - do last
* allow user to choose user profile

Backend:
* all chess rules coded in
* conversion of game states into strings (PGN/FEN, probably both)
* building a half decent ai
* save/load locations
* Repertoire structure
* load user profile

Classes needed: 

Backend: 
* Piece superclass/interface
	* Pawn, knight, bishop, rook, queen, king
	* methods: move, capture
	* state: location, color
* Board
	* methods: initialize, toString
	* state: players, timer, Position
* Position
	* methods: toFEN
	* state: 2d array of Squares
* Square
	* methods: toString
	* state: row, column
* Game
	* methods: load and save
	* state: Player1, Player2, List of Turns
* Half-Turn (hierarchy: subclasses whitehalfturn and blackhalfturn)
	* methods: toString
	* state: move, prePosition, postPosition
* Full turn
	* methods: toString
	* state: whitehalfturn, blackhalfturn
* Repertoire (Serializable?)
	* methods: add(repertoire), returnChildren
	* stateL halfTurn stored, List of repertoires
* Move
	* state: piece, source, destination, capture (boolean), enpasssant (boolean), castling (boolean)
* User
	* methods: load/save
	* state: list of games

Frontend: 
* Start screen (for new game)
* main screen
* Menu (With save/load/etc… buttons)
* Displays (for timer, pgn/fen)

