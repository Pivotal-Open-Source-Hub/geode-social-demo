package io.pivotal.happysocial.sentiment;

import io.pivotal.happysocial.model.Post;

import java.util.Collection;

public interface SentimentAnalyzer {

  public String analyze(Collection<Post> posts);
  
}
