package com.everis.notas.app.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.everis.notas.app.model.Nota;
import com.everis.notas.app.service.NotaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@RestController
@RefreshScope
@RequestMapping("/api/v1.0")
public class NotaRestController {

  /**
   * Injected LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(NotaRestController.class);

  /**
 * Injected service.
 */
  @Autowired
  private NotaService service;
  
  /**
   * . Service listar Course.
   */
  @GetMapping("/notas")
  public Mono<ResponseEntity<Flux<Nota>>> findAll() {
    return Mono.just(

        ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(service.findAll()));
  }

  /**

   * Servicio buscar por ID.

   */
  @GetMapping("/notas/{id}")
 public Mono<Nota> findById(@PathVariable final String id) {
    final Flux<Nota> notass = service.findAll();
    final Mono<Nota> nota = notass.filter(s -> s.getId().equals(id))
                        .next().doOnNext(nots -> LOG.info(nots.getStudentID()));
    return nota;
  }

  /**

   * Servicio findBystudentID.

   */
  @GetMapping("/notas/student/{studentID}")
 public Flux<Nota> findBystudentID(@PathVariable final String studentID) {
    return service.findByStudentID(studentID).doOnNext(stud -> LOG.info(stud.getStudentID()));
  }

  /**

   * Servicio buscar por ID.

   */
  @GetMapping("/notas/teacher/{teacherID}")
 public Flux<Nota> findByTeacherID(@PathVariable final String teacherID) {
    return service.findByTeacherID(teacherID).doOnNext(teach -> LOG.info(teach.getTeacherID()));
  }
  
  /**

   * Servicio buscar por ID.

   */
  @GetMapping("/notas/course/{courseID}")
 public Flux<Nota> findByCourseID(@PathVariable final String courseID) {
    return service.findByCourseID(courseID).doOnNext(cours -> LOG.info(cours.getCourseID()));
  }

  /**
   * . Service save
   */

  @PostMapping("/notas")
  public Mono<ResponseEntity<Nota>> newCourse(@Valid @RequestBody final Nota nota) {
    return service.save(nota)
              .map(addNota -> new ResponseEntity<>(addNota, HttpStatus.CREATED))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
  }

  /**

   * Servicio para modificar.

   */
  @PutMapping("/notas/{id}")
    public Mono<ResponseEntity<Nota>> updateNota(@PathVariable(value = "id") final String id,
                                                   @Valid @RequestBody final Nota nota) {
    return service.findById(id)
                .flatMap(existingNota -> {
                  existingNota.setTeacherID(nota.getTeacherID());
                  existingNota.setStudentID(nota.getStudentID());
                  existingNota.setStudentID(nota.getCourseID());
                  existingNota.setModifiedAt(nota.getModifiedAt());
                  existingNota.setNota1(nota.getNota1());
                  existingNota.setNota2(nota.getNota2());
                  existingNota.setNota3(nota.getNota3());
                  existingNota.setPromedio((nota.getNota1()+nota.getNota2()+nota.getNota3())/3);
                  return service.save(existingNota);
                })
                .map(updateNota -> new ResponseEntity<>(updateNota, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**

   * Servicio para eliminar.

   */
  @DeleteMapping("/notas/{id}")
  public Mono<ResponseEntity<Void>> deleteNota(@PathVariable(value = "id") final String id) {
    return service.findById(id)
    .flatMap(existingNota ->
 service.delete(existingNota)
 .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
 .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
