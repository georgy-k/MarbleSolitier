package model;

import org.junit.Test;


import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;


import static org.junit.Assert.assertEquals;


public class TestEuropean {

  @Test
  public void testIllegalArgs() {


    try {
      EuropeanSolitaireModelImpl game = new EuropeanSolitaireModelImpl(2);
    } catch (IllegalArgumentException e) {
      assertEquals("The arm thickness is not a positive odd number",
              e.getMessage());
    }

    try {
      EuropeanSolitaireModelImpl game1 = new EuropeanSolitaireModelImpl(0);
    } catch (IllegalArgumentException e) {
      assertEquals("The arm thickness is not a positive odd number",
              e.getMessage());
    }

    try {
      EuropeanSolitaireModelImpl game1 = new EuropeanSolitaireModelImpl(-1);
    } catch (IllegalArgumentException e) {
      assertEquals("The arm thickness is not a positive odd number",
              e.getMessage());
    }

    try {
      EuropeanSolitaireModelImpl game2 =
              new EuropeanSolitaireModelImpl(5, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,0)",
              e.getMessage());
    }

    try {
      EuropeanSolitaireModelImpl game3 = new EuropeanSolitaireModelImpl(1, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,1)",
              e.getMessage());
    }

    try {
      EuropeanSolitaireModelImpl game4 = new EuropeanSolitaireModelImpl(5, 8);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,8)",
              e.getMessage());
    }
    try {
      EuropeanSolitaireModelImpl game5 = new EuropeanSolitaireModelImpl(105, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (105,5)",
              e.getMessage());
    }
  }


  @Test
  public void testDefaultInitializeBoard() {
    EuropeanSolitaireModelImpl game = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", game.getGameState());


  }

  @Test
  public void testLegalCustomEmptyCell() {
    EuropeanSolitaireModelImpl game = new EuropeanSolitaireModelImpl(4, 5);
    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O _ O\n" +
                    "  O O O O O\n" +
                    "    O O O", game.getGameState());

  }


  @Test
  public void testLegalCustomArmSize() {
    EuropeanSolitaireModelImpl game = new EuropeanSolitaireModelImpl(5);
    assertEquals(
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O", game.getGameState());

  }

  @Test
  public void testEverythingCustom() {
    EuropeanSolitaireModelImpl game = new EuropeanSolitaireModelImpl(5, 4, 3);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", game.getGameState());

  }

  @Test
  public void testGetGameState() {
    EuropeanSolitaireModelImpl game1 = new EuropeanSolitaireModelImpl();
    String board = game1.getGameState();
    String expected =
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";

    assertEquals(expected, board);
    EuropeanSolitaireModelImpl game2 = new EuropeanSolitaireModelImpl(5);
    String board2 = game2.getGameState();
    String expected2 =
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O";
    assertEquals(expected2, board2);

    EuropeanSolitaireModelImpl game3 = new EuropeanSolitaireModelImpl(1);
    String board3 = game3.getGameState();
    String expected3 = "_";
    assertEquals(expected3, board3);

  }


  @Test
  public void testMakeMove() {
    EuropeanSolitaireModelImpl game1 = new EuropeanSolitaireModelImpl();

    /*
    Make move from (1,3) to (3,3) (MOVE DOWN)
     */
    game1.move(1, 3, 3, 3);
    String expected1 =
            "    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";
    assertEquals(expected1, game1.getGameState());


    /*
    Make move from (2,1) to (2,3) (MOVE RIGHT)
     */
    game1.move(2, 1, 2, 3);
    String expected2 =
            "    O O O\n" +
                    "  O O _ O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";
    assertEquals(expected2, game1.getGameState());

    /*
    Make move from (2,4) to (2,2) (MOVE LEFT)
     */
    game1.move(2, 4, 2, 2);
    String expected3 =
            "    O O O\n" +
                    "  O O _ O O\n" +
                    "O _ O _ _ O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";
    assertEquals(expected3, game1.getGameState());

    /*
    Make move from (4,1) to (2,1) (MOVE UP)
     */
    game1.move(4, 1, 2, 1);
    String expected4 =
            "    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O _ _ O O\n" +
                    "O _ O O O O O\n" +
                    "O _ O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";
    assertEquals(expected4, game1.getGameState());

    EuropeanSolitaireModelImpl game2 = new EuropeanSolitaireModelImpl(4, 3);
    game2.move(4, 1, 4, 3);
    String expected5 =
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O";
    assertEquals(expected5, game2.getGameState());

  }

  @Test
  public void testGetScore() {
    EuropeanSolitaireModelImpl game = new EuropeanSolitaireModelImpl();
    assertEquals(36, game.getScore());
    game.move(1, 3, 3, 3);
    assertEquals(35, game.getScore());
    game.move(2, 1, 2, 3);
    assertEquals(34, game.getScore());
    game.move(2, 4, 2, 2);
    assertEquals(33, game.getScore());
    game.move(4, 1, 2, 1);
    assertEquals(32, game.getScore());

  }

  @Test
  public void testIllegalMoves() {

    EuropeanSolitaireModelImpl game = new EuropeanSolitaireModelImpl();

    /*
    Move from invalid game field
     */
    try {
      game.move(0, 0, 0, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Cell (1,1) is not a part of the board", e.getMessage());
    }

    /*
    Move to invalid game field
     */
    try {
      game.move(2, 1, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Cell (1,1) is not a part of the board", e.getMessage());
    }

    /*
    Move to a field that contains ball
     */
    try {
      game.move(2, 1, 4, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("You can not move to (5,2) because there is marble", e.getMessage());
    }
    /*
    Move to a field that does not exist
     */
    try {
      game.move(2, 1, 100, 500);
    } catch (IllegalArgumentException e) {
      assertEquals("Cell (101,501) does not exist", e.getMessage());
    }
    /*
    Move from a field that does not exist
     */
    try {
      game.move(13, 1, 1, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Cell (14,2) does not exist", e.getMessage());
    }
    /*
    From and to cells are the same cell
     */
    try {
      game.move(4, 4, 4, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Move distance can not be less than 2 cells", e.getMessage());
    }

    /*
    Diagonal
     */
    boolean thrown = false;
    try {
      game.move(1, 1, 3, 3);
    } catch (IllegalArgumentException e) {
      thrown = true;
      assertEquals("Move can't be diagonal", e.getMessage());
    }
    assertEquals(true, thrown);
    /*
    No marble in between
     */
    try {
      game.move(1, 3, 3, 3);
      game.move(0, 3, 2, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("You can move only over a marble", e.getMessage());
    }
  }

  @Test
  public void testGameOver() {
    EuropeanSolitaireModelImpl game = new EuropeanSolitaireModelImpl(0,2);
    assertEquals(false, game.isGameOver());
    EuropeanSolitaireModelImpl game2 = new EuropeanSolitaireModelImpl(4, 5);
    assertEquals(false, game2.isGameOver());

    game.move(2, 2, 0, 2);
    game.move(2, 0, 2, 2);
    game.move(4, 0, 2, 0);
    game.move(4, 1, 2, 1);
    game.move(1, 1, 3, 1);
    game.move(2, 3, 2, 1);
    game.move(3, 1, 1, 1);
    game.move(1, 4, 1, 2);
    game.move(0, 2, 2, 2);
    game.move(3, 2, 1, 2);
    game.move(0, 4, 0, 2);
    game.move(0, 2, 2, 2);
    game.move(5, 2, 3, 2);
    game.move(2, 2, 4, 2);
    game.move(4, 3, 4, 1);
    game.move(5, 1, 3, 1);
    game.move(5, 4, 5, 2);
    game.move(6, 2, 4, 2);
    game.move(6, 4, 6, 2);
    game.move(3, 4, 1, 4);
    game.move(1, 5, 1, 3);
    game.move(3, 5, 1, 5);
    game.move(4, 5, 4, 3);
    game.move(4, 3, 2, 3);
    /*
    Now only one legal move left and 10 marbles left
     */
    assertEquals(false, game.isGameOver());
    assertEquals(12, game.getScore());

    game.move(2, 3, 0, 3);
    /*
    Now 0 legal moves left and 9 marbles left (GAME OVER)
     */
    String gameOverState =
            "    _ O _\n" +
                    "  O _ _ _ O\n" +
                    "O _ _ _ _ _ O\n" +
                    "_ O _ _ _ _ O\n" +
                    "_ _ O _ _ _ O\n" +
                    "  _ _ _ _ O\n" +
                    "    O _ _";
    assertEquals(true, game.isGameOver());
    assertEquals(11, game.getScore());
    assertEquals(gameOverState, game.getGameState());
  }


}
