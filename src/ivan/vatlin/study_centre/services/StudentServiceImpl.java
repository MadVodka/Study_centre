package ivan.vatlin.study_centre.services;

import ivan.vatlin.study_centre.entity.Student;
import ivan.vatlin.study_centre.repository.StudentsRepo;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentsRepo studentsRepo;

    public StudentServiceImpl(StudentsRepo studentsRepo) {
        this.studentsRepo = studentsRepo;
    }

    @Override
    public List<Student> getStudents() {
        return studentsRepo.getStudents();
    }

    @Override
    public List<Student> getStudentsGetExpelled(long studentId) {
        return null;
    }

    @Override
    public int getDaysLeftToStudy(long studentId) {
        return 0;
    }

    @Override
    public double averageMark(long studentId) {
        return 0;
    }

    @Override
    public boolean possibilityGetExpelled(long studentId) {
        return false;
    }
}
