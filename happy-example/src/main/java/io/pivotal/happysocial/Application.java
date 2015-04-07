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

import com.gemstone.gemfire.cache.query.FunctionDomainException;
import com.gemstone.gemfire.cache.query.NameResolutionException;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.TypeMismatchException;

public class Application {
  
  @Autowired
  PersonRepository people;
  
  @Autowired
  PostRepository posts;
  
  @Autowired
  FunctionClient functionClient;
  

  public static void main(String[] args) throws IOException, FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException {
    new Application().run();
  }
  
  public void run() {
    ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("cache-context.xml");
    
    people.save(new Person("SpongeBob", "SpongeBob SquarePants"));
    people.save(new Person("Neil", "Neil deGrasse Tyson"));
    
    posts.save(new Post("SpongeBob", "Run Mr. Krabs! Run like you’re not in a coma!"));
    posts.save(new Post("Spongebob", "Excuse me, sir, but you’re sitting on my body, which is also my face."));
    posts.save(new Post("Neil", "Space exploration is a force of nature unto itself that no other force in society can rival."));
    
    Collection<Post> neilsPosts = posts.findPosts("Neil");
    System.out.println(neilsPosts);
    
    Set<String> filter = new HashSet<String>();
    filter.add("Neil");
    filter.add("SpongeBob");
    List<MoodResult> result = functionClient.getMood(filter);
    System.out.println(result);
    
    

//    ClientCache cache = new ClientCacheFactory().addPoolLocator("locahost",
//        10334).create();
//
//    Region<String, Person> people = cache.<String, Person>createClientRegionFactory(PROXY)
//        .create("people");
//    
//    Region<PostId, Post> posts = cache.<PostId, Post>createClientRegionFactory(PROXY)
//        .create("people");
//    
//    people.put("Luke", new Person("Luke"));
//    people.put("Leia", new Person("Leia"));
//    people.put("Han", new Person("Han"));
//    
//    posts.put(new PostId("Han"), new Post("Han", "Uh, uh... negative, negative. We had a reactor leak here now. Give us a few minutes to lock it down. Large leak, very dangerous."));
//    posts.put(new PostId("Luke"), new Post("Luke", "If there's a bright center to the universe, you're on the planet that it's farthest from."));
//    posts.put(new PostId("Han"), new Post("Han", "Look, Your Worshipfulness, let's get one thing straight. I take orders from just one person: me. "));
//    
//    
//    SelectResults<Post> results = posts
//        .query("select * from posts where person='Han'");
//    System.out.println(results);
//
//    ResultCollector<MoodResult, MoodResult> rc = (ResultCollector<MoodResult, MoodResult>) FunctionService
//        .onRegion(posts).withFilter(Collections.singleton("Han"))
//        .execute("getMood");
//    MoodResult mood = rc.getResult();
//    System.out.println("Han is feeling " + mood);
  }
}
