
/**
 * Copyright 2016 SciFY.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scify.memorimusicgame.card;

import javafx.scene.control.Button;
import org.scify.memorimusicgame.MainOptions;
import org.scify.memorimusicgame.interfaces.Tile;
import org.scify.memorimusicgame.utils.ResourceLocator;

/**
 * Implements the {@link Tile} representation in the game.
 * The user can move on tiles and flip them
 */
public class Card implements Tile{

    /**
     * the button element that binds the card with the UI layout
     */
    private Button button;
    /**
     * the type of the card. Cards with the same type are considered identical
     */
    private String label;
    /**
     * file name of the image associated with the card
     */
    private String[] images;
    /**
     * whether the Card has been flipped
     */
    private boolean isFlipped;
    /**
     * whether the Card has been won
     */
    private boolean isWon;
    /**
     * file name of the sound associated with the card
     */
    private String[] sounds;
    /**
     * file name of the card description sound
     */
    private String cardNameSound;
    /**
     * file name of the card name sound
     */
    private String[] descriptiveSounds;

    protected static ResourceLocator resourceLocator = new ResourceLocator();
    /**
     *
     * @return the Node (Button) that is laid on the layout
     */
    public Button getButton() {
        return button;
    }

    /**
     * Checks if the card is won
     * @return true if the card is won
     */
    @Override
    public boolean getWon() {
        return isWon;
    }

    /**
     * sets a card as won
     */
    @Override
    public void setWon() {
        isWon = true;
    }

    /**
     * Checks if the card is flipped
     * @return true if the card is flipped
     */
    @Override
    public boolean getFlipped() {
        return isFlipped;
    }

    public Card(String label, String[] images, String[] sounds, String[] descriptiveSounds, String cardNameSound) {
        this.images = images;
        this.button = new Button();
        this.sounds = sounds;
        this.descriptiveSounds = descriptiveSounds;
        this.cardNameSound = cardNameSound;
        this.button.setId(label);
        // each card takes a dynamic height and width, based on the height and with of the screen
        double width = MainOptions.mWidth/MainOptions.NUMBER_OF_COLUMNS - ((MainOptions.mWidth/MainOptions.NUMBER_OF_COLUMNS) * 0.1);
        this.button.setPrefHeight(width * 0.66);
        this.button.setPrefWidth(width);
        // apply the appropriate style classes
        this.button.getStyleClass().addAll("cardButton", "closedCard");
        this.label = label;
        this.isWon = false;
        this.isFlipped = false;
        flipBackUI();
    }

    public String getLabel() {
        return label;
    }


    @Override
    public void flip() {
        isFlipped = !isFlipped;
    }

    /**
     * function to set the UI of the flipped card (change icons)
     * @param imgIndex the index of the image
     */
    public void flipUI(int imgIndex) {
        // only if this image exists
        if(imgIndex < images.length) {
            String imgFile = resourceLocator.getCorrectPathForFile("img/", images[imgIndex]);
            button.setStyle("-fx-background-image: url(" + imgFile + ")");
        }
    }


    /**
     * function to set the UI of the flipped back card (change icons)
     */
    public void flipBackUI () {
        //TODO: change logic here (game options should include door card for each game)
        String imgFile = "door.jpg";
        if(MainOptions.gameClassName.equals("FIND_INSTRUMENT_FAMILY")) {
            imgFile = "cardcategorie.png";
        } else if(MainOptions.gameClassName.equals("FIND_INSTRUMENT")) {
            imgFile = "cardinstrument.png";
        } else if(MainOptions.gameClassName.equals("FIND_NOTE")) {
            imgFile = "cardnote.png";
        }
        String imgFilePath = resourceLocator.getCorrectPathForFile("img/", imgFile);
        button.setStyle("-fx-background-image: url(" + imgFilePath + ")");
        button.setStyle("-fx-background-image: url(" + imgFilePath + ")");
    }

    /**
     * Get a random sound from the card sounds
     * @return the sound file name associated with the card
     */
    public String getRandomSound() {
        if(sounds.length != 0)
            return sounds[random_int(0, sounds.length)];
        return null;
    }

    public String getDescriptionSound() {
        if(descriptiveSounds.length != 0)
            return descriptiveSounds[random_int(0, descriptiveSounds.length)];
        return null;
    }

    private int random_int(int Min, int Max) {
        return (int) (Math.random()*(Max-Min))+Min;
    }
}
