package ivan.vatlin.study_centre.repository;

import ivan.vatlin.study_centre.demo.DemoDataInitializer;
import ivan.vatlin.study_centre.entity.Student;

import java.util.List;

public class StudentsRepo {
    private List<Student> students;
    private static StudentsRepo studentsRepo;

    public static StudentsRepo getInstance() {
        if (studentsRepo == null) {
            studentsRepo = new StudentsRepo();
        }
        return studentsRepo;
    }

    private StudentsRepo() {
        students = DemoDataInitializer.getInitializedStudents();
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }
}
