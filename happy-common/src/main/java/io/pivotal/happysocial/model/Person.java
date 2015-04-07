package io.pivotal.happysocial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

@Region("people")
public class Person {

  @Id
  private String name;
  private String description;
  
  public Person() {
  }

  @PersistenceConstructor
  public Person(String name, String description) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  
}