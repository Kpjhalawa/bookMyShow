package com.example.demo.model.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name ="Shows")
@Getter
@Setter
public class Show extends BaseModel {
  private Date startTime;
  private Date endTime;
  @ManyToOne
  private Movie movie;
  @ManyToOne
  private Screen screen;
}
