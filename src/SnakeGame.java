import objects.*;
import processing.core.PApplet;
import util.Direction;
import util.FruitType;
import util.UIManager;
import util.SnakeConstructor;

public class SnakeGame extends PApplet {
    public UIManager UI;
    public SnakeConstructor Constructor;
    public Fruit[] fruits;
    public Snake[] snake;

    public int gameScore;
    public int points;
    public int gameState;
    public int snakeLength;
    public int snakeColor;
    public int magnetismDuration;
    public int magnetismPower;
    public float snakeHeadX;
    public float snakeHeadY;
    private boolean firstTime;
    private boolean isMagnetic;

    public void settings() {
        this.size(720, 720);
        this.gameState = 0;
        this.snakeHeadX = this.width / 2;
        this.snakeHeadY = this.height / 2;
        this.firstTime = true;
        this.startNewGame();
    }

    public void setup() {
        this.noStroke();
        this.textFont(createFont("fonts/chokoPlain.ttf", 25));
        this.textAlign(CENTER);
    }

    public void draw() {
        switch (this.gameState) {
            case 0 -> {
                this.UI.showMainMenu();
                if (this.mousePressed){
                    if (this.UI.mousePos(this.mouseX, 170, 540)) {
                        if (this.UI.mousePos(this.mouseY, 280, 350)) {
                            if (this.firstTime) {
                                this.gameState = 3;
                            } else {
                                this.startNewGame();
                                this.gameState = 1;
                            }
                        } else if (this.UI.mousePos(this.mouseY, 380, 450)) {
                            this.gameState = 3;
                        } else if (this.UI.mousePos(this.mouseY, 520, 560)) {
                            exit();
                        }
                    }
                }

            }
            case 1 -> {
                this.UI.showGameScreenBackground();

                // Shadows should be drawn separately to prevent overlapping
                for (Snake part : this.snake) {
                    part.drawShadow();
                }
                for (Fruit fruit : this.fruits) {
                    fruit.drawShadow();
                }
                this.UI.showBananaExplosion();
                this.magnetFruits();

                for (int i = snake.length-1; i >= 0; i--) {
                    this.snake[i].drawSprite();
                    this.snake[i].fly();
                    if (i == 0) {
                        this.snake[i].move();
                        this.snake[i].rotate(this.snake[i]);
                    } else {
                        this.snake[i].follow(this.snake[i - 1]);
                        this.snake[i].rotate(this.snake[i - 1]);
                    }
                }
                this.checkForLoseConditions();

                this.fruitLogic();
                this.UI.showGameScreenForeground(this.gameScore);
                this.UI.showCollectedFruits();
            }
            case 2 -> {
                this.UI.showGameOverScreen(this.gameScore);
                this.textAlign(CENTER);
                if (this.mousePressed) {
                    if (this.UI.mousePos(this.mouseX, 115, 350)) {
                        if (this.UI.mousePos(this.mouseY, 375, 430)) {
                            this.gameState = 1;
                            this.startNewGame();
                        } else if (this.UI.mousePos(this.mouseY, 475, 530)) {
                            this.gameState = 0;
                        }
                    }
                }
            }
            case 3 -> {
                this.UI.showRulesScreen();
                if (this.mousePressed) {
                    if (this.UI.mousePos(this.mouseX, 160, 550)) {
                        if (this.UI.mousePos(this.mouseY, 635, 700)) {
                            if (this.firstTime) {
                                this.firstTime = false;
                                this.gameState = 1;
                            } else {
                                this.UI.changeSplashNumber();
                                this.gameState = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public void startNewGame() {
        this.points = 1;
        this.gameScore = 0;
        this.snakeLength = 5;
        this.magnetismPower = 0;
        this.magnetismDuration = 250;
        this.isMagnetic = false;
        this.fruits = new Fruit[5];
        for (int i = 0; i < this.fruits.length; i++) {
            this.fruits[i] = new Fruit(this, random(100, this.width - 100), random(-640, -120));
        }
        this.Constructor = new SnakeConstructor(this, this.snake);
        this.snakeColor = this.Constructor.snakeColor;
        this.UI = new UIManager(this);
        this.UI.changeSplashNumber();
        this.snake = this.Constructor.constructSnake(this.snakeHeadX, this.snakeHeadY, this.snakeLength);
    }

    public void fruitLogic() {
        for (int i = 0; i < this.fruits.length; i++) {
            if (!this.snake[0].isFlying && (this.fruits[i].isCollectable && this.snake[0].isColliding(this.fruits[i]) )) {
                switch (this.fruits[i].fruitType) {
                    case STARFRUIT -> {
                        if (this.UI.collectedEnoughStarFruits()) {
                            this.snakeLength = this.snakeLength/2 + 1;
                            this.snake = this.Constructor.removeSnakePart(this.snakeLength);
                        }
                        this.UI.lastFruitCollected = this.fruits[i].fruitType;
                        this.fruits[i] = new Fruit(this, random(100, this.width - 130), random(-640, -120));
                    }
                    case MELON -> {
                        this.magnetismPower++;
                        this.isMagnetic = true;
                        this.fruits[i] = this.eatFruit(fruits[i]);
                    }
                    case BANANA -> {
                        this.UI.bananaExploded = true;
                        this.UI.getSnakePosition(this.snake[0].coords.getX() - 16, this.snake[0].coords.getY() - 16);
                        this.fruits[i] = this.eatFruit(this.fruits[i]);
                        this.becomeUneatable();
                    }
                    default -> {
                        this.fruits[i] = this.eatFruit(this.fruits[i]);
                    }
                }
            }
            this.fruits[i].fall();
            this.fruits[i].drawSprite();
        }
    }

    public Fruit eatFruit(Fruit fruit) {
        if (this.UI.lastFruitCollected == FruitType.MANGO || this.UI.lastFruitCollected == FruitType.KIWI) this.points++;
        else this.points = 1;

        this.gameScore += this.points;
        this.snakeLength++;
        this.UI.lastFruitCollected = fruit.fruitType;
        this.snake = this.Constructor.addSnakePart(this.snakeLength);
        fruit = new Fruit(this, random(80, this.width - 80), random(-640, -120));
        return fruit;
    }

    public void becomeUneatable() {
        this.snake[0].isFlying = true;
        for (int i = 1; i < this.snake.length; i++) {
            if (this.snake[i-1].zDifference != 0) {
                this.snake[i].zDifference = this.snake[i - 1].zDifference + 1;
            }
            this.snake[i].isFlying = true;
            this.snake[i].isEatable = false;
        }
    }

    public void magnetFruits() {
        if (this.isMagnetic && this.magnetismDuration != 0) {
            for (Fruit fruit : this.fruits) {
                if (fruit.isCollectable) {
                    fruit.coords.setX(lerp(fruit.coords.getX(), this.snake[0].coords.getX(), 0.01f * this.magnetismPower));
                    fruit.coords.setY(lerp(fruit.coords.getY(), this.snake[0].coords.getY(), 0.01f * this.magnetismPower));
                    }
                }
            this.magnetismDuration--;
        } else {
            this.magnetismPower = 0;
            this.isMagnetic = false;
            this.magnetismDuration = 250;
        }
    }

    public void checkForLoseConditions() {
        if (this.snake[0].leftScreen()) {
            this.UI.scores.recalculateScores(System.getProperty("user.name"), this.gameScore);
            this.gameState = 2;
        }
        for (int i = 1; i < this.snake.length; i++) {
            if (this.snake[i].isEatable && this.snake[0].isColliding(this.snake[i])) {
                this.UI.scores.recalculateScores(System.getProperty("user.name"), this.gameScore);
                this.gameState = 2;
            }
        }
    }

    public void keyPressed() {
        switch (this.keyCode) {
            case RIGHT -> this.snake[0].setDirection(Direction.RIGHT);
            case LEFT -> this.snake[0].setDirection(Direction.LEFT);
            case UP -> this.snake[0].setDirection(Direction.UP);
            case DOWN -> this.snake[0].setDirection(Direction.DOWN);
        }
        if (this.key == 's') {
            this.snakeLength += 1;
            this.gameScore += 5;
            this.snake = this.Constructor.addSnakePart(this.snakeLength);
        }
        if (this.key == 'p') {
            this.UI.changeSplashNumber();
        }
    }
}
