package controller;

/**
 * Represents input type of Interaction interface.
 */
class InputInteraction implements Interaction {

  String input;

  /**
   * Constructs InputInteraction given user input.
   *
   * @param input input for the controller
   */
  InputInteraction(String input) {
    this.input = input;
  }

  /**
   * Appends user input to the global input (in).
   *
   * @param in  prescribed input
   * @param out output to be written ti
   */
  public void apply(StringBuilder in, StringBuilder out) {
    in.append(input).append("\n");
  }
}