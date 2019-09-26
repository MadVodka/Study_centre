package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    List<Student> getStudents(); // overload method with sorting argument

    List<Student> getStudentsGetExpelled(long studentId);

    int hoursLeftToStudy(long studentId) throws StudentNotFoundException;

    double averageMark(long studentId);

    boolean possibilityGetExpelled(long studentId);
}
