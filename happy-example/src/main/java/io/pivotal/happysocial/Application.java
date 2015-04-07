package io.pivotal.happysocial;

import static com.gemstone.gemfire.cache.client.ClientRegionShortcut.PROXY;
import io.pivotal.happysocial.model.MoodResult;
import io.pivotal.happysocial.model.Person;
import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.model.PostId;

import java.io.IOException;
import java.util.Collections;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import com.gemstone.gemfire.cache.query.FunctionDomainException;
import com.gemstone.gemfire.cache.query.NameResolutionException;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.SelectResults;
import com.gemstone.gemfire.cache.query.TypeMismatchException;

public class Application {

  public static void main(String[] args) throws IOException, FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException {

    ClientCache cache = new ClientCacheFactory().addPoolLocator("locahost",
        10334).create();

    Region<String, Person> people = cache.<String, Person>createClientRegionFactory(PROXY)
        .create("people");
    
    Region<PostId, Post> posts = cache.<PostId, Post>createClientRegionFactory(PROXY)
        .create("people");
    
    people.put("Luke", new Person("Luke"));
    people.put("Leia", new Person("Leia"));
    people.put("Han", new Person("Han"));
    
    posts.put(new PostId("Han"), new Post("Han", "Uh, uh... negative, negative. We had a reactor leak here now. Give us a few minutes to lock it down. Large leak, very dangerous."));
    posts.put(new PostId("Luke"), new Post("Luke", "If there's a bright center to the universe, you're on the planet that it's farthest from."));
    posts.put(new PostId("Han"), new Post("Han", "Look, Your Worshipfulness, let's get one thing straight. I take orders from just one person: me. "));
    
    
    SelectResults<Post> results = posts
        .query("select * from posts where person='Han'");
    System.out.println(results);

    ResultCollector<MoodResult, MoodResult> rc = (ResultCollector<MoodResult, MoodResult>) FunctionService
        .onRegion(posts).withFilter(Collections.singleton("Han"))
        .execute("getMood");
    MoodResult mood = rc.getResult();
    System.out.println("Han is feeling " + mood);
  }
}
