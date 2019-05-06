package cs3500.marblesolitaire.model.hw02;


import java.util.ArrayList;

/**
 * Represents abstract Marble Solitaire model. Provides ALL functionality for English and European
 * versions. Provides move, isGameOver, getScore functionality for triangular version
 */
public class EuropeanAndEnglishModel implements MarbleSolitaireModel {
  protected int arm;
  public Cell[][] cells;

  /**
   * Initializes a game model, with 2d array of default cells (state = cellState.ILLEGAL).
   *
   * @param arm       Arm thickness (dimension for triangle).
   * @param boardSize Size of the array that needed to be created
   */
  public EuropeanAndEnglishModel(int arm, int boardSize) {
    if (checkArmLegalness(arm)) {
      this.arm = arm;
      this.cells = createBoard(boardSize);

    }
  }

  /**
   * Checks whether arm size is legal for this model.
   *
   * @param arm arm thickness
   * @return true if arm is legal
   * @throws IllegalArgumentException if arm is illegal
   */
  protected boolean checkArmLegalness(int arm) {
    if (arm <= 0 || (arm % 2) == 0) {
      throw new IllegalArgumentException("The arm thickness is not a positive odd number");
    } else {
      return true;
    }
  }

  /**
   * Creates a board as a 2d array with empty cells in it.
   *
   * @param side the length of a single row computed as (arm * 3 - 2)
   * @return 2d Array of default (empty cells)
   */
  private Cell[][] createBoard(int side) {
    Cell[][] board = new Cell[side][];

    for (int i = 0; i < side; i++) {
      Cell[] row = new Cell[side];
      board[i] = row;
      for (int j = 0; j < side; j++) {
        row[j] = new Cell(i, j);
      }
    }
    return board;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (legalToMove(fromRow, fromCol, toRow, toCol) == Legalness.OK) {
      cells[fromRow][fromCol].makeEmpty();
      findMiddleCell(fromRow, fromCol, toRow, toCol).makeEmpty();
      cells[toRow][toCol].makeMarble();
    } else {
      throw new IllegalArgumentException(errorMessage(fromRow, fromCol, toRow, toCol));
    }
  }

  /**
   * Checks Legalness enum value returned from legalToMove and transforms it into a message. Row and
   * Col are incremented by 1 for a more user friendly message. Please see legalToMove method for
   * each enum value explanation.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   */
  private String errorMessage(int fromRow, int fromCol, int toRow, int toCol) {
    Legalness reason = legalToMove(fromRow, fromCol, toRow, toCol);

    switch (reason) {
      case FROM_OUT_OF_BOARD:
        return "Cell (" + (fromRow + 1) + "," + (fromCol + 1) + ") does not exist";
      case TO_OUT_OF_BOARD:
        return "Cell (" + (toRow + 1) + "," + (toCol + 1) + ") does not exist";
      case FROM_ILLEGAL_CELL:
        return "Cell (" + (fromRow + 1) + "," + (fromCol + 1) + ") is not a part of the board";
      case TO_ILLEGAL_CELL:
        return "Cell (" + (toRow + 1) + "," + (toCol + 1) + ") is not a part of the board";
      case DIAGONAL_MOVE:
        return "Move can't be diagonal";
      case TOO_FAR:
        return "Move distance can not be more than 2 cells";
      case TOO_CLOSE:
        return "Move distance can not be less than 2 cells";
      case MOVE_FROM_EMPTY:
        return "You can not move from (" + (fromRow + 1) + "," + (fromCol + 1) + ")"
                + " because it is empty";
      case MOVE_TO_MARBLE:
        return "You can not move to (" + (toRow + 1) + "," + (toCol + 1) + ")"
                + " because there is marble";
      case NO_MIDDLE_MARBLE:
        return "You can move only over a marble";
      default:
        return "Something bad happened, it definitely shouldn't";
    }
  }

