package com.everis.notas.app.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.everis.notas.app.model.Nota;
import com.everis.notas.app.repository.NotaRepository;
import com.everis.notas.app.service.NotaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@Service
public class NotaServiceImpl implements NotaService{

  /**
   * .
   */
  @Autowired
  private NotaRepository notarepository;

  /**
   * .
   */
  @Override
  public Flux<Nota> findAll() {
    return notarepository.findAll();
  }
  
  /**
   * .
   */
  @Override
  public Mono<Nota> findById(String id) {
    return notarepository.findById(id);
  }
  
  /**
   * .
   */
  @Override
  public Mono<Nota> save(Nota nota) {
    return notarepository.save(nota);
  }
  
  /**
   * .
   */
  @Override
  public Mono<Void> delete(Nota nota) {
    return notarepository.delete(nota);
  }
  
  /**
   * .
   */
  @Override
  public Flux<Nota> findByStudentID(String studentID) {
    return notarepository.findByStudentID(studentID);
  }
  
  /**
   * .
   */
  @Override
  public Flux<Nota> findByTeacherID(String teacherID) {
    return notarepository.findByTeacherID(teacherID);
   }
  
  /**
   * .
   */
  @Override
  public Flux<Nota> findByCourseID(String courseID) {
    return notarepository.findByCourseID(courseID);
  }

}
