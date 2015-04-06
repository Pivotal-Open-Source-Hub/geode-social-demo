package io.pivotal.happysocial.mood;

import io.pivotal.happysocial.model.Post;

import java.util.Collection;

public interface MoodScorer {

  public String computeMood(Collection<Post> posts);
  
}
