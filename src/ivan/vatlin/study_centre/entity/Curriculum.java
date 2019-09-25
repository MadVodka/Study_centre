package ivan.vatlin.study_centre.entity;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Curriculum {
    private String name;
    private Set<Course> courses;
    private Map<Course, Integer> marks;

    public Curriculum(String name, Set<Course> courses) {
        this.name = name;
        this.courses = courses;
        marks = courses.stream().collect(Collectors.toMap(course -> course, null));
    }

    public String getName() {
        return name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Map<Course, Integer> getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Curriculum curriculum = (Curriculum) o;

        return name.equals(curriculum.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
