package ivan.vatlin.study_centre.entity;

import java.util.Set;

public class Curriculum {
    private String name;
    private Set<Course> courses;

    public Curriculum(String name, Set<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
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
