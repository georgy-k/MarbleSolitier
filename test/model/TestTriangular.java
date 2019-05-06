package model;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;

public class TestTriangular {
  @Test
  /* Tests both constructor and getGameState()*/
  public void testDefaultTriangle() {
    MarbleSolitaireModel triang = new TriangleSolitaireModelImpl();
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", triang.getGameState());


  }

  @Test
  /* Tests both constructor and getGameState()*/
  public void testCustomLengthTriangle() {
    MarbleSolitaireModel triang = new TriangleSolitaireModelImpl(3);
    assertEquals("  _\n" +
            " O O\n" +
            "O O O", triang.getGameState());

    MarbleSolitaireModel triang1 = new TriangleSolitaireModelImpl(1);
    assertEquals("_", triang1.getGameState());

  }

  @Test
  /* Tests both constructor and getGameState()*/
  public void testCustomEmpty() {
    MarbleSolitaireModel triang = new TriangleSolitaireModelImpl(1, 1);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", triang.getGameState());

    MarbleSolitaireModel triang1 = new TriangleSolitaireModelImpl(2, 1);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O", triang1.getGameState());

  }

  @Test
  /* Tests both constructor and getGameState()*/
  public void testAllCustomTriangle() {
    MarbleSolitaireModel triang = new TriangleSolitaireModelImpl(5,
            1, 1);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", triang.getGameState());

    MarbleSolitaireModel triang1 = new TriangleSolitaireModelImpl(10,
            3, 2);
    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O _ O\n" +
            "     O O O O O\n" +
            "    O O O O O O\n" +
            "   O O O O O O O\n" +
            "  O O O O O O O O\n" +
            " O O O O O O O O O\n" +
            "O O O O O O O O O O", triang1.getGameState());


  }


  @Test
  /* Tests constructor's exceptions */
  public void testIllegalArgs() {

    /*Try even dimension that should work*/
    boolean thrown = false;
    try {
      MarbleSolitaireModel game = new TriangleSolitaireModelImpl(2);
    } catch (IllegalArgumentException e) {
      thrown = true;
    }
    assertEquals(false, thrown);
    thrown = false;


    /*Try 0 dimension*/
    try {
      MarbleSolitaireModel game = new TriangleSolitaireModelImpl(0);
    } catch (IllegalArgumentException e) {
      assertEquals("Triangle dimension must be a positive number",
              e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;


    /*Try negative dimension*/
    try {
      MarbleSolitaireModel game = new TriangleSolitaireModelImpl(-1);
    } catch (IllegalArgumentException e) {
      assertEquals("Triangle dimension must be a positive number",
              e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;


    /*Try invalid empty cell*/
    try {
      MarbleSolitaireModel game =
              new TriangleSolitaireModelImpl(3, 0, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)",
              e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;


    /*Try invalid empty cell*/
    try {
      MarbleSolitaireModel game =
              new TriangleSolitaireModelImpl(3, 100, 100);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (100,100)",
              e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;


    /*Try invalid empty cell*/
    try {
      MarbleSolitaireModel game =
              new TriangleSolitaireModelImpl(6, 6, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,6)",
              e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

  }


  @Test
  /*  Tests both move and getGameState */
  public void testMakeMove() {
    MarbleSolitaireModel game1 =
            new TriangleSolitaireModelImpl(5, 0, 0);

    /*
    Make move from (2,0) to (0,0) (MOVE Diagonal (UP and RIGHT))
     */
    game1.move(2, 0, 0, 0);
    String expected1 =
            "    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O";
    assertEquals(expected1, game1.getGameState());


    /*
    Make move from (2,2) to (2,0) (MOVE LEFT)
     */
    game1.move(2, 2, 2, 0);
    String expected2 =
            "    O\n" +
                    "   _ O\n" +
                    "  O _ _\n" +
                    " O O O O\n" +
                    "O O O O O";
    assertEquals(expected2, game1.getGameState());

    /*
    Make move from (2,4) to (2,2) (MOVE DIAGONAL (DOWN and RIGHT)
     */
    game1.move(0, 0, 2, 2);
    String expected3 =
            "    _\n" +
                    "   _ _\n" +
                    "  O _ O\n" +
                    " O O O O\n" +
                    "O O O O O";
    assertEquals(expected3, game1.getGameState());

    /*
    Make move from (4,3) to (2,1) (MOVE DIAGONAL (UP and LEFT)
     */
    game1.move(4, 3, 2, 1);
    String expected4 =
            "    _\n" +
                    "   _ _\n" +
                    "  O O O\n" +
                    " O O _ O\n" +
                    "O O O _ O";
    assertEquals(expected4, game1.getGameState());


    /*
    Make move from (3,0) to (3,2) (MOVE RIGHT)
     */
    game1.move(3, 0, 3, 2);
    String expected5 =
            "    _\n" +
                    "   _ _\n" +
                    "  O O O\n" +
                    " _ _ O O\n" +
                    "O O O _ O";
    assertEquals(expected5, game1.getGameState());

    /*
    Make move from (3,0) to (3,2) (MOVE DIAGONAL (DOWN and LEFT))
     */
    game1.move(3, 2, 1, 0);
    game1.move(1, 0, 3, 0);
    String expected6 =
            "    _\n" +
                    "   _ _\n" +
                    "  _ _ O\n" +
                    " O _ _ O\n" +
                    "O O O _ O";
    assertEquals(expected6, game1.getGameState());

  }

  @Test
  /*  Tests getScore method */
  public void testGetScore() {
    MarbleSolitaireModel game1 = new TriangleSolitaireModelImpl(5, 0, 0);
    assertEquals(14, game1.getScore());
    game1.move(2, 0, 0, 0);
    assertEquals(13, game1.getScore());
    game1.move(2, 2, 2, 0);
    assertEquals(12, game1.getScore());
    game1.move(0, 0, 2, 2);
    assertEquals(11, game1.getScore());
    game1.move(4, 3, 2, 1);
    assertEquals(10, game1.getScore());

  }

  @Test
  /*  Tests exceptions thrown by the move method */
  public void testIllegalMoves() {

    MarbleSolitaireModel game = new TriangleSolitaireModelImpl();

    /*
    Move from invalid game field
     */
    boolean thrown = false;
    try {
      game.move(0, 2, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Cell (1,3) is not a part of the board", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

    /*
    Wrong diagonal move: manhattan distance is neither 2 nor 4
    */
    try {
      game.move(2, 1, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move can't be diagonal", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

    /*
    Move to invalid game field
     */
    try {
      game.move(1, 1, 1, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Cell (2,5) is not a part of the board", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

    /*
    Move to a field that contains ball
     */
    try {
      game.move(4, 1, 4, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("You can not move to (5,4) because there is marble", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

    /*
    Move to a field that does not exist
     */
    try {
      game.move(2, 1, 100, 500);
    } catch (IllegalArgumentException e) {
      assertEquals("Cell (101,501) does not exist", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

    /*
    Move from a field that does not exist
     */
    try {
      game.move(13, 1, 1, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Cell (14,2) does not exist", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

    /*
    From and to cells are the same cell
     */
    try {
      game.move(4, 4, 4, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Move distance can not be less than 2 cells", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

    /*
    No marble in between
     */
    try {
      game.move(2, 0, 0, 0);
      game.move(0, 0, 2, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("You can move only over a marble", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
    thrown = false;

    /*
    Move to far
     */
    try {
      game.move(4, 0, 1, 0);

    } catch (IllegalArgumentException e) {
      assertEquals("Move distance can not be more than 2 cells", e.getMessage());
      thrown = true;
    }
    assertEquals(true, thrown);
  }

  @Test
  public void testGameOver() {
    MarbleSolitaireModel game = new TriangleSolitaireModelImpl();
    assertEquals(false, game.isGameOver());

    game.move(2, 0, 0, 0);
    game.move(4, 0, 2, 0);
    game.move(4, 2, 4, 0);
    game.move(4, 4, 4, 2);
    game.move(2, 2, 4, 4);
    game.move(3, 1, 3, 3);
    game.move(4, 4, 2, 2);
    game.move(1, 1, 3, 3);
    game.move(2, 0, 2, 2);

    /*
    Now only one legal move left and 10 marbles left
     */
    assertEquals(false, game.isGameOver());
    assertEquals(5, game.getScore());

    game.move(2, 2, 4, 4);
    /*
    Now 0 legal moves left and 9 marbles left (GAME OVER)
     */
    String gameOverState =
            "    O\n" +
                    "   _ _\n" +
                    "  _ _ _\n" +
                    " _ _ _ _\n" +
                    "O _ O _ O";
    assertEquals(true, game.isGameOver());
    assertEquals(4, game.getScore());
    assertEquals(gameOverState, game.getGameState());
  }


}


