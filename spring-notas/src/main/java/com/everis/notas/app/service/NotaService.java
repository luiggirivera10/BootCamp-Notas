package com.everis.notas.app.service;

import com.everis.notas.app.model.Nota;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotaService {
  /**
   * Metodo FindAll.
   */
    Flux<Nota> findAll();

    /**
   * Metodo FindById.
   */
    Mono<Nota> findById(String id);

    /**
   * Metodo save.
   */
    Mono<Nota> save(Nota nota);

    /**
   * Metodo delete.
   */
    Mono<Void> delete(Nota nota);

    /**
   * Metodo FindById.
   */
    Flux<Nota> findByStudentID(String studentID);

    /**
   * Metodo FindById.
   */
    Flux<Nota> findByTeacherID(String teacherID);

    /**
   * Metodo FindById.
   */
    Flux<Nota> findByCourseID(String courseID);
}
