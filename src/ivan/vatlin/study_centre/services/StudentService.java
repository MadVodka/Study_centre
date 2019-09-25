package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Student;

import java.util.List;

public interface StudentService {
    void addStudents(List<Student> students);

    List<Student> getStudents(); // overload method with sorting argument

    List<Student> getStudentsGetExpelled();

    int getDaysLeftToStudy();

    double averageMark();

    boolean possibilityGetExpelled();
}
