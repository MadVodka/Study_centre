package ivan.vatlin.study_centre.data;

import java.util.*;

public class CurriculumDataHolder {
    private static Map<String, Set<String>> courses;
    private static final String[] DATABASE_COURSES = {
            "Tables design", "SQL", "JDBC", "Spring JDBC"
    };
    private static final String[] JAVA_FUNDAMENTAL_COURSES = {
            "Variables and data types", "Object oriented programming", "Polymorphism",
            "Data handling", "Collections", "Stream API"
    };

    private static final String[] FRONTEND_COURSES = {
            "HTML", "CSS", "XML", "Javascript", "Popular JS frameworks", "Angular"
    };

    static {
        Set<String> databaseCourses = new LinkedHashSet<>(Arrays.asList(DATABASE_COURSES));
        Set<String> javaFundamentalCourses = new LinkedHashSet<>(Arrays.asList(JAVA_FUNDAMENTAL_COURSES));
        Set<String> frontendCourses = new LinkedHashSet<>(Arrays.asList(FRONTEND_COURSES));

        courses = new HashMap<>();
        courses.put("Databases in Java", databaseCourses);
        courses.put("Java Fundamentals", javaFundamentalCourses);
        courses.put("Frontend", frontendCourses);
    }

    private CurriculumDataHolder() {
    }

    public static Map<String, Set<String>> getCourses() {
        return courses;
    }
}
