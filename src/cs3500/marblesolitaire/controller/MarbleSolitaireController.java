package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;


/**
 * Represents all needed operation to operate with MarbleSolitaireModel.
 */
public interface MarbleSolitaireController {


  /**
   * Plays a game by taking commands from readable and sending them to the model. Makes necessary
   * changes in the model and transmits results into the appendable (output)
   *
   * @param model Implementation of MarbleSolitaireModel
   * @throws IllegalArgumentException if provided model is null.
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException;

}
