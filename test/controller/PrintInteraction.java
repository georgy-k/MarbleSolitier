package controller;

/**
 * Represents "output" part of Interaction interface.
 */
public class PrintInteraction implements Interaction {

  String[] lines;


  /**
   * Constructs an PrintInteraction given output in a from of String... lines.
   *
   * @param lines output as a lines of String.
   */
  PrintInteraction(String... lines) {
    this.lines = lines;
  }

  /**
   * Appends all lines onto the given output.
   *
   * @param in  outputs received.
   * @param out output to be written to.
   */
  public void apply(StringBuilder in, StringBuilder out) {
    for (String line : lines) {
      out.append(line).append("\n");
    }
  }
}
