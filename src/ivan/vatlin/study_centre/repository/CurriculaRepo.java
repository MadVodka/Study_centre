package ivan.vatlin.study_centre.repository;

import ivan.vatlin.study_centre.entity.Curriculum;

import java.util.ArrayList;
import java.util.List;

public class CurriculaRepo {
    private List<Curriculum> curricula;
    private static CurriculaRepo curriculaRepo;

    private CurriculaRepo() {
        curricula = new ArrayList<>();
    }

    public static CurriculaRepo getInstance() {
        if (curriculaRepo == null) {
            curriculaRepo = new CurriculaRepo();
        }
        return curriculaRepo;
    }

    public boolean addCurriculum(Curriculum curriculum) {
        return curricula.add(curriculum);
    }

    public List<Curriculum> getCurricula() {
        return curricula;
    }
}