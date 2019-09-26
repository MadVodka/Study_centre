package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.exceptions.StudentNotFoundException;
import ivan.vatlin.study_centre.repository.StudentsRepo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public List<Student> getStudentsGetExpelled(long studentId) {
        return null;
    }

    @Override
    public int hoursLeftToStudy(long studentId) throws StudentNotFoundException {
        int hoursToStudyPerDay = 8;

        Student student = getStudent(studentId);

        int overallHours = student.getCurriculum().getCourses().stream()
                .mapToInt(Course::getHours)
                .sum();

        int daysAlreadyStudiedInclusive = Period.between(student.getStartStudyingDate(), LocalDate.now()).getDays() + 1;

        return overallHours - hoursToStudyPerDay * daysAlreadyStudiedInclusive;
    }

    @Override
    public double averageMark(long studentId) throws StudentNotFoundException {
        Student student = getStudent(studentId);

        return student.getMarks().stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();
    }

    @Override
    public boolean possibilityGetExpelled(long studentId) throws StudentNotFoundException {
        Logger logger = Logger.getGlobal();

        Student student = getStudent(studentId);
        Curriculum curriculum = student.getCurriculum();

        int amountOfMarks = curriculumService.amountOfMarks(curriculum);
        logger.log(Level.INFO, "amount of marks is " + amountOfMarks);

        int sumOfExistingMarks = student.getMarks().stream()
                .mapToInt(Integer::intValue)
                .sum();

        int amountExistingMarks = student.getMarks().size();

        return false;
    }
}
