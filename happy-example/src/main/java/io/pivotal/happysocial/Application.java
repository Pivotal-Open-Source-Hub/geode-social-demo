package io.pivotal.happysocial;

import io.pivotal.happysocial.functions.FunctionClient;
import io.pivotal.happysocial.model.MoodResult;
import io.pivotal.happysocial.model.Person;
import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.repositories.PersonRepository;
import io.pivotal.happysocial.repositories.PostRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.query.FunctionDomainException;
import com.gemstone.gemfire.cache.query.NameResolutionException;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.TypeMismatchException;

@Component
public class Application {
  
  @Autowired
  PersonRepository people;
  
  @Autowired
  PostRepository posts;
  
  @Autowired
  FunctionClient functionClient;
  

  public static void main(String[] args) throws IOException, FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cache-context.xml");
    Application application = context.getBean(Application.class);
    application.run();
  }
  
  public void run() {
    people.save(new Person("SpongeBob", "SpongeBob SquarePants"));
    people.save(new Person("Neil", "Neil deGrasse Tyson"));
    people.save(new Person("Ackbar", "Admiral Ackbar"));
    
    posts.save(new Post("SpongeBob", "Run Mr. Krabs! Run like you’re not in a coma!"));
    posts.save(new Post("Spongebob", "Excuse me, sir, but you’re sitting on my body, which is also my face."));
    posts.save(new Post("Neil", "Space exploration is a force of nature unto itself that no other force in society can rival."));
    posts.save(new Post("Ackbar", "It's a trap!"));
    
    Collection<Post> neilsPosts = posts.findPosts("Neil");
    System.out.println(neilsPosts);
    
    Set<String> filter = new HashSet<String>();
    filter.add("Ackbar");
    List<MoodResult> result = functionClient.getMood(filter);
    System.out.println("Admiral Ackbar is being " + result.get(0).getMood());
  }
}
