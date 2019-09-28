package ivan.vatlin.study_centre.demo;

import ivan.vatlin.study_centre.data.StudentDataHolder;
import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.generators.MarkGenerator;
import ivan.vatlin.study_centre.services.CurriculumService;
import ivan.vatlin.study_centre.services.CurriculumServiceImpl;
import ivan.vatlin.study_centre.services.StudentService;
import ivan.vatlin.study_centre.services.StudentServiceImpl;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

class DemoDataInitializer {
    private static DemoDataInitializer demoDataInitializer;
    private CurriculumService curriculumService;
    private StudentService studentService;
    private boolean isInitialized = false;

    private DemoDataInitializer() {
        curriculumService = new CurriculumServiceImpl();
        studentService = new StudentServiceImpl();
    }

    static void initialize() {
        if (demoDataInitializer == null) {
            demoDataInitializer = new DemoDataInitializer();
        }
        if (!demoDataInitializer.isInitialized) {
            demoDataInitializer.initializeCurriculum();
            demoDataInitializer.initializeStudents();
            demoDataInitializer.isInitialized = true;
        }
    }

    private void initializeStudents() {
        String[] names = StudentDataHolder.NAMES;
        String[] dates = StudentDataHolder.DATES;
        MarkGenerator markGenerator = new MarkGenerator(5);

        for (int i = 0; i < names.length; i++) {
            Student student = new Student(names[i]);

            LocalDate startingDate = LocalDate.parse(dates[i]);
            student.setStartStudyingDate(startingDate);

            Curriculum curriculum = curriculumService.getAnyCurriculum();
            student.setCurriculum(curriculum);

            int quantityMarksToPut = studentService.daysPassed(student);
            for (int j = 0; j < quantityMarksToPut; j++) {
                int mark = markGenerator.generate();
                student.putMark(mark);
            }

            studentService.addStudent(student);
        }
    }

    private void initializeCurriculum() {
        curriculumService.addCurriculum(createDatabaseCurriculum());
        curriculumService.addCurriculum(createJavaFundamentalsCurriculum());
        curriculumService.addCurriculum(createFrontendCurriculum());
    }

    private Curriculum createDatabaseCurriculum() {
        Course course = new Course("Tables design", 20);
        Course course1 = new Course("SQL", 6);
        Course course2 = new Course("JDBC", 50);
        Course course3 = new Course("Spring JDBC", 30);

        Set<Course> databaseCourses = new LinkedHashSet<>();
        databaseCourses.add(course);
        databaseCourses.add(course1);
        databaseCourses.add(course2);
        databaseCourses.add(course3);

        return new Curriculum("Database in Java", databaseCourses);
    }

    private Curriculum createJavaFundamentalsCurriculum() {
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

        return new Curriculum("Java Fundamentals", databaseCourses);
    }

    private Curriculum createFrontendCurriculum() {
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

        return new Curriculum("Frontend", databaseCourses);
    }
}
