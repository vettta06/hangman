//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import database.WordBase;
import main.NonInteractiveMode;
import InteractiveMode;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            NonInteractiveMode.run(args[0], args[1]);
        } else {
            InteractiveMode.run();
        }
        UIUtils.close();
    }
}