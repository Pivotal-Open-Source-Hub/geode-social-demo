package io.pivotal.happysocial.functions;

import io.pivotal.happysocial.model.SentimentResult;
import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.sentiment.SentimentAnalyzer;
import io.pivotal.happysocial.repositories.PostRepository;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.function.annotation.Filter;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;
import org.springframework.stereotype.Component;

@Component
public class Functions {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  SentimentAnalyzer sentimentAnalyzer;

  @GemfireFunction(HA=true)
  public SentimentResult getSentiment(@Filter Set<String> personNames) {
    String personName = personNames.iterator().next();
    Collection<Post> posts = postRepository.findPosts(personName);
    String sentiment = sentimentAnalyzer.analyze(posts);
    return new SentimentResult(sentiment, personName);
    
  }
}
