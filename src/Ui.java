public class Ui {
    private static final String[] HANGMAN_STAGES = {
            """
         ___________
         |         |
         |
         |
         |
         |
         |
        """,
            """
         ___________
         |         |
         |         O
         |
         |
         |
         |
        """,
            """
         ___________
         |         |
         |         O
         |         |
         |         |
         |
         |
        """,
            """
         ___________
         |         |
         |         O
         |        /|
         |         |
         |
         |
        """,
            """
         ___________
         |         |
         |         O
         |        /|\\
         |         |
         |
         |
        """,
            """
         ___________
         |         |
         |         O
         |        /|\\
         |         |
         |        /
         |
        """,
            """
         ___________
         |         |
         |         O
         |        /|\\
         |         |
         |        / \\
         |
        """
    };

    public static void draw(int wrongAnswers) {
        if (wrongAnswers == 0) return;

        int stageIndex = wrongAnswers - 1;
        if (stageIndex >= 0 && stageIndex < HANGMAN_STAGES.length) {
            System.out.println(HANGMAN_STAGES[stageIndex]);
        } else {
            System.out.println(HANGMAN_STAGES[HANGMAN_STAGES.length - 1]);
        }
    }
}