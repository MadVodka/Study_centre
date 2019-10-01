package ivan.vatlin.study_centre.data;

import java.util.Arrays;
import java.util.List;

public class StudentDataHolder {
    private static List<String> names;
    private static final String[] NAMES = {
            "Oleg", "Vladimir", "Raya", "Ulyana", "Mikhail", "Roman", "Ekaterina", "Irina", "Nikolay", "Fedor",
            "Nadezhda", "Elena"
    };

    static {
        names = Arrays.asList(NAMES);
    }

    public static List<String> getNames() {
        return names;
    }
}
