package io.pivotal.happysocial;

import io.pivotal.happysocial.functions.FunctionClient;
import io.pivotal.happysocial.model.Person;
import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.model.SentimentResult;
import io.pivotal.happysocial.repositories.PersonRepository;
import io.pivotal.happysocial.repositories.PostRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Application {
  
  @Autowired
  PersonRepository people;
  
  @Autowired
  PostRepository posts;
  
  @Autowired
  FunctionClient functionClient;
  

  public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cache-context.xml");
    Application application = context.getBean(Application.class);
    if(args.length > 0 && args[0].equals("analyze")) {
      application.analyzeSentiments();
    } else {
      application.submitPosts();
    }
  }
  
  private void analyzeSentiments() throws InterruptedException, IOException {

    List<String> names = loadNames();
    Random random = new Random();
    while(true) {
      Set<String> filter = new HashSet<String>();
      filter.add(names.get(random.nextInt(names.size())));
      
      List<SentimentResult> result = functionClient.getSentiment(filter);
      result.forEach(r -> System.out.println(r.getPersonName() + "\t is being " + r.getSentiment()));
      
      Thread.sleep(1000);
      
      if(System.in.available() > 0) {
        System.out.println("Exiting...");
        break;
      }
    }
    
  }

  public void submitPosts() throws IOException, InterruptedException {
    
    List<String> names = loadNames();
    
    for(String name: names) {
      people.save(new Person(name));
    }
    

    Random random = new Random();
    Reader in = new FileReader(new File("data", "/Sentiment Analysis Dataset.csv"));
    // Some of the data has comments. Don't use any comment characters or escape
    // characters
    CSVParser tweets = CSVFormat.newFormat(',').withHeader().parse(in);
    int tweetCount = 0;
    for(CSVRecord tweet : tweets) {
      String name = names.get(random.nextInt(names.size()));
      String post = tweet.get("SentimentText");
      posts.save(new Post(name, post));
      tweetCount++;
      System.out.println(name + ": " + post);
      
      Thread.sleep(1000);
      if(System.in.available() > 0) {
        System.out.println("Exiting...");
        System.out.println("Posted " + tweetCount + " tweets");
        break;
      }
    }
  }

  private List<String> loadNames() throws IOException {
    
    Reader in = new FileReader(new File("data", "/names.csv"));
    CSVParser names = CSVFormat.DEFAULT.parse(in);
    
    List<String> results = names.getRecords().stream()
        .map(r -> r.get(0))
        .collect(Collectors.toList());
    
    return results;
  }
}
