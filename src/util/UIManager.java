package util;

import objects.FruitUI;
import processing.core.PApplet;
import processing.core.PImage;
import util.scores.ScoreManager;
import util.texts.SplashTextLoader;

public class UIManager {
    private int starfruitCollected;
    private int bananaExplosionTimer;
    private int splashNumber;
    public boolean bananaExploded;
    public FruitType lastFruitCollected;
    private final FruitUI[] fruitsOnScreen;
    private final PImage menuScreen;
    private final PImage gameOverScreen;
    private final PImage bananaExplosion;
    private final PImage gameScreenBackground;
    private final PImage gameScreenForeground;
    private final PImage rulesScreen;
    private final Pair<Float, Float> bananaExplosionCoords;

    private final PApplet sketch;
    public final SplashTextLoader splash;
    public final ScoreManager scores;

    public UIManager(PApplet sketch) {
        this.bananaExploded = false;
        this.splash = new SplashTextLoader();
        this.scores = new ScoreManager();
        this.scores.openScoresFile();
        this.scores.readScores();
        this.sketch = sketch;
        this.bananaExplosionTimer = 300;
        this.bananaExplosionCoords = new Pair<>(0.0f, 0.0f);
        this.menuScreen = this.sketch.loadImage("assets/MainMenuForeground.png");
        this.gameOverScreen = this.sketch.loadImage("assets/GameOverScreen.png");
        this.bananaExplosion = this.sketch.loadImage("assets/bananaExplosion.png");
        this.gameScreenBackground = this.sketch.loadImage("assets/GameScreenBackground.png");
        this.gameScreenForeground = this.sketch.loadImage("assets/GameScreenForeground.png");
        this.rulesScreen = this.sketch.loadImage("assets/RulesScreen.png");
        this.fruitsOnScreen = new FruitUI[4];
        this.starfruitCollected = 0;
    }

    public void showMainMenu() {
        int lineLength = this.splash.splashes[this.splashNumber].length();
        this.sketch.image(this.menuScreen, 0, 0);
        this.sketch.textSize(40 - lineLength/2);
        this.sketch.pushMatrix();
        this.sketch.rotate(PApplet.radians(20 - lineLength/2));
        this.sketch.fill(0, 0, 0, 50);
        this.sketch.text(this.splash.splashes[this.splashNumber], 210, 190 + lineLength);
        this.sketch.fill(255, 255, 78);
        this.sketch.text(this.splash.splashes[this.splashNumber], 200, 180 + lineLength);
        this.sketch.popMatrix();
    }

    public void showRulesScreen() {
        this.sketch.image(this.rulesScreen, 0, 0);
    }

    public void showGameOverScreen(int score) {
        String[] scoreLines  = this.scores.readScores();
        this.sketch.image(this.gameOverScreen, 0, 0);
        this.sketch.fill(255, 40, 92);
        this.sketch.textAlign(this.sketch.CENTER);
        this.sketch.textSize(60);
        this.sketch.text(score, 240, 283);
        this.sketch.textAlign(this.sketch.LEFT);
        this.sketch.textSize(30);
        for (int i = 0; i < scoreLines.length; i++) {
            this.sketch.text(scoreLines[i], 400, 380 + 30*i);
        }
    }

    public void showGameScreenBackground() {
        this.sketch.image(this.gameScreenBackground, 0, 0);
    }

    public void showGameScreenForeground(int gameScore) {
        this.sketch.image(gameScreenForeground, 0, 0);
        this.sketch.textSize(30);
        this.sketch.fill(189, 229, 80);
        this.sketch.text(gameScore, 100, 230);
    }

    public boolean collectedEnoughStarFruits() {
        if (this.starfruitCollected == 3) {
            this.starfruitCollected = 0;
            return true;
        } else {
            this.starfruitCollected++;
        }
        return false;
    }

    public void showBananaExplosion() {
        if (this.bananaExploded && this.bananaExplosionTimer != 0) {
            this.sketch.image(this.bananaExplosion, this.bananaExplosionCoords.getX(), this.bananaExplosionCoords.getY());
            this.bananaExplosionTimer--;
        } else {
            this.bananaExploded = false;
            this.bananaExplosionTimer = 300;
        }
        }

    public void showCollectedFruits() {
        for (int i = 0; i < this.starfruitCollected; i++) {
            this.fruitsOnScreen[i] = new FruitUI(this.sketch, 54 + 33 * i, 267, FruitType.STARFRUIT);
            this.fruitsOnScreen[i].drawSprite();
        }
        if (this.lastFruitCollected != null) {
            this.fruitsOnScreen[3] = new FruitUI(this.sketch, 82, 133, this.lastFruitCollected);
            this.fruitsOnScreen[3].drawSprite();
        }
    }

    public boolean mousePos(int mousePos, int valMin, int valMax) {
        return valMin < mousePos && mousePos < valMax;
    }

    public void getSnakePosition(Float x, Float y) {
    this.bananaExplosionCoords.setX(x);
    this.bananaExplosionCoords.setY(y);
    }

    public void changeSplashNumber() {
        this.splashNumber = (int) this.sketch.random(0, this.splash.splashes.length);
    }

}
