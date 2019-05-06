package cs3500.marblesolitaire.model.hw02;


/**
 * Represents a model of a Marble Solitaire game.
 */
public class MarbleSolitaireModelImpl extends EuropeanAndEnglishModel {

  /**
   * Constructor for custom arm thickness and empty cell position. First creating a board without an
   * empty cell, then, if emptyRow and emptyCol are correct, places the empty cell
   *
   * @param arm      thickness of the arm
   * @param emptyRow row position of the empty cell
   * @param emptyCol column position of the empty cell
   * @throws IllegalArgumentException if arm thickness is not a positive odd number or empty cell
   *                                  position is invalid
   */
  public MarbleSolitaireModelImpl(int arm, int emptyRow, int emptyCol) throws
          IllegalArgumentException {
    super(arm, arm * 3 - 2);
    initializeEnglish(emptyRow, emptyCol);
  }

  /**
   * Constructor for default game (arm thickness of 3 and empty cell placed in the center).
   */
  public MarbleSolitaireModelImpl() {
    this(3, 3, 3);
  }


  /**
   * Constructor for generation of the game with custom arm thickness. Empty cell is placed in the
   * center
   *
   * @param arm arm thickness
   * @throws IllegalArgumentException if arm thickness is not a positive off number
   */
  public MarbleSolitaireModelImpl(int arm) throws IllegalArgumentException {
    this(arm, (arm * 3 - 2) / 2, (arm * 3 - 2) / 2);
  }

  /**
   * Constructor for generation of the game with custom empty cell position. Arm thickness is 3.
   *
   * @param emptyRow row position of the empty cell
   * @param emptyCol column position of the empty cel
   * @throws IllegalArgumentException if empty cell position is invalid
   */
  public MarbleSolitaireModelImpl(int emptyRow, int emptyCol) throws IllegalArgumentException {
    this(3, emptyRow, emptyCol);

  }


  /**
   * Initializes a board by putting marbels everywhere they should be, including an empty cell.
   *
   * @param emptyRow row position of the empty cell
   * @param emptyCol column position of the empty cell
   * @return 2d Array of cells where each cell that is on board is a marble.
   */
  private void initializeEnglish(int emptyRow, int emptyCol) {
    for (Cell[] row : cells) {
      for (Cell c : row) {
        if (c.getRow() < (arm - 1) || c.getRow() >= (arm * 2 - 1)) {
          if (c.getCol() >= (arm - 1) && c.getCol() < (arm * 2 - 1)) {
            c.makeMarble();

          }
        } else {
          c.makeMarble();
        }
      }
    }
    if (emptyRow < 0 || emptyRow > (arm * 3 - 3)
            || emptyCol < 0 || emptyCol > (arm * 3 - 3)
            || cells[emptyRow][emptyCol].getState() == CellState.ILLEGAL) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + emptyRow + "," + emptyCol + ")");
    } else {
      cells[emptyRow][emptyCol].makeEmpty();
    }

  }

}


