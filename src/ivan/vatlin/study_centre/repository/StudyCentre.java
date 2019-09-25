package ivan.vatlin.study_centre.repository;

import ivan.vatlin.study_centre.entity.Student;

import java.util.List;

public class StudyCentre {
    private List<Student> students;

    public StudyCentre(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}
