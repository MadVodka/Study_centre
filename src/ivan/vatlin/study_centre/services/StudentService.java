package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    List<Student> getStudentsSortByAvgMark();

    List<Student> getStudentsSortByHoursLeft();

    boolean addStudent(Student student);

    int daysPassed(Student student);

    int hoursLeftToStudy(Student student);

    double averageMark(Student student);

    int possibilityGetExpelled(Student student);
}
