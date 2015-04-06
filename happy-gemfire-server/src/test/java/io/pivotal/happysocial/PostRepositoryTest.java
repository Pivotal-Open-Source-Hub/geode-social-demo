package io.pivotal.happysocial;

import static org.junit.Assert.*;

import java.util.Collection;

import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.repositories.PostRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations="/cache-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PostRepositoryTest {

  @Autowired
  private PostRepository posts;
  
  @Test
  public void test() {
    Post post1 = new Post("Dan");
    post1.setText("I hate xml");
    posts.save(post1);
    
    Post post2 = new Post("Dan");
    post2.setText("I like toast");
    posts.save(post2);
    
    Post post3 = new Post("Jane");
    post3.setText("sdfkl sdfsdf");
    posts.save(post3);
  
    Post result = posts.findOne(post1.getId());
    assertEquals("I hate xml", post1.getText());
    
    Collection<Post> results = posts.findPosts("Dan");
    assertEquals(2, results.size());
  }

}
