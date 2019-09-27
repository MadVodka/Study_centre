package ivan.vatlin.study_centre.demo;

import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.repository.StudentsRepo;
import ivan.vatlin.study_centre.services.StudentService;
import ivan.vatlin.study_centre.services.StudentServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo {
    public void run() {
        Course course = new Course("Spring", 48);
        Course course1 = new Course("Git", 6);
        Course course2 = new Course("Kafka", 30);
        Course course3 = new Course("Angular", 50);
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        Curriculum curriculum = new Curriculum("FullStack", courses);

        Student student = new Student();
        student.setName("Ivan");
        student.setStartStudyingDate(LocalDate.of(2019, 9, 22));
        student.setCurriculum(curriculum);
        student.putMark(3);
        student.putMark(3);
        student.putMark(4);
        student.putMark(4);

        List<Student> students = new ArrayList<>();
        students.add(student);

        StudentsRepo studentsRepo = new StudentsRepo(students);
        StudentService studentService = new StudentServiceImpl(studentsRepo);

        System.out.println("Average mark of " + student + " is " + studentService.averageMark(student));
        System.out.println("Hours left to study is " + studentService.hoursLeftToStudy(student));

        int possibilityExpelled = studentService.possibilityGetExpelled(student);
        if (possibilityExpelled == 1) {
            System.out.println("Keep it up! You are good!");
        } else if (possibilityExpelled == 0) {
            System.out.println("You have to do better! Try your best!");
        } else {
            System.out.println("You get expelled. I'm sorry");
        }
    }
}
