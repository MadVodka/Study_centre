package ivan.vatlin.study_centre.generators;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class DateGenerator {
    private LocalDate startLocalDate;
    private LocalDate endLocalDate;

    public DateGenerator(LocalDate startLocalDate, LocalDate endLocalDate) {
        this.startLocalDate = startLocalDate;
        this.endLocalDate = endLocalDate;
    }

    public LocalDate generate() {
        long daysDifference = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
        long daysOffset = (long) (Math.random() * daysDifference);
        return startLocalDate.plusDays(daysOffset);
    }
}
