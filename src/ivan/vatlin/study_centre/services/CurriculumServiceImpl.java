package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.repository.CurriculaRepo;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class CurriculumServiceImpl implements CurriculumService {
    private CurriculaRepo curriculaRepo;

    public CurriculumServiceImpl() {
        curriculaRepo = CurriculaRepo.getInstance();
    }

    @Override
    public List<Curriculum> getCurricula() {
        return curriculaRepo.getCurricula();
    }

    @Override
    public Curriculum getAnyCurriculum() {
        int size= curriculaRepo.getCurricula().size();

        Random random = new Random();
        int randomPosition = random.nextInt(size);

        return curriculaRepo.getCurricula().get(randomPosition);
    }

    @Override
    public boolean addCurriculum(Curriculum curriculum) {
        return curriculaRepo.addCurriculum(curriculum);
    }

    @Override
    public int overallHours(Curriculum curriculum) {
        Set<Course> courses = curriculum.getCourses();
        return courses.stream()
                .mapToInt(Course::getHours)
                .sum();
    }

    @Override
    public int overallMarks(Curriculum curriculum) {
        return overallDays(curriculum);
    }

    @Override
    public int overallDays(Curriculum curriculum) {
        double days = overallHours(curriculum) / 8.0;
        return (int) Math.ceil(days);
    }
}
