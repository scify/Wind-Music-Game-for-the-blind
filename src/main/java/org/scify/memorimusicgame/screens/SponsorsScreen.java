
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

package org.scify.memorimusicgame.screens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scify.memorimusicgame.fx.FXAudioEngine;
import org.scify.memorimusicgame.fx.FXRenderingEngine;
import org.scify.memorimusicgame.fx.FXSceneHandler;
import org.scify.memorimusicgame.helper.MemoriConfiguration;
import org.scify.memorimusicgame.helper.UTF8Control;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;

import static org.scify.memorimusicgame.MainOptions.mHeight;
import static org.scify.memorimusicgame.MainOptions.mWidth;

/**
 * JavaFX Screen constructor page
 */
public class SponsorsScreen{

    protected FXSceneHandler sceneHandler;
    Timer timer = new Timer();
    FXAudioEngine audioEngine = new FXAudioEngine();
    public SponsorsScreen(FXSceneHandler shSceneHandler, Stage mainWindow) {
        this.sceneHandler = shSceneHandler;
        sceneHandler.setMainWindow(mainWindow);
        MemoriConfiguration configuration = new MemoriConfiguration();
        Locale locale = new Locale(configuration.getProjectProperty("APP_LANG"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sponsors.fxml"),
                ResourceBundle.getBundle("languages.strings", locale, new UTF8Control()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        audioEngine.pauseAndPlaySound("screens/sponsor_screen/sponsor_message.mp3", false);
        Scene sponsorsScene = new Scene(root, mWidth, mHeight);
        FXRenderingEngine.setSponsorcoverIcon(sponsorsScene, "gameCoverImgContainer");
        sceneHandler.pushScene(sponsorsScene);

        sponsorsScene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    timer.cancel();
                    timer.purge();
                    audioEngine.pauseCurrentlyPlayingAudios();
                    sceneHandler.popScene();
                    break;
            }
        });

        // after 8 seconds, we should return to the previous screen
        timer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        audioEngine.pauseCurrentlyPlayingAudios();
                        sceneHandler.popScene();
                    }
                },
                12000
        );
    }

}
