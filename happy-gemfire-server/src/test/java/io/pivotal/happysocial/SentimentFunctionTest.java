package io.pivotal.happysocial;

import io.pivotal.happysocial.functions.FunctionClient;
import io.pivotal.happysocial.model.SentimentResult;
import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.repositories.PostRepository;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gemstone.gemfire.cache.Region;

@ContextConfiguration(locations="/cache-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SentimentFunctionTest {
  @Autowired
  private FunctionClient functions;
  
  @Autowired
  private PostRepository posts;
  
  @Resource(name="sentimentWords")
  private Region<String, String> sentimentWords;
  
  @Test
  public void test() throws Exception {
    sentimentWords.put("like", "positive");
    sentimentWords.put("xml", "negative");
    sentimentWords.put("hate", "negative");
    
    Post post1 = new Post("Dan");
    post1.setText("I hate xml");
    posts.save(post1);
    
    Post post2 = new Post("Dan");
    post2.setText("I like toast");
    posts.save(post2);
    
    Post post3 = new Post("Jane");
    post3.setText("sdfkl sdfsdf");
    posts.save(post3);
    
    List<SentimentResult> result = functions.getSentiment(Collections.singleton("Dan"));
    
    Assert.assertEquals(1, result.size());
    Assert.assertEquals("negative", result.iterator().next().getSentiment());
  }

}
