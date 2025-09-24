import database.WordBase;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            NonInteractiveMode.run(args[0], args[1]);
        } else {
            InteractiveMode.run();
        }
        Game.close();
    }
}