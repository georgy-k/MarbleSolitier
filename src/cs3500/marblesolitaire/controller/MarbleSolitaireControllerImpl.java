package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a controller needed to play Marble Solitaire game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private Readable input;
  private Appendable output;

  /**
   * Constructs an initial controller with user inputs and output (Appendable) to be written to.
   *
   * @param rd user input in a from of Readable interface
   * @param ap Appendable object where all outputs will be written to
   * @throws IllegalArgumentException if either Readable or Appendable is null
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap)
          throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("null readable or appendable was passed in");
    }
    this.input = rd;
    this.output = ap;
  }

  @Override
  public void playGame(MarbleSolitaireModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    Scanner scanner = new Scanner(input);

    ArrayList<Integer> moveCords = new ArrayList<>();
    boolean madeMove = true;

    try {
      while (!model.isGameOver()) {

        if (madeMove) {
          output.append(model.getGameState() + "\n");
          output.append("Score: " + model.getScore() + "\n");
        }
        while (moveCords.size() < 4 && !model.isGameOver()) {

          if (scanner.hasNext()) {
            String next = scanner.next();
            if (!(next.charAt(0) == 'q' || next.charAt(0) == 'Q')) {
              try {
                moveCords.add(Integer.parseInt(next));
              } catch (NumberFormatException nfe) {
                try {
                  output.append("Please enter positive number for move coordinate" + "\n");
                } catch (IOException iae) {
                  throw new IllegalStateException("Unable to transmit output");
                }
              }
            } else {
              output.append("Game quit!" + "\n");
              output.append("State of game when quit:" + "\n");
              output.append(model.getGameState() + "\n");
              output.append("Score: " + model.getScore() + "\n");
              return;

            }
          } else {
            throw new IllegalStateException("Ran out of inputs");
          }
        }
        try {
          model.move(moveCords.get(0) - 1,
                  moveCords.get(1) - 1, moveCords.get(2) - 1,
                  moveCords.get(3) - 1);
          moveCords = new ArrayList<>();
          madeMove = true;
        } catch (IllegalArgumentException iae) {
          output.append("Invalid move. Play again. " + iae.getMessage() + "\n");
          moveCords = new ArrayList<>();
          madeMove = false;
        }

      }
      output.append("Game over!" + "\n");
      output.append(model.getGameState() + "\n");
      output.append("Score: " + model.getScore() + "\n");
      return;

    } catch (IOException ise) {
      throw new IllegalStateException("Unable to transmit output");
    }
  }
}