  /**
   * Checks direction of the move and returns the cell between two given coordinates.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return cell that "from" cell will jump over
   */
  protected Cell findMiddleCell(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow == toRow) {
      if (toCol > fromCol) {
        return cells[fromRow][toCol - 1];
      } else {
        return cells[fromRow][fromCol - 1];
      }
    } else {
      if (toRow > fromRow) {
        return cells[toRow - 1][toCol];
      } else {
        return cells[fromRow - 1][toCol];
      }
    }
  }

  /**
   * Generates ArrayList with cells that cell with given fromRow and fromCol can move to. Checks all
   * possible 4 direction of the move, and adds destination cell to the ArrayList if move is legal.
   * Used for a GameOver check. MODIFIED: all model will check for left and right moves. All other
   * moves are delegated to helpers (addTriangularMoves() and addUpAndDownMoves()). These helpers
   * use dynamic dispatch to add moves specific to each model.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @return ArrayList of cells with all legal destination cells.
   */
  protected ArrayList<Cell> legalMoves(int fromRow, int fromCol) {

    ArrayList<Cell> legalMoves = new ArrayList<Cell>();

    /*    Check move right     */
    if (legalToMove(fromRow, fromCol, fromRow, fromCol + 2) == Legalness.OK) {
      legalMoves.add(cells[fromRow][fromCol + 2]);
    }
    /*    Check move left     */
    if (legalToMove(fromRow, fromCol, fromRow, fromCol - 2) == Legalness.OK) {
      legalMoves.add(cells[fromRow][fromCol - 2]);
    }
    addTriangularMoves(fromRow, fromCol, legalMoves);
    addUpAndDownMoves(fromRow, fromCol, legalMoves);


    return legalMoves;
  }

  /**
   * Adds moves that are only specific for Triangular version.
   *
   * @param fromRow    the row number of the position to be moved from (starts at 0)
   * @param fromCol    the column number of the position to be moved from (starts at 0)
   * @param legalMoves ArrayList of cells with all legal destination cells found so far.
   */
  protected void addTriangularMoves(int fromRow, int fromCol, ArrayList<Cell> legalMoves) {
    /*
    Since this is a European and English model, no diagonal moves should be added
     */
  }

  /**
   * Adds Up and Down moves that are specific to only English and European versions.
   *
   * @param fromRow    the row number of the position to be moved from (starts at 0)
   * @param fromCol    the column number of the position to be moved from (starts at 0)
   * @param legalMoves ArrayList of cells with all legal destination cells found so far.
   */
  protected void addUpAndDownMoves(int fromRow, int fromCol, ArrayList<Cell> legalMoves) {
    /*    Check move down     */
    if (legalToMove(fromRow, fromCol, fromRow + 2, fromCol) == Legalness.OK) {
      legalMoves.add(cells[fromRow + 2][fromCol]);
    }
    /*    Check move up    */
    if (legalToMove(fromRow, fromCol, fromRow - 2, fromCol) == Legalness.OK) {
      legalMoves.add(cells[fromRow - 2][fromCol]);
    }
  }

  /**
   * MODIFIED: Added a call for diagonalsAreLegal() method to properly handle diagonal moves. This
   * method will use a dynamic dispatch and ensure that in this particular model diagonals are
   * allowed or not allowed.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return Legalness.OK - if move is legal
   *
   * <p>
   * Legalness.FROM_OUT_OF_BOARD - if move is made from a cell that does not exist (Out of bound)
   * </p>
   *
   * <p>
   * Legalness.TO_OUT_OF_BOARD - if move is made to a cell that does not exist (Out of bound)
   * </p>
   *
   * <p>
   * Legalness.FROM_ILLEGAL_CELL - if move is made from a cell that is not part of board
   * </p>
   *
   * <p>
   * Legalness.TO_ILLEGAL_CELL - if move is made to a cell that is not part of board
   * </p>
   *
   * <p>
   * Legalness.DIAGONAL_MOVE - if move is diagonal
   * </p>
   *
   * <p>
   * Legalness.TOO_FAR - if move distance is more than 2 cells
   * </p>
   *
   * <p>
   * Legalness.TOO_CLOSE - if move distance is less than 2 cells
   * </p>
   *
   * <p>
   * Legalness.MOVE_FROM_EMPTY - if move is made from empty cell
   * </p>
   *
   * <p>
   * Legalness.MOVE_TO_MARBLE - if move is made to occupied cell
   * </p>
   *
   * <p>
   * Legalness.NO_MIDDLE_MARBLE - if there is no marble in between
   * </p>
   */
  protected Legalness legalToMove(int fromRow, int fromCol, int toRow, int toCol) {
    int manhDist = Math.abs(toRow - fromRow) + Math.abs(toCol - fromCol);
    if (fromRow < 0 || fromCol < 0 || fromRow > (cells.length - 1)
            || fromCol > (cells.length - 1)) {
      return Legalness.FROM_OUT_OF_BOARD;
    }

    if (toCol < 0 || toRow < 0 || toRow > (cells.length - 1) || toCol > (cells.length - 1)) {
      return Legalness.TO_OUT_OF_BOARD;
    }
    if (cells[fromRow][fromCol].getState() == CellState.ILLEGAL) {
      return Legalness.FROM_ILLEGAL_CELL;
    }
    if (cells[toRow][toCol].getState() == CellState.ILLEGAL) {
      return Legalness.TO_ILLEGAL_CELL;
    }

    if (Math.abs(toRow - fromRow) > 2 || Math.abs(toCol - fromCol) > 2) {
      return Legalness.TOO_FAR;
    }
    if (Math.abs(toRow - fromRow) < 2 && Math.abs(toCol - fromCol) < 2) {
      return Legalness.TOO_CLOSE;
    }
    if (!diagonalsAreLegal()) {
      if (fromRow != toRow && fromCol != toCol) {
        return Legalness.DIAGONAL_MOVE;
      }
    } else if (manhDist != 2 && manhDist != 4) {
      return Legalness.DIAGONAL_MOVE;
    }
    if (cells[fromRow][fromCol].getState() != CellState.MARBLE) {
      return Legalness.MOVE_FROM_EMPTY;
    }
    if (cells[toRow][toCol].getState() == CellState.MARBLE) {
      return Legalness.MOVE_TO_MARBLE;
    }
    if (findMiddleCell(fromRow, fromCol, toRow, toCol).getState() != CellState.MARBLE) {
      return Legalness.NO_MIDDLE_MARBLE;
    }

    return Legalness.OK;
  }

  /**
   * Check if diagonal moves are legal for this model.
   *
   * @return true if legal, false if illegal.
   */
  protected boolean diagonalsAreLegal() {
    return false;
  }

  /**
   * Checks if the game is over by checking if at least one cell has at least 1 legal move.
   *
   * @return true if game is over, false if game is not over
   */
  @Override
  public boolean isGameOver() {
    for (Cell[] row : cells) {
      for (Cell c : row) {
        if (legalMoves(c.getRow(), c.getCol()).size() > 0) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character (O, _ or space for
   * a marble, empty and invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot. Firstly creates
   * Char[][] where each row of cell for game cells is transformed into a corresponding character.
   * Then it calls buildBoard(Char[][] rawBoard) method to construct actual string representation of
   * the board
   *
   * @return string representation of the board
   */
  public String getGameState() {
    char[][] output = new char[cells.length][];

    for (int i = 0; i < cells.length; i++) {
      if (i < (arm - 1) || i >= (arm * 2 - 1)) {
        char[] row = new char[rowLength(i) * 2 - 1];
        for (int j = 0; j < rowLength(i); j++) {
          if (j != rowLength(i) - 1) {

            row[2 * j] = cells[i][j].asChar();
            row[2 * j + 1] = ' ';
          } else {
            row[2 * j] = cells[i][j].asChar();
          }
        }
        output[i] = row;

      } else {
        char[] row = new char[cells.length * 2 - 1];
        for (int j = 0; j < cells[i].length; j++) {
          if (cells[i][j].getCol() < (cells[i].length - 1)) {
            row[2 * j] = cells[i][j].asChar();
            row[2 * j + 1] = ' ';
          } else {
            row[2 * j] = cells[i][j].asChar();
          }
        }
        output[i] = row;
      }
    }
    return buildBoard(output);
  }

  /**
   * Calculates number of cells that needs to be displayed in the given row.
   *
   * @param row index of the row.
   * @return int representing number of marbles.
   */
  protected int rowLength(int row) {
    int length = 0;
    boolean illegalTwice = false;

    for (Cell c : cells[row]) {
      if (c.getState() == CellState.ILLEGAL && illegalTwice) {
        return length;
      } else if (c.getState() == CellState.MARBLE || c.getState() == CellState.EMPTY) {
        illegalTwice = true;
      }
      length += 1;
    }
    return length;
  }

  /**
   * Transforms 2d array of characters into a string.
   *
   * @param rawBoard board representation as 2d Array of characters
   * @return string representation of the board
   */
  protected String buildBoard(char[][] rawBoard) {
    String board = "";
    for (int i = 0; i < rawBoard.length - 1; i++) {
      board += new String(rawBoard[i]) + "\n";
    }
    board += new String(rawBoard[cells.length - 1]);
    return board;
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int score = 0;
    for (Cell[] row : cells) {
      for (Cell c : row) {
        if (c.getState() == CellState.MARBLE) {
          score += 1;
        }
      }
    }
    return score;
  }


}
