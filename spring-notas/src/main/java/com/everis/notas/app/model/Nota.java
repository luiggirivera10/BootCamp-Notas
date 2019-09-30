package com.everis.notas.app.model;

import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model.
 * @author lriveras
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notas")
public class Nota {
  /**
   * .
   */
  @Id
 private String id;
  /**
   * .
   */
  @NotEmpty(message = "'teacherID' No debe ser vacio!")
  @Size(min = 8, max = 8,message = "'teacherID' debe tener 8 caracteres")
 private String teacherID;
  /**
   * .
   */
  @NotEmpty(message = "'studentID' No debe ser vacio!")
  @Size(min = 8, max = 8,message = "'studentID' debe tener 8 caracteres")
 private String studentID;
  /**
   * .
   */
  @NotEmpty(message = "'courseID' No debe ser vacio!")
 private String courseID;
  /**
   * .
   */
  @Min(0)
  @Max(20)
  private Double nota1 = 0.0;
 
  /**
   * .
   */
  @Min(0)
  @Max(20)
  private Double nota2 = 0.0;
 
  /**
   * .
   */
  @Min(0)
  @Max(20)
  private Double nota3 = 0.0;
 
  /**
   * .
   */
  private Double promedio = 0.0;
  /**
   * .
   */
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
 private Date createdAt = new Date();
  /**
   * .
   */
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
 private Date modifiedAt = new Date();  
}
