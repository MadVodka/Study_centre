package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.exceptions.StudentNotFoundException;
import ivan.vatlin.study_centre.repository.StudentsRepo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

public class StudentServiceImpl implements StudentService {
    private StudentsRepo studentsRepo;
    private CurriculumService curriculumService = new CurriculumServiceImpl();

    public StudentServiceImpl(StudentsRepo studentsRepo) {
        this.studentsRepo = studentsRepo;
    }

    @Override
    public List<Student> getStudents() {
        return studentsRepo.getStudents();
    }

    @Override
    public Student getStudent(long id) throws StudentNotFoundException {
        return studentsRepo.getStudents().stream()
                .filter(studentSearch -> studentSearch.getId() == id)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("student with id " + id + " not found"));
    }

    @Override
    public List<Student> getStudentsGetExpelled() {
        return null;
    }

    @Override
    public int hoursLeftToStudy(Student student) {
        int hoursToStudyPerDay = 8;

        Set<Course> courses = student.getCurriculum().getCourses();
        int overallHours = courses.stream()
                .mapToInt(Course::getHours)
                .sum();

        Period studiedPeriod = Period.between(student.getStartStudyingDate(), LocalDate.now());
        int daysAlreadyStudiedInclusive = studiedPeriod.getDays() + 1;

        return overallHours - hoursToStudyPerDay * daysAlreadyStudiedInclusive;
    }

    @Override
    public double averageMark(Student student) {
        return student.getMarks().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    /**
     * @param student
     * @return 1 if student is successful so far, 0 if he doesn't have passing average mark but still can correct it,
     * -1 if he gets expelled
     */
    @Override
    public int possibilityGetExpelled(Student student) {
        double passingMark = 4.5;

        if (averageMark(student) >= passingMark) {
            return 1;
        } else {
            Curriculum curriculum = student.getCurriculum();

            int quantityAllMarks = curriculumService.quantityMarks(curriculum);
            int quantityExistingMarks = student.getMarks().size();
            int sumExistingMarks = student.getMarks().stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            double potentialAverageMark = (sumExistingMarks + (quantityAllMarks - quantityExistingMarks) * 5) /
                    (double) quantityAllMarks;

            if (potentialAverageMark >= passingMark) {
                return 0;
            }
        }
        return -1;
    }
}
