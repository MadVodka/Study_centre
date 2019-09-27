package ivan.vatlin.study_centre;

import ivan.vatlin.study_centre.demo.Demo;
import ivan.vatlin.study_centre.demo.DemoImpl;
import ivan.vatlin.study_centre.services.CurriculumService;
import ivan.vatlin.study_centre.services.CurriculumServiceImpl;
import ivan.vatlin.study_centre.services.StudentService;
import ivan.vatlin.study_centre.services.StudentServiceImpl;

public class Application {
    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();
        CurriculumService curriculumService = new CurriculumServiceImpl();

        Demo demo = new DemoImpl(studentService, curriculumService);
        demo.run();
    }
}
