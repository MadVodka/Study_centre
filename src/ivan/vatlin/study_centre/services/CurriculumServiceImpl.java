package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.repository.CurriculaRepo;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CurriculumServiceImpl implements CurriculumService {
    private CurriculaRepo curriculaRepo;
    private Random random;

    public CurriculumServiceImpl() {
        curriculaRepo = CurriculaRepo.getInstance();
        random = new Random();
    }

    @Override
    public List<Curriculum> getCurricula() {
        return curriculaRepo.getCurricula();
    }

    @Override
    public Curriculum getAnyCurriculum() {
        int size = curriculaRepo.getCurricula().size();
        int randomPosition = random.nextInt(size);
        return curriculaRepo.getCurricula().get(randomPosition);
    }

    @Override
    public boolean addCurriculum(Curriculum curriculum) {
        return curriculaRepo.addCurriculum(curriculum);
    }

    @Override
    public int getTotalHours(Curriculum curriculum) {
        Set<Course> courses = curriculum.getCourses();
        return courses.stream()
                .mapToInt(Course::getHours)
                .sum();
    }

    @Override
    public int getTotalMarks(Curriculum curriculum) {
        return getTotalDays(curriculum);
    }

    @Override
    public int getTotalDays(Curriculum curriculum) {
        double days = getTotalHours(curriculum) / 8.0;
        return (int) Math.ceil(days);
    }

    @Override
    public long getDaysPassed(LocalDate startingDate, Curriculum curriculum) {
        long daysAlreadyStudiedInclusive = ChronoUnit.DAYS.between(startingDate, LocalDate.now()) + 1;
        int daysToStudyOverall = getTotalDays(curriculum);

        if (daysAlreadyStudiedInclusive < 0) {
            return 0;
        } else if (daysAlreadyStudiedInclusive > daysToStudyOverall) {
            return daysToStudyOverall;
        }
        return daysAlreadyStudiedInclusive;
    }
}
