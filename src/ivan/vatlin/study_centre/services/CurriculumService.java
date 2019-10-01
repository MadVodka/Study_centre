package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Curriculum;

import java.time.LocalDate;
import java.util.List;

public interface CurriculumService {
    List<Curriculum> getCurricula();

    Curriculum getAnyCurriculum();

    boolean addCurriculum(Curriculum curriculum);

    int getTotalHours(Curriculum curriculum);

    int getTotalMarks(Curriculum curriculum);

    int getTotalDays(Curriculum curriculum);

    long getDaysPassed(LocalDate startingDate, Curriculum curriculum);
}
