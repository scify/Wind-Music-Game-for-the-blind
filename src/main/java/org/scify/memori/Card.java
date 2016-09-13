package org.scify.memori;

import javafx.scene.control.Button;
import org.scify.memori.MainOptions;
import org.scify.memori.interfaces.Tile;

public class Card implements Tile{

    /**
     * the button element that binds the card with the UI layout
     */
    private Button button;
    /**
     * the type of the card. Cards with the same type are considered identical
     */
    private String tileType;
    /**
     * file name of the image associated with the card
     */
    private String imgName;
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
    private String sound;

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

    public Card(String id, String img, String soundFile) {
        imgName = img;
        button = new Button();
        sound = soundFile;
        button.setId(id);
        // each card takes a dynamic height and width, based on the height and with of the screen
        button.setPrefHeight(MainOptions.mHeight/MainOptions.NUMBER_OF_ROWS - (MainOptions.mHeight/MainOptions.NUMBER_OF_ROWS * 0.05));
        button.setPrefWidth(MainOptions.mWidth/MainOptions.NUMBER_OF_COLUMNS);
        // apply the appropriate style classes
        button.getStyleClass().addAll("cardButton", "closedCard");
        tileType = id;
        isWon = false;
        isFlipped = false;
    }

    @Override
    public String getTileType() {
        return tileType;
    }


    @Override
    public void flip() {
        isFlipped = !isFlipped;
    }

    /**
     * function to set the UI of the flipped card (change icons)
     */
    public void flipUI() {
        String imgFile = "/img/" + imgName;
        button.setStyle("-fx-background-image: url(" + imgFile +")");
    }
    /**
     * function to set the UI of the flipped back card (change icons)
     */
    public void flipBackUI () {
        String imgFile = "/img/door.jpg";
        button.setStyle("-fx-background-image: url(" + imgFile +")");
    }

    /**
     * Get the sound of the card
     * @return the sound file name associated with the card
     */
    public String getSound() {
        return sound;
    }

}
