package controller;

import org.junit.Test;


import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;


public class TestController {

  /**
   * Simulates controller behaviour with prescribed interactions and model.
   *
   * @param interactions list of interactions
   * @param model        Marble Solitaire model
   * @return String[2] exctedAndActual where at [0] is expected and at [2] - actual output.
   */
  private static String[] interactionsTester(Interaction[] interactions,
                                             MarbleSolitaireModel model) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();
    String[] expectedAndActual = new String[2];

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(input, actualOutput);
    controller.playGame(model);
    expectedAndActual[0] = expectedOutput.toString();
    expectedAndActual[1] = actualOutput.toString();

    return expectedAndActual;

  }

  @Test
  /**
   * Test behaviour when constructor gets null as a Readable.
   */
  public void testNullReadable() {
    boolean thrown = false;
    try {
      new MarbleSolitaireControllerImpl(null, new StringBuilder());

    } catch (IllegalArgumentException iae) {
      thrown = true;
    }
    assertEquals(true, thrown);
  }

  @Test
  /**
   * Test behaviour when constructor gets null as an Appendable.
   */
  public void testNullAppendable() {
    boolean thrown = false;
    try {
      new MarbleSolitaireControllerImpl(new StringReader(""), null);
    } catch (IllegalArgumentException iae) {
      thrown = true;
    }
    assertEquals(true, thrown);
  }

  @Test
  /**
   * Test behaviour when constructor gets null as both arguments.
   */
  public void testNullBoth() {
    boolean thrown = false;
    try {
      new MarbleSolitaireControllerImpl(null, null);
    } catch (IllegalArgumentException iae) {
      thrown = true;
    }
    assertEquals(true, thrown);
  }

  @Test
  public void testIllegalModel() {
    boolean thrown = false;
    String message = "";
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(new StringReader(""), new StringBuilder());
    try {

      controller.playGame(null);

    } catch (IllegalArgumentException iae) {
      message = iae.getMessage();
      thrown = true;
    }
    assertEquals("Model can not be null", message);
    assertEquals(true, thrown);

  }

  @Test
  /**
   Test different "quit" command possibilities
   */
  public void testDifferentQuit() {
    /*
    Standard quit: playing with normal commands and then enter command "q"
     */
    Interaction[] interactions = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),
            new InputInteraction("2 4 4 4"),
            new PrintInteraction("    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),
            new InputInteraction("3 2 3 4"),
            new PrintInteraction("    O O O",
                    "    O _ O",
                    "O _ _ O O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 30"),
            new InputInteraction("5 2 3 2"),
            new PrintInteraction("    O O O",
                    "    O _ O",
                    "O O _ O O O O",
                    "O _ O O O O O",
                    "O _ O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 29"),
            new InputInteraction("q"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O _ O",
                    "O O _ O O O O",
                    "O _ O O O O O",
                    "O _ O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 29")
    };
    String expected = interactionsTester(interactions, new MarbleSolitaireModelImpl())[0];
    String actual = interactionsTester(interactions, new MarbleSolitaireModelImpl())[1];

    assertEquals(expected, actual);


    /*
    playing with normal commands and then enter command "Q" in between coordinates
    */
    Interaction[] interactions1 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),
            new InputInteraction("2 4 4 4"),
            new PrintInteraction("    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),
            new InputInteraction("3 2 Q 5"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),


    };

    String expected1 = interactionsTester(interactions1, new MarbleSolitaireModelImpl())[0];
    String actual1 = interactionsTester(interactions1, new MarbleSolitaireModelImpl())[1];

    assertEquals(expected1, actual1);


    /*
    Enter command "Q" right after 4 coordinates
     */
    Interaction[] interactions2 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),
            new InputInteraction("2 4 4 4 Q"),
            new PrintInteraction("    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),


    };

    String expected2 = interactionsTester(interactions2, new MarbleSolitaireModelImpl())[0];
    String actual2 = interactionsTester(interactions2, new MarbleSolitaireModelImpl())[1];

    assertEquals(expected2, actual2);
    /*
    Enter command "Q" as the first command in the game
     */
    Interaction[] interactions3 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),
            new InputInteraction("Q"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),


    };

    String expected3 = interactionsTester(interactions3, new MarbleSolitaireModelImpl())[0];
    String actual3 = interactionsTester(interactions3, new MarbleSolitaireModelImpl())[1];

    assertEquals(expected3, actual3);


  }

  @Test
  /**
   * Test behavior when move coordinates are interrupted by an invalid command.
   */
  public void testWaitForNumber() {
    Interaction[] interactions = {new PrintInteraction("    O O O",
            "    O O O",
            "O O O O O O O",
            "O O O _ O O O",
            "O O O O O O O",
            "    O O O",
            "    O O O",
            "Score: 32"),
            new InputInteraction("2 4 4 ccvb"),
            new PrintInteraction("Please enter positive number for move coordinate"),
            new InputInteraction("4"),
            new PrintInteraction("    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),
            new InputInteraction("q"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31")};

    String expected = interactionsTester(interactions, new MarbleSolitaireModelImpl())[0];
    String actual = interactionsTester(interactions, new MarbleSolitaireModelImpl())[1];

    assertEquals(expected, actual);

  }

  @Test
  /**
   * Test behaviour when controller runs out of input.
   */
  public void testRanOutOfInput() {
    Interaction[] interactions1 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),
            new InputInteraction("2 4 4 4"),
            new PrintInteraction("    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),
    };

    boolean thrown = false;

    try {
      interactionsTester(interactions1, new MarbleSolitaireModelImpl());
    } catch (IllegalStateException ise) {
      thrown = true;
    }

    assertEquals(true, thrown);

  }

  @Test
  /**
   Test behavior when move coordinates are interrupted
   */
  public void testGameOver() {
    MarbleSolitaireModel gameOver = new MarbleSolitaireModelImpl();

    gameOver.move(1, 3, 3, 3);
    gameOver.move(2, 1, 2, 3);
    gameOver.move(4, 1, 2, 1);
    gameOver.move(4, 2, 2, 2);
    gameOver.move(6, 2, 4, 2);
    gameOver.move(5, 4, 5, 2);
    gameOver.move(6, 4, 6, 2);
    gameOver.move(3, 4, 5, 4);
    gameOver.move(3, 3, 1, 3);
    gameOver.move(2, 5, 2, 3);
    gameOver.move(3, 6, 3, 4);
    gameOver.move(4, 6, 4, 4);
    gameOver.move(4, 4, 6, 4);
    gameOver.move(5, 2, 3, 2);
    gameOver.move(0, 4, 2, 4);
    gameOver.move(0, 2, 0, 4);
    gameOver.move(2, 3, 0, 3);
    gameOver.move(2, 2, 0, 2);
    gameOver.move(2, 4, 4, 4);
    gameOver.move(4, 3, 4, 5);
    gameOver.move(2, 0, 2, 2);
    gameOver.move(2, 2, 4, 2);

    Interaction[] interactions1 = {
            new InputInteraction("5 1 3 1"),
            new PrintInteraction("Game over!",
                    "    O O O",
                    "    _ _ _",
                    "O _ _ _ _ _ O",
                    "_ _ _ _ _ _ _",
                    "_ _ O _ _ O _",
                    "    _ _ _",
                    "    O _ O",
                    "Score: 9")};

    String expected = interactionsTester(interactions1, gameOver)[0];
    String actual = interactionsTester(interactions1, gameOver)[1];

    assertEquals(expected, actual);


  }

  @Test
  /**
   Test specific error messages when move happened to be illegal.
   */
  public void testIllegalMoves() {
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    /*
    Go FROM cell that does not exist (out of bound)
     */
    Interaction[] interactions = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),

            new InputInteraction("100 5 4 4"),
            new PrintInteraction("Invalid move. Play again. Cell (100,5) does not exist"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32")

    };
    String expected = interactionsTester(interactions, model)[0];
    String actual = interactionsTester(interactions, model)[1];

    assertEquals(expected, actual);

    /*
    Go TO cell that does not exist (out of bound)
     */
    Interaction[] interactions1 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),

            new InputInteraction("4 5 88 99"),
            new PrintInteraction("Invalid move. Play again. Cell (88,99) does not exist"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32")

    };
    String expected1 = interactionsTester(interactions1, model)[0];
    String actual1 = interactionsTester(interactions1, model)[1];

    assertEquals(expected1, actual1);

    /*
    Go FROM cell that is not a part of the game
     */
    Interaction[] interactions2 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),

            new InputInteraction("1 1 2 3"),
            new PrintInteraction("Invalid move. Play again. " +
                    "Cell (1,1) is not a part of the board"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32")

    };
    String expected2 = interactionsTester(interactions2, model)[0];
    String actual2 = interactionsTester(interactions2, model)[1];

    assertEquals(expected2, actual2);

    /*
    Go TO cell that is not a part of the game
     */
    Interaction[] interactions3 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),

            new InputInteraction("5 4 7 7"),
            new PrintInteraction("Invalid move. Play again. " +
                    "Cell (7,7) is not a part of the board"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32")

    };

    String expected3 = interactionsTester(interactions3, model)[0];
    String actual3 = interactionsTester(interactions3, model)[1];

    assertEquals(expected3, actual3);

    /*
    Move FROM cell that is empty
     */
    Interaction[] interactions4 = {
            new PrintInteraction(
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),

            new InputInteraction("4 4 6 4"),
            new PrintInteraction("Invalid move. Play again. " +
                    "You can not move from (4,4) because it is empty"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32")

    };
    String expected4 = interactionsTester(interactions4, model)[0];
    String actual4 = interactionsTester(interactions4, model)[1];

    assertEquals(expected4, actual4);

    /*
    Move is SHORTER than 2 cells
     */
    Interaction[] interactions5 = {new PrintInteraction(
            "    O O O",
            "    O O O",
            "O O O O O O O",
            "O O O _ O O O",
            "O O O O O O O",
            "    O O O",
            "    O O O",
            "Score: 32"),

            new InputInteraction("3 4 4 4"),
            new PrintInteraction("Invalid move. Play again. " +
                    "Move distance can not be less than 2 cells"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32")

    };
    String expected5 = interactionsTester(interactions5, model)[0];
    String actual5 = interactionsTester(interactions5, model)[1];

    assertEquals(expected5, actual5);

    /*
    Move is LONGER than 2 cells
     */
    Interaction[] interactions6 = {
            new PrintInteraction(
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),

            new InputInteraction("1 4 4 4"),
            new PrintInteraction("Invalid move. Play again. " +
                    "Move distance can not be more than 2 cells"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32")

    };
    String expected6 = interactionsTester(interactions6, model)[0];
    String actual6 = interactionsTester(interactions6, model)[1];

    assertEquals(expected6, actual6);

    /*
    Move to an occupied cell
     */
    Interaction[] interactions7 = {
            new PrintInteraction(
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),

            new InputInteraction("1 4 3 4"),
            new PrintInteraction("Invalid move. Play again. " +
                    "You can not move to (3,4) because there is marble"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32")

    };
    String expected7 = interactionsTester(interactions7, model)[0];
    String actual7 = interactionsTester(interactions7, model)[1];

    assertEquals(expected7, actual7);

    /*
    Move over an empty cell
     */
    Interaction[] interactions8 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),
            new InputInteraction("2 4 4 4"),
            new PrintInteraction(
                    "    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),
            new InputInteraction("1 4 3 4"),
            new PrintInteraction("Invalid move. Play again. " +
                    "You can move only over a marble"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31")

    };
    String expected8 = interactionsTester(interactions8, new MarbleSolitaireModelImpl())[0];
    String actual8 = interactionsTester(interactions8, new MarbleSolitaireModelImpl())[1];

    assertEquals(expected8, actual8);

    /*
    Diagonal move
     */
    Interaction[] interactions9 = {
            new PrintInteraction("    O O O",
                    "    O O O",
                    "O O O O O O O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 32"),
            new InputInteraction("2 4 4 4"),
            new PrintInteraction(
                    "    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31"),
            new InputInteraction("1 3 3 4"),
            new PrintInteraction("Invalid move. Play again. " +
                    "Move can't be diagonal"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O",
                    "    O _ O",
                    "O O O _ O O O",
                    "O O O O O O O",
                    "O O O O O O O",
                    "    O O O",
                    "    O O O",
                    "Score: 31")

    };
    String expected9 = interactionsTester(interactions9, new MarbleSolitaireModelImpl())[0];
    String actual9 = interactionsTester(interactions9, new MarbleSolitaireModelImpl())[1];

    assertEquals(expected9, actual9);


  }

  @Test
  /**
   * Test normal interactions with triangular model
   */
  public void testWithTriangular() {
    /*
    Test legal moves and quit
     */
    Interaction[] interactions = {
            new PrintInteraction("    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
                    "Score: 14"),
            new InputInteraction("3 1 1 1"),
            new PrintInteraction("    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O",
                    "Score: 13"),
            new InputInteraction("100 5 2 2"),
            new PrintInteraction("Invalid move. Play again. Cell (100,5) does not exist"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O\n" +
                            "   _ O\n" +
                            "  _ O O\n" +
                            " O O O O\n" +
                            "O O O O O",
                    "Score: 13")

    };
    String expected = interactionsTester(interactions, new TriangleSolitaireModelImpl())[0];
    String actual = interactionsTester(interactions, new TriangleSolitaireModelImpl())[1];

    assertEquals(expected, actual);

    /*
    Test wait for a number
     */
    Interaction[] interactions1 = {
            new PrintInteraction("    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
                    "Score: 14"),
            new InputInteraction("3 1 1 ggg"),
            new PrintInteraction("Please enter positive number for move coordinate"),
            new InputInteraction("1 q"),
            new PrintInteraction("    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O\n" +
                    "Score: 13"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O\n" +
                    "Score: 13")
    };
    String expected1 = interactionsTester(interactions1, new TriangleSolitaireModelImpl())[0];
    String actual1 = interactionsTester(interactions1, new TriangleSolitaireModelImpl())[1];

    assertEquals(expected1, actual1);


  }

  @Test
  /**
   * Test normal interactions with european model
   */
  public void testWithEuropean() {
    /*
    Test legal move, then illegal move, then quit
     */
    Interaction[] interactions = {
            new PrintInteraction("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
                    "Score: 36"),
            new InputInteraction("4 2 4 4"),
            new PrintInteraction("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
                    "Score: 35"),
            new InputInteraction("100 5 2 2"),
            new PrintInteraction("Invalid move. Play again. Cell (100,5) does not exist"),
            new InputInteraction("quit"),
            new PrintInteraction("Game quit!",
                    "State of game when quit:",
                    "    O O O\n" +
                            "  O O O O O\n" +
                            "O O O O O O O\n" +
                            "O _ _ O O O O\n" +
                            "O O O O O O O\n" +
                            "  O O O O O\n" +
                            "    O O O",
                    "Score: 35")

    };
    String expected = interactionsTester(interactions, new EuropeanSolitaireModelImpl())[0];
    String actual = interactionsTester(interactions, new EuropeanSolitaireModelImpl())[1];

    assertEquals(expected, actual);

    /*
    Test wait for a number
     */
    Interaction[] interactions1 = {
            new PrintInteraction("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
                    "Score: 36"),
            new InputInteraction("4 gdf 2 4"),
            new PrintInteraction("Please enter positive number for move coordinate"),
            new InputInteraction("4 q"),
            new PrintInteraction("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
                    "Score: 35"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
                    "Score: 35")
    };

    String expected1 = interactionsTester(interactions1, new EuropeanSolitaireModelImpl())[0];
    String actual1 = interactionsTester(interactions1, new EuropeanSolitaireModelImpl())[1];

    assertEquals(expected1, actual1);
  }
}




