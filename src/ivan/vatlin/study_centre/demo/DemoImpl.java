package ivan.vatlin.study_centre.demo;

import ivan.vatlin.study_centre.data.StudentData;
import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.generators.MarkGenerator;
import ivan.vatlin.study_centre.services.CurriculumService;
import ivan.vatlin.study_centre.services.StudentService;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DemoImpl implements Demo {
    private StudentService studentService;
    private CurriculumService curriculumService;

    public DemoImpl(StudentService studentService, CurriculumService curriculumService) {
        this.studentService = studentService;
        this.curriculumService = curriculumService;
    }

    @Override
    public void run() {
        initializeCurriculum();
        initializeStudents();

        List<Student> students = studentService.getStudentsSortByHoursLeft();
        for (Student student : students) {
            System.out.println(student.getName()+":");
            System.out.println("Hours left to study by curriculum " + student.getCurriculum().getName() + " - "
                    + studentService.hoursLeftToStudy(student));
            System.out.println("Average mark - " + studentService.averageMark(student));

            int possibilityExpelled = studentService.possibilityGetExpelled(student);
            if (possibilityExpelled == 1) {
                System.out.println("Keep it up! You are good!");
            } else if (possibilityExpelled == 0) {
                System.out.println("You have to do better! Try your best!");
            } else {
                System.out.println("You get expelled. I'm sorry");
            }

            System.out.println("-------------------------");
        }
    }

    private void initializeStudents() {
        String[] names = StudentData.NAMES;
        String[] dates = StudentData.DATES;
        MarkGenerator markGenerator = new MarkGenerator(5);

        for (int i = 0; i < names.length; i++) {
            Student student = new Student(names[i]);

            LocalDate startingDate = LocalDate.parse(dates[i]);
            student.setStartStudyingDate(startingDate);

            Curriculum curriculum = curriculumService.getAnyCurriculum();
            student.setCurriculum(curriculum);

            for (int j = 0; j < 5; j++) {
                int mark = markGenerator.generate();
                student.putMark(mark);
            }

            studentService.addStudent(student);
        }
    }

    private void initializeCurriculum() {
        initializeDatabaseCurriculum();
        initializeJavaFundamentalsCurriculum();
        initializeFrontendCurriculum();
    }

    private void initializeDatabaseCurriculum() {
        Course course = new Course("Tables design", 20);
        Course course1 = new Course("SQL", 6);
        Course course2 = new Course("JDBC", 50);
        Course course3 = new Course("Spring JDBC", 30);

        Set<Course> databaseCourses = new LinkedHashSet<>();
        databaseCourses.add(course);
        databaseCourses.add(course1);
        databaseCourses.add(course2);
        databaseCourses.add(course3);

        Curriculum databaseInJavaCurriculum = new Curriculum("Database in Java", databaseCourses);
        curriculumService.addCurriculum(databaseInJavaCurriculum);
    }

    private void initializeJavaFundamentalsCurriculum() {
        Course course = new Course("Variables and data types", 8);
        Course course1 = new Course("Object oriented programming", 14);
        Course course2 = new Course("Polymorphism", 22);
        Course course3 = new Course("Data handling", 15);
        Course course4 = new Course("Collections", 48);
        Course course5 = new Course("Stream API", 48);

        Set<Course> databaseCourses = new LinkedHashSet<>();
        databaseCourses.add(course);
        databaseCourses.add(course1);
        databaseCourses.add(course2);
        databaseCourses.add(course3);
        databaseCourses.add(course4);
        databaseCourses.add(course5);

        Curriculum javaFundamentalsCurriculum = new Curriculum("Java Fundamentals", databaseCourses);
        curriculumService.addCurriculum(javaFundamentalsCurriculum);
    }

    private void initializeFrontendCurriculum() {
        Course course = new Course("HTML", 20);
        Course course1 = new Course("CSS", 20);
        Course course2 = new Course("XML", 16);
        Course course3 = new Course("Javascript", 60);
        Course course4 = new Course("Popular JS frameworks", 8);
        Course course5 = new Course("Angular", 48);

        Set<Course> databaseCourses = new LinkedHashSet<>();
        databaseCourses.add(course);
        databaseCourses.add(course1);
        databaseCourses.add(course2);
        databaseCourses.add(course3);
        databaseCourses.add(course4);
        databaseCourses.add(course5);

        Curriculum frontendCurriculum = new Curriculum("Frontend", databaseCourses);
        curriculumService.addCurriculum(frontendCurriculum);
    }
}
