package spring.project.resume_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.project.resume_analyzer.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
