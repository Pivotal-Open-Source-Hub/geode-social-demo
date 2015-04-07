package io.pivotal.happysocial.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.PersistenceConstructor;

public class PostId implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private String person;
  private UUID postId;
  
  public PostId() {
    
  }

  @PersistenceConstructor
  public PostId(String person) {
    this.person = person;
    this.postId = UUID.randomUUID();
  }

  public String getPerson() {
    return person;
  }

  public void setPerson(String person) {
    this.person = person;
  }

  public UUID getPostId() {
    return postId;
  }

  public void setPostId(UUID postId) {
    this.postId = postId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((person == null) ? 0 : person.hashCode());
    result = prime * result + ((postId == null) ? 0 : postId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PostId other = (PostId) obj;
    if (person == null) {
      if (other.person != null)
        return false;
    } else if (!person.equals(other.person))
      return false;
    if (postId == null) {
      if (other.postId != null)
        return false;
    } else if (!postId.equals(other.postId))
      return false;
    return true;
  }
  
  
}
