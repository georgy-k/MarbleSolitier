package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EuropeanAndEnglishModel;
import cs3500.marblesolitaire.model.hw02.CellState;


public class EuropeanSolitaireModelImpl extends EuropeanAndEnglishModel {


  public EuropeanSolitaireModelImpl(int arm, int emptyRow, int emptyCol) throws
          IllegalArgumentException {
    super(arm, arm * 3 - 2);
    initializeEuropen(emptyRow, emptyCol);
  }

  /**
   * Constructor for default game (arm thickness of 3 and empty cell placed in the center).
   */
  public EuropeanSolitaireModelImpl() {
    this(3, 3, 3);
  }


  /**
   * Constructor for generation of the game with custom arm thickness. Empty cell is placed in the
   * center
   *
   * @param arm arm thickness
   * @throws IllegalArgumentException if arm thickness is not a positive off number
   */
  public EuropeanSolitaireModelImpl(int arm) throws IllegalArgumentException {
    this(arm, (arm * 3 - 2) / 2, (arm * 3 - 2) / 2);
  }

  /**
   * Constructor for generation of the game with custom empty cell position. Arm thickness is 3.
   *
   * @param emptyRow row position of the empty cell
   * @param emptyCol column position of the empty cel
   * @throws IllegalArgumentException if empty cell position is invalid
   */
  public EuropeanSolitaireModelImpl(int emptyRow, int emptyCol) throws IllegalArgumentException {
    this(3, emptyRow, emptyCol);

  }

  private void initializeEuropen(int emptyRow, int emptyCol) {
    int size = 3 * arm - 2;

    for (int i = 0; i < arm - 1; i++) {
      int offset = arm - 1 - i;
      for (int j = offset; j < size - offset; j++) {
        cells[i][j].makeMarble();
      }
    }
    for (int i = arm - 1; i < 2 * arm - 1; i++) {
      for (int j = 0; j < size; j++) {
        cells[i][j].makeMarble();
      }
    }
    for (int i = 0; i < arm - 1; i++) {
      int offset = arm - 1 - i;
      for (int j = offset; j < size - offset; j++) {
        cells[size - i - 1][j].makeMarble();
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
