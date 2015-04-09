package io.pivotal.happysocial.functions;

import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.model.PostId;
import io.pivotal.happysocial.model.SentimentResult;
import io.pivotal.happysocial.repositories.PostRepository;
import io.pivotal.happysocial.sentiment.SentimentAnalyzer;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.function.annotation.Filter;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;
import org.springframework.data.gemfire.mapping.GemfireMappingContext;
import org.springframework.data.gemfire.repository.support.GemfireRepositoryFactory;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.Region;

@Component
public class Functions {
  @Autowired
  SentimentAnalyzer sentimentAnalyzer;

  @GemfireFunction(HA=true)
  public SentimentResult getSentiment(Region<PostId, Post> localPosts, @Filter Set<String> personNames) throws Exception {
    String personName = personNames.iterator().next();
    PostRepository postRepository = getRepostory(localPosts);
    Collection<Post> posts = postRepository.findPosts(personName);
    String sentiment = sentimentAnalyzer.analyze(posts);
    return new SentimentResult(sentiment, personName);
    
  }

  private PostRepository getRepostory(Region<PostId, Post> postRegion) {
    GemfireRepositoryFactory factory = new GemfireRepositoryFactory(
        Collections.singleton(postRegion), null);
    PostRepository postRepository = factory.getRepository(PostRepository.class);
    return postRepository;
  }
}
