package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;

public class CurriculumServiceImpl implements CurriculumService {
    @Override
    public int quantityMarks(Curriculum curriculum) {
        int overallHours = curriculum.getCourses().stream()
                .mapToInt(Course::getHours)
                .sum();

        double days = overallHours/8.0;
        return (int) Math.ceil(days);
    }
}
