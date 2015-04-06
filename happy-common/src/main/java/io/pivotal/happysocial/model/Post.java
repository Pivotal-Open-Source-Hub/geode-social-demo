package io.pivotal.happysocial.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

@Region("posts")
public class Post {

  @Id
  private PostId id;

  private Date date;
  private String text;

  public Post() {
  }
  
  @PersistenceConstructor
  public Post(String personId) {
    this.id = new PostId(personId);
    this.date = new Date();
  }

  public PostId getId() {
    return id;
  }

  public void setId(PostId id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
