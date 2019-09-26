package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.exceptions.StudentNotFoundException;
import ivan.vatlin.study_centre.repository.StudentsRepo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentServiceImpl implements StudentService {
    private StudentsRepo studentsRepo;

    public StudentServiceImpl(StudentsRepo studentsRepo) {
        this.studentsRepo = studentsRepo;
    }

    @Override
    public List<Student> getStudents() {
        return studentsRepo.getStudents();
    }

    @Override
    public List<Student> getStudentsGetExpelled(long studentId) {
        return null;
    }

    /**
     *
     * @param studentId
     * @return if a student is still studying amount of hours left to study otherwise negative number
     * @throws Exception
     */
    @Override
    public int hoursLeftToStudy(long studentId) throws Exception {
        int hoursToStudyPerDay = 8;

        Student student = studentsRepo.getStudents().stream()
                .filter(studentSearch -> studentSearch.getId() == studentId)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("student with id " + studentId + " not found"));

        int overallHours = student.getCurriculum().getCourses().stream()
                .mapToInt(Course::getHours)
                .sum();

        int daysAlreadyStudiedInclusive = Period.between(student.getStartStudyingDate(), LocalDate.now()).getDays() + 1;

        return overallHours - hoursToStudyPerDay * daysAlreadyStudiedInclusive;
    }

    @Override
    public double averageMark(long studentId) {
        return studentsRepo.getStudents().stream()
                .filter(student -> student.getId() == studentId)
                .map(Student::getMarks)
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(integer -> integer != 0)
                .mapToInt(Integer::intValue)
                .average().getAsDouble();
    }

    @Override
    public boolean possibilityGetExpelled(long studentId) {
        return false;
    }
}
