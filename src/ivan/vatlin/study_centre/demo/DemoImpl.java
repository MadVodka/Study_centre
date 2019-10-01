package ivan.vatlin.study_centre.demo;

import ivan.vatlin.study_centre.entity.Curriculum;
import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.services.CurriculumService;
import ivan.vatlin.study_centre.services.StudentService;

import java.util.List;

public class DemoImpl implements Demo {
    private StudentService studentService;
    private CurriculumService curriculumService;
    private static final String DASH_DELIMITER = "-------------------------";
    private static final String ASTERISK_DELIMITER = "****************************";

    public DemoImpl(StudentService studentService, CurriculumService curriculumService) {
        this.studentService = studentService;
        this.curriculumService = curriculumService;
    }

    @Override
    public void run() {
        List<Curriculum> curricula = curriculumService.getCurricula();
        System.out.println(curricula);
        System.out.println(ASTERISK_DELIMITER);

        List<Student> students = studentService.getStudents();
        printStudents(students);

        System.out.println("Студенты отсортированные по оставшемуся количеству часов");
        List<Student> studentsSortByHoursLeft = studentService.getStudentsSortByHoursLeft();
        printStudents(studentsSortByHoursLeft);

        System.out.println("Студенты отсортированные по среднему баллу");
        List<Student> studentsSortByAvgMark = studentService.getStudentsSortByAvgMark();
        printStudents(studentsSortByAvgMark);

        System.out.println("Студенты, которые еще возможно пройдут дальше");
        List<Student> studentsProbablySuccessful = studentService.getStudentsProbablySuccessful();
        printStudents(studentsProbablySuccessful);
    }

    private void printStudents(List<Student> students) {
        System.out.println();
        for (Student student : students) {
            System.out.println(student.getName() + ":");
            System.out.println(createMessageOnLeftHours(student));

            int hoursLeftToStudy = studentService.getHoursLeftToStudy(student);
            if (hoursLeftToStudy >= 0) {
                System.out.println("Оценки - " + student.getMarks());
                System.out.printf("Средняя оценка - %.2f%n", studentService.getAverageMark(student));
                System.out.println(createMessageOnPossibilityGetExpelled(student));
            }
            System.out.println(DASH_DELIMITER);
        }
        System.out.println(ASTERISK_DELIMITER);
    }

    private String createMessageOnLeftHours(Student student) {
        int hoursLeftToStudy = studentService.getHoursLeftToStudy(student);
        if (hoursLeftToStudy < 0) {
            return "Обучение по программе " + student.getCurriculum().getName() + " еще не началось.";
        } else if (hoursLeftToStudy == 0) {
            return "Обучение по программе " + student.getCurriculum().getName() + " завершено.";
        }
        return "Остаток часов по программе " + student.getCurriculum().getName() + " - " + hoursLeftToStudy;
    }

    private String createMessageOnPossibilityGetExpelled(Student student) {
        int hoursLeftToStudy = studentService.getHoursLeftToStudy(student);
        int possibilityExpelled = studentService.getPossibilityGetExpelled(student);

        if (hoursLeftToStudy == 0) {
            if (possibilityExpelled == 1) {
                return "Обучение пройдено успешно! Вы проходите дальше!";
            }
            return "Проходной бал не пройден. Вы отчислены.";
        }

        if (possibilityExpelled == 1) {
            return "Продолжайте в том же духе!";
        } else if (possibilityExpelled == 0) {
            return "Приложите больше усилий, чтобы набрать проходной балл.";
        }
        return "Вы отчислены.";
    }
}
