package ivan.vatlin.study_centre.demo;

import ivan.vatlin.study_centre.data.CurriculumDataHolder;
import ivan.vatlin.study_centre.data.StudentDataHolder;
import ivan.vatlin.study_centre.entity.Course;
import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.generators.DateGenerator;
import ivan.vatlin.study_centre.generators.MarkGenerator;
import ivan.vatlin.study_centre.services.CurriculumService;
import ivan.vatlin.study_centre.services.CurriculumServiceImpl;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.LongStream;

public class DemoDataInitializer {
    private static CurriculumService curriculumService = new CurriculumServiceImpl();
    private static List<Student> students;
    private static List<Curriculum> curricula;

    private DemoDataInitializer() {
    }

    public static List<Student> getInitializedStudents() {
        if (students == null) {
            initializeStudents();
        }
        return students;
    }

    private static void initializeStudents() {
        students = new ArrayList<>();

        List<String> names = StudentDataHolder.getNames();

        LocalDate startLocalDate = LocalDate.of(2019, 6, 15);
        LocalDate endLocalDate = LocalDate.of(2019, 10, 20);
        DateGenerator dateGenerator = new DateGenerator(startLocalDate, endLocalDate);

        MarkGenerator markGenerator = new MarkGenerator(5);

        for (String name : names) {
            Student student = new Student(name);

            LocalDate startingDate = dateGenerator.generate();
            student.setStartStudyingDate(startingDate);
            Curriculum curriculum = curriculumService.getAnyCurriculum();
            student.setCurriculum(curriculum);

            long quantityMarksToPut = curriculumService.getDaysPassed(startingDate, curriculum);
            LongStream.range(0, quantityMarksToPut).forEach(value -> {
                int mark = markGenerator.generate();
                student.putMark(mark);
            });

            students.add(student);
        }
    }

    public static List<Curriculum> getInitializedCurricula() {
        if (curricula == null) {
            initializeCurricula();
        }
        return curricula;
    }

    private static void initializeCurricula() {
        curricula = new ArrayList<>();

        int minHours = 8;
        int maxHours = 60;
        Random random = new Random();

        Map<String, Set<String>> courses = CurriculumDataHolder.getCourses();
        courses.forEach((title, coursesSet) -> {
            Set<Course> curriculumCourses = new LinkedHashSet<>();
            for (String courseName : coursesSet) {
                int hours = random.nextInt(maxHours - minHours) + minHours;
                Course course = new Course(courseName, hours);
                curriculumCourses.add(course);
            }
            Curriculum curriculum = new Curriculum(title, curriculumCourses);
            curricula.add(curriculum);
        });
    }
}
