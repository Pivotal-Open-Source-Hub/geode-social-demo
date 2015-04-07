package io.pivotal.happysocial.rest;

import io.pivotal.happysocial.functions.FunctionClient;
import io.pivotal.happysocial.model.MoodResult;
import io.pivotal.happysocial.model.Post;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HappyPeopleController {

  @Autowired
  private FunctionClient functions;
  
  @Autowired
  PersonRepository personRepository;
  
  @Autowired
  PostsRepository postsRepository;

  @RequestMapping("/mood")
  public MoodResult getMood(
      @RequestParam(value = "name") String name) {
    return functions.getMood(Collections.singleton(name)).iterator().next();
  }
  
  @RequestMapping("/newpost")
  public void newPost(
      @RequestParam(value = "person") String name, @RequestParam(value="text") String text) {
    Post post = new Post(name, text);
    postsRepository.save(post);
  }
}
