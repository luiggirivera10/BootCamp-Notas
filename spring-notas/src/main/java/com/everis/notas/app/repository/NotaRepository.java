package com.everis.notas.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.everis.notas.app.model.Nota;
import reactor.core.publisher.Flux;

public interface NotaRepository extends ReactiveMongoRepository<Nota, String>{

  /**
 * findByStudentID.
 */
  Flux<Nota> findByStudentID(String studentID);

  /**
 * findByTeacherID.
 */
  Flux<Nota> findByTeacherID(String teacherID);

  /**
 * findByTeacherID.
 */
  Flux<Nota> findByCourseID(String courseID);
}
