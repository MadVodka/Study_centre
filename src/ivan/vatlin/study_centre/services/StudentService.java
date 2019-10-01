package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    List<Student> getStudentsSortByAvgMark();

    List<Student> getStudentsSortByHoursLeft();

    List<Student> getStudentsProbablySuccessful();

    boolean addStudent(Student student);

    int getHoursLeftToStudy(Student student);

    double getAverageMark(Student student);

    int getPossibilityGetExpelled(Student student);
}
