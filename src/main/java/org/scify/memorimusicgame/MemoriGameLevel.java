package org.scify.memorimusicgame;

import org.scify.memorimusicgame.card.Card;
import org.scify.memorimusicgame.interfaces.GameLevel;

import java.awt.geom.Point2D;

/**
 * Description of a game level for the Memori game
 * Created by pisaris on 18/10/2016.
 */
public class MemoriGameLevel implements GameLevel {

    /**
     * the code associated with each level (1, 2, etc)
     */
    protected int levelCode;
    /**
     * In memor-i, each level is associated with dimesions for the level's terrain
     */
    protected Point2D dimensions;
    /**
     * Each game level has a introductory sound playing upon level initialization
     */
    protected String introSound;
    /**
     * Each game lavel can have a helpe sounds associated with it (plays at the beginning)
     */
    protected String introHelperSound;
    /**
     * Each game level has a discriptive sound playing upon user interaction with the level button
     * (in order for a blind person to know which level is there)
     */
    protected String introScreenSound;

    /**
     * Each game level may have a dedicated JSON file for getting {@link Card}s
     */
    protected String JSONDBFileForLevel;

    public MemoriGameLevel(int levelCode, Point2D dimensions, String introSound, String introScreenSound, String JSONDBFileForLevel) {
        this.levelCode = levelCode;
        this.dimensions = dimensions;
        this.introSound = introSound;
        this.introScreenSound = introScreenSound;
        this.JSONDBFileForLevel = JSONDBFileForLevel;
    }

    public String getIntroHelperSound() {
        return introHelperSound;
    }

    public MemoriGameLevel(int levelCode, Point2D dimensions, String introSound, String introScreenSound, String JSONDBFileForLevel, String introHelperSound) {
        this.levelCode = levelCode;
        this.dimensions = dimensions;
        this.introSound = introSound;
        this.introScreenSound = introScreenSound;
        this.JSONDBFileForLevel = JSONDBFileForLevel;
        this.introHelperSound = introHelperSound;

    }

    public String getJSONDBFileForLevel() {
        return JSONDBFileForLevel;
    }

    public int getLevelCode() {
        return levelCode;
    }

    @Override
    public Point2D getDimensions() {
        return dimensions;

    }

    @Override
    public String getIntroSound() {
        return introSound;
    }

    @Override
    public String getIntroScreenSound() {
        return introScreenSound;
    }
}
