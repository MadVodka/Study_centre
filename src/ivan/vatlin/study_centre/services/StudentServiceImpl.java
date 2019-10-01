package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.repository.StudentsRepo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private StudentsRepo studentsRepo;
    private CurriculumService curriculumService = new CurriculumServiceImpl();

    public StudentServiceImpl() {
        studentsRepo = StudentsRepo.getInstance();
    }

    @Override
    public List<Student> getStudents() {
        return studentsRepo.getStudents();
    }

    @Override
    public List<Student> getStudentsSortByAvgMark() {
        return getStudents().stream()
                .sorted(Comparator.comparingDouble(this::getAverageMark))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsSortByHoursLeft() {
        return getStudents().stream()
                .sorted(Comparator.comparingInt(this::getHoursLeftToStudy))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsProbablySuccessful() {
        return getStudents().stream()
                .filter(student -> getPossibilityGetExpelled(student) == 0)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addStudent(Student student) {
        return studentsRepo.addStudent(student);
    }

    /**
     * @param student {@link Student} object
     * @return positive number is hours left to study, 0 if study is finished, -1 if study has not been started yet
     */
    @Override
    public int getHoursLeftToStudy(Student student) {
        int hoursToStudyPerDay = 8;

        Period studiedPeriod = Period.between(student.getStartStudyingDate(), LocalDate.now());
        int daysAlreadyStudiedInclusive = studiedPeriod.getDays() + 1;

        if (daysAlreadyStudiedInclusive < 0) {
            return -1;
        }

        int overallDays = curriculumService.getTotalDays(student.getCurriculum());
        if (daysAlreadyStudiedInclusive >= overallDays) {
            return 0;
        }

        int overallHours = curriculumService.getTotalHours(student.getCurriculum());
        return overallHours - hoursToStudyPerDay * daysAlreadyStudiedInclusive;
    }

    @Override
    public double getAverageMark(Student student) {
        return student.getMarks().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    /**
     * @param student {@link Student} object
     * @return 1 if student is successful so far, 0 if he doesn't have passing average mark but still can correct it,
     * -1 if he gets expelled
     */
    @Override
    public int getPossibilityGetExpelled(Student student) {
        double passingMark = 4.5;

        if (getAverageMark(student) >= passingMark) {
            return 1;
        }

        Curriculum curriculum = student.getCurriculum();

        int quantityAllMarks = curriculumService.getTotalMarks(curriculum);
        int quantityExistingMarks = student.getMarks().size();
        int sumExistingMarks = student.getMarks().stream()
                .mapToInt(Integer::intValue)
                .sum();

        double potentialAverageMark = (sumExistingMarks + (quantityAllMarks - quantityExistingMarks) * 5) /
                (double) quantityAllMarks;

        if (potentialAverageMark >= passingMark) {
            return 0;
        }
        return -1;
    }
}
