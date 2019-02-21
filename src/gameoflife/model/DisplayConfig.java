package gameoflife.model;

import javafx.scene.paint.Color;
import java.awt.*;

public class DisplayConfig {
    public static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static final double GAME_BOARD_WIDTH = SCREEN_WIDTH*0.8;
    public static final double GAME_BOARD_HEIGHT = SCREEN_HEIGHT*0.8;
    public static final double MENU_PANEL_WIDTH = SCREEN_WIDTH*0.15;
    public static final double MENU_PANEL_HEIGHT = SCREEN_HEIGHT*0.9;
    public static final Color CELL_COLOR_ALIVE = Color.BLACK;
    public static final Color CELL_COLOR_DEAD = Color.WHITE;
    public static final Color CELL_ALIVE_COLOR_ENTERED = Color.GRAY;
    public static final Color CELL_DEAD_COLOR_ENTERED = Color.GREEN;
    public static double CELL_WIDTH;
    public static double CELL_HEIGHT;
}
