package io.pivotal.happysocial.rest;

import java.util.Collections;

import io.pivotal.happysocial.functions.FunctionClient;
import io.pivotal.happysocial.model.MoodResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HappyPeopleController {

  @Autowired
  private FunctionClient functions;

  @RequestMapping("/mood")
  public MoodResult greeting(
      @RequestParam(value = "name", defaultValue = "Dan") String name) {
    return functions.getMood(Collections.singleton(name)).iterator().next();
  }
}
