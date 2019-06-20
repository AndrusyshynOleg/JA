package logos.lesson19.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.lesson19.domain.Student;


public interface StudentRepository extends JpaRepository<Student, String> {

}
