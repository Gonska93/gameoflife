package gameoflife;

import gameoflife.model.DisplayConfig;
import gameoflife.view.MenuFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
         *Game container which got menu panel on the left side and cell table on the right
         */
        MenuFX menu = new MenuFX("Game of Life", primaryStage);

        Scene mainScene = new Scene(menu, DisplayConfig.SCREEN_WIDTH, DisplayConfig.SCREEN_HEIGHT);
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
