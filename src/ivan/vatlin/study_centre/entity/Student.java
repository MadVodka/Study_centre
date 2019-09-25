package ivan.vatlin.study_centre.entity;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class Student {
    private long id;
    private String name;
    private Curriculum curriculum;
    private Map<Course, Integer> marks;
    private LocalDate startStudyingDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
        marks = curriculum.getCourses().stream()
                .collect(Collectors.toMap(course -> course, null));
    }

    public LocalDate getStartStudyingDate() {
        return startStudyingDate;
    }

    public void setStartStudyingDate(LocalDate startStudyingDate) {
        this.startStudyingDate = startStudyingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
