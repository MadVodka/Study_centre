package ivan.vatlin.study_centre.repository;

import ivan.vatlin.study_centre.entity.Student;

import java.util.List;

public class StudentsRepo {
    private List<Student> students;

    public StudentsRepo(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}
