package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Curriculum;

import java.util.List;

public interface CurriculumService {
    List<Curriculum> getCurricula();

    Curriculum getAnyCurriculum();

    boolean addCurriculum(Curriculum curriculum);

    int overallHours(Curriculum curriculum);

    int overallMarks(Curriculum curriculum);

    int overallDays(Curriculum curriculum);
}
