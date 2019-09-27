package ivan.vatlin.study_centre.generators;

import java.util.Random;

public class MarkGenerator {
    private Random random;
    private int maxMark;

    public MarkGenerator(int maxMark) {
        this.maxMark = maxMark;
        random = new Random();
    }

    public int generate() {
        return random.nextInt(maxMark) + 1;
    }
}
