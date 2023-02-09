package util.texts;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SplashTextLoader {
    public String[] splashes;

    public SplashTextLoader() {
        try {
            this.splashes = Files.readString(Paths.get("src/util/texts/splash.txt")).split("\n");
        } catch (Exception exception) {
            this.splashes = new String[]{"I've failed loading splashes"};
        }
    }
}
