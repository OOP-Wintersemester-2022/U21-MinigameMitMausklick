import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class MiniGame extends GraphicsApp {

    /* Private Konstanten */
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    private ClickBall clickBall;
    private int counter;

    /*
     * Die initialize-Methode wird einmalig zum Start des Programms
     * aufgerufen.
     */

    @Override
    public void initialize() {
        setupCanvas();
        counter = 0;
        clickBall = new ClickBall(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    /*
     * Die draw-Methode wird so lange wiederholt aufgerufen, bis das Programm
     * beendet wird.
     */

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        clickBall.update();
        clickBall.draw();
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
    }

    @Override
    public void onMousePressed(MousePressedEvent mousePressedEvent) {
        if (clickBall.hitTest(mousePressedEvent.getXPos(), mousePressedEvent.getYPos())) {
            clickBall.changeDirection();
            counter++;
            System.out.println("You have " + counter + " points!");
        }
    }

    public void onKeyPressed(KeyPressedEvent keyPressedEvent) {
        if (keyPressedEvent.getKeyCode() == KeyPressedEvent.VK_ESCAPE) {
            clickBall.stop();
            counter = 0;
        }
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}