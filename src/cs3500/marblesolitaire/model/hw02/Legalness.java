package cs3500.marblesolitaire.model.hw02;

/**
 * Represents a legal status of a move.
 * OK - move is legal
 * FROM_OUT_OF_BOARD - move is made from a cell that does not exist (Out of bound)
 * TO_OUT_OF_BOARD - move is made to a cell that does not exist (Out of bound)
 * FROM_ILLEGAL_CELL - move is made from a cell that is not part of board
 * TO_ILLEGAL_CELL - move is made to a cell that is not part of board
 * DIAGONAL_MOVE - move is diagonal
 * TOO_FAR - move distance is more than 2 cells
 * TOO_CLOSE - move distance is less than 2 cells
 * MOVE_FROM_EMPTY - move is made from empty cell
 * MOVE_TO_MARBLE - move is made to occupied cell
 * NO_MIDDLE_MARBLE - there is no marble in between
 */
public enum Legalness {
  OK, MOVE_TO_MARBLE, MOVE_FROM_EMPTY, NO_MIDDLE_MARBLE, DIAGONAL_MOVE,
  TOO_FAR, TOO_CLOSE,TO_ILLEGAL_CELL, FROM_ILLEGAL_CELL, FROM_OUT_OF_BOARD, TO_OUT_OF_BOARD,

}
