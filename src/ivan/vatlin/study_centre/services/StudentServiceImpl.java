package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.repository.StudentsRepo;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Override
    public int getDaysLeftToStudy(long studentId) {
        return 0;
    }

    @Override
    public double averageMark(long studentId) {
        return studentsRepo.getStudents().stream()
                .filter(student -> student.getId() == studentId)
                .map(student -> student.getCurriculum().getMarks())
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .average().getAsDouble();
    }

    @Override
    public boolean possibilityGetExpelled(long studentId) {
        return false;
    }
}
