package cs3500.marblesolitaire.model.hw02;

/**
 * Represents a single cell of the Marble Solitaire game.
 */
public class Cell {
  /**
   * MODIFIED: set accessors to private and added getters to satisfy invariance covered in class.
   * MODIFIED: Initially had 2 boolean fields (isField, isMarble) now replaced with enum CellState.
   * Professor advised me to do so in order to make my definition more invariant. Now can be:
   * CellState.ILLEGAL (not a game field) CellState.MARBLE (cell with marble) and CellState.EMPTY
   * (empty cell)
   */
  private final int row;
  private final int col;
  private CellState state;


  /**
   * Construct default cell that is an illegal game cell.
   *
   * @param row row position
   * @param col column position
   */
  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
    this.state = CellState.ILLEGAL;

  }

  /**
   * Returns character representation of this cell. Where marble is '0', empty cell is '_' and not a
   * game field is ' '.
   *
   * @return character representation
   */
  public char asChar() {
    if (this.state == CellState.ILLEGAL) {
      return ' ';
    } else if (this.state == CellState.MARBLE) {
      return 'O';
    } else {
      return '_';
    }
  }


  /**
   * Makes this cell empty.
   */
  public void makeEmpty() {
    this.state = CellState.EMPTY;
  }

  /**
   * Makes this cell to be Marble.
   */
  public void makeMarble() {
    this.state = CellState.MARBLE;
  }


  /**
   * Checks equality of this cell and other object. Two cells are equal if their fields are equal.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof Cell) {
      Cell other = (Cell) object;
      return this.state == other.state
              && this.col == other.col
              && this.row == other.row;
    } else {
      return false;
    }
  }

  /**
   * Returns row position of this cell.
   *
   * @return row
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Retruns column position of this cell.
   *
   * @return column
   */
  public int getCol() {
    return this.col;
  }

  /**
   * Retruns current state of this cell.
   *
   * @return enum value of CellState
   */
  public CellState getState() {
    return this.state;
  }

  /**
   * New hashcode to match equality notion.
   */
  @Override
  public int hashCode() {
    return this.col * this.row;
  }
}
