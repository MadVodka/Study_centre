package ivan.vatlin.study_centre.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private static long idIncrement = 0;
    private final long id = idIncrement++;
    private String name;
    private Curriculum curriculum;
    private List<Integer> marks;
    private LocalDate startStudyingDate;

    public Student(String name) {
        this.name = name;
        marks = new ArrayList<>();
    }

    public long getId() {
        return id;
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
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public boolean putMark(Integer mark) {
        return marks.add(mark);
    }

    public LocalDate getStartStudyingDate() {
        return startStudyingDate;
    }

    public void setStartStudyingDate(LocalDate startStudyingDate) {
        this.startStudyingDate = startStudyingDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
