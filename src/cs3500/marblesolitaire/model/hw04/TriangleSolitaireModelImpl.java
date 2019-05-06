package cs3500.marblesolitaire.model.hw04;


import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.EuropeanAndEnglishModel;
import cs3500.marblesolitaire.model.hw02.Cell;
import cs3500.marblesolitaire.model.hw02.CellState;
import cs3500.marblesolitaire.model.hw02.Legalness;

/**
 * Represents Triangular version of marble solitaire game.
 */
public class TriangleSolitaireModelImpl extends EuropeanAndEnglishModel {

  /**
   * Constructs triangular Marble Solitaire model with all parameters custom.
   *
   * @param dimension side length
   * @param emptyRow  row position of the empty cell
   * @param emptyCol  col position of the empty cell
   */
  public TriangleSolitaireModelImpl(int dimension, int emptyRow, int emptyCol) {
    super(dimension, dimension);
    initializeTriangular(emptyRow, emptyCol);
  }

  /**
   * Constructs default triangular model with size 5 and empty cell at (0,0).
   */
  public TriangleSolitaireModelImpl() {
    this(5, 0, 0);
  }

  /**
   * Constructs triangular model with custom size and empty cell at (0,0).
   *
   * @param dimension side length
   */
  public TriangleSolitaireModelImpl(int dimension) throws IllegalArgumentException {
    this(dimension, 0, 0);
  }

  /**
   * Constructs triangular model with size 5 and custom empty cell position.
   *
   * @param emptyRow row position of the empty cell
   * @param emptyCol col position of the empty cell
   */
  public TriangleSolitaireModelImpl(int emptyRow, int emptyCol) {
    this(5, emptyRow, emptyCol);
  }


  /**
   * Initializes a triangular board. Places marbles on their spots and creates an empty cell.
   *
   * @param emptyRow row position of the empty cell
   * @param emptyCol col position of the empty cell
   * @throws IllegalArgumentException if empty cell col or row position is illegal.
   */
  private void initializeTriangular(int emptyRow, int emptyCol)
          throws IllegalArgumentException {

    for (int row = 0; row < arm; row++) {
      for (int col = 0; col <= row; col++) {
        cells[row][col].makeMarble();
      }
    }
    if (emptyRow < 0 || emptyRow > (arm - 1)
            || emptyCol < 0 || emptyCol > (arm - 1)
            || cells[emptyRow][emptyCol].getState() == CellState.ILLEGAL) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + emptyRow + "," + emptyCol + ")");
    } else {
      cells[emptyRow][emptyCol].makeEmpty();
    }
  }

  @Override
  protected boolean checkArmLegalness(int arm) {
    if (arm <= 0) {
      throw new IllegalArgumentException("Triangle dimension must be a positive number");
    } else {
      return true;
    }
  }

  @Override
  public String getGameState() {
    char[][] output = new char[cells.length][];

    for (int row = 0; row < cells.length; row++) {
      int offset = arm - row - 1;
      char[] line = new char[rowLength(row) * 2 - 1 + offset];

      for (int i = 0; i <= offset; i++) {
        line[i] = ' ';
      }
      for (int col = 0; col < line.length; col++) {
        if (col >= offset) {
          for (int index = 0; index < rowLength(row); index++) {

            if (index != rowLength(row) - 1) {
              line[col] = cells[row][index].asChar();
              line[col + 1] = ' ';
              col += 2;

            } else {
              line[col] = cells[row][index].asChar();
            }
          }
          output[row] = line;
        }
      }
    }
    return buildBoard(output);
  }


  @Override
  protected Cell findMiddleCell(int fromRow, int fromCol, int toRow, int toCol) {
    Cell middle = new Cell(-1, -1);
    if (fromRow == toRow) {
      if (toCol > fromCol) {
        middle = cells[fromRow][toCol - 1];
      } else {
        middle = cells[fromRow][fromCol - 1];
      }
    } else if (toRow > fromRow) {
      if (toCol > fromCol) {
        middle = cells[fromRow + 1][fromCol + 1];
      } else {
        middle = cells[fromRow + 1][fromCol];
      }
    } else if (toRow < fromRow) {
      if (toCol < fromCol) {
        middle = cells[toRow + 1][toCol + 1];
      } else {
        middle = cells[toRow + 1][toCol];
      }
    }
    return middle;
  }


  @Override
  protected void addTriangularMoves(int fromRow, int fromCol, ArrayList<Cell> legalMoves) {
    /*    Check diagonal move down and right     */
    if (legalToMove(fromRow, fromCol, fromRow + 2, fromCol + 2) == Legalness.OK) {
      legalMoves.add(cells[fromRow + 2][fromCol]);
    }
    /*    Check diagonal move down and left     */
    if (legalToMove(fromRow, fromCol, fromRow + 2, fromCol - 2) == Legalness.OK) {
      legalMoves.add(cells[fromRow + 2][fromCol]);
    }

    /*    Check diagonal move up and right     */
    if (legalToMove(fromRow, fromCol, fromRow - 2, fromCol) == Legalness.OK) {
      legalMoves.add(cells[fromRow + 2][fromCol]);
    }
    /*    Check diagonal move up and left     */
    if (legalToMove(fromRow, fromCol, fromRow - 2, fromCol) == Legalness.OK) {
      legalMoves.add(cells[fromRow + 2][fromCol]);
    }
  }

  @Override
  protected void addUpAndDownMoves(int fromRow, int fromCol, ArrayList<Cell> legalMoves) {
    /*
    Since this is a Triangular model, no up and down moves should be added
     */
  }

  @Override
  protected boolean diagonalsAreLegal() {
    return true;
  }
}
















