package cs3500.marblesolitaire;

import java.io.InputStreamReader;


import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;


/**
 * Class represents starter of the game.
 */
public final class MarbleSolitaire {
  static Builder builder;

  /**
   * Creates MarbleSolitaireModel and plays the Marble Solitaire game.
   *
   * @param args commands in a form if String[]
   */
  public static void main(String[] args) {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), System.out);
    String type = "";

    switch (args[0]) {
      case "english":
      case "european":
      case "triangular":
        type = args[0];
        builder = new Builder(type);
        controller.playGame(buildModel(args));
        break;
      default:
        throw new IllegalArgumentException("Command must begin with one of: "
                + "\nenglish" + "\neuropean" + "\ntriangular\n");
    }
  }


  /**
   * Checks passed in commands and builds an appropriate game model.
   *
   * @param args commands
   * @return Marble Solitaire Model
   */
  static MarbleSolitaireModel buildModel(String[] args) {
    int size = -1;
    int emptyRow = -1;
    int emptyCol = -1;

    try {
      if (args.length == 1) {
        return builder.build();
      }
      if (args.length == 3 && args[1].equals("-size")) {
        size = Integer.parseInt(args[2]);
        builder.setSize(size);
      } else if (args.length == 4 && args[1].equals("-hole")) {
        emptyRow = Integer.parseInt(args[2]);
        emptyCol = Integer.parseInt(args[3]);
        builder.setHoles(emptyRow, emptyCol);

      } else if (args.length == 6 && args[1].equals("-size") && args[3].equals("-hole")) {
        size = Integer.parseInt(args[2]);
        builder.setSize(size);

        emptyRow = Integer.parseInt(args[4]);
        emptyCol = Integer.parseInt(args[5]);
        builder.setHoles(emptyRow, emptyCol);

      } else {
        throw new IllegalArgumentException("Wrong command");
      }
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException("Expected an integer but got a string");
    }
    return builder.build();
  }


  /**
   * Represents a marble solitaire builder class. Contains f
   */
  final static class Builder {

    private String type;
    private int size;
    private int emptyRow;
    private int emptyCol;

    /**
     * Initializes default board parameters based on the given type.
     *
     * @param type type of the board: english, european, triangular
     */
    public Builder(String type) {
      this.type = type;

      switch (type) {
        case "english":
        case "european":
          size = 3;
          emptyRow = 3;
          emptyCol = 3;
          break;
        case "triangular":
          size = 5;
          emptyRow = 0;
          emptyCol = 0;
          break;
        default:
          throw new IllegalArgumentException("Command must begin with one of: "
                  + "\nenglish" + "\neuropean" + "\ntriangular\n");
      }
    }


    /**
     * Sets the size of the board to be created. Also recalculates the default empty spot if type is
     * english or european.
     *
     * @param size size of the arm (english, european) or dimension (triangle)
     */
    void setSize(int size) {
      switch (type) {
        case "english":
        case "european":
          this.size = size;
          this.emptyRow = (size * 3 - 2) / 2;
          this.emptyCol = (size * 3 - 2) / 2;
          break;
        default:
          this.size = size;
      }
    }

    /**
     * Sets the row and col place of the empty cell.
     *
     * @param holeRow row position of the empty space
     * @param holeCol col position of the empty space
     */
    void setHoles(int holeRow, int holeCol) {
      this.emptyRow = holeRow;
      this.emptyCol = holeCol;
    }

    /**
     * Builds the desired Marble Solitaire model. Uses previously assigned parameters (size,
     * emptyRow, emptyCol
     *
     * @return required type of MarbleSolitaireModel
     */
    MarbleSolitaireModel build() {
      switch (type) {
        case "english":
          return new MarbleSolitaireModelImpl(size, emptyRow, emptyCol);
        case "european":
          return new EuropeanSolitaireModelImpl(size, emptyRow, emptyCol);
        case "triangular":
          return new TriangleSolitaireModelImpl(size, emptyRow, emptyCol);
        default:
          return new MarbleSolitaireModelImpl(size, emptyRow, emptyCol);

      }
    }
  }
}



