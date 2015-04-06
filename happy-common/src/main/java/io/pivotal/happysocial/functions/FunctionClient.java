package io.pivotal.happysocial.functions;

import io.pivotal.happysocial.model.MoodResult;

import java.util.List;
import java.util.Set;

import org.springframework.data.gemfire.function.annotation.Filter;
import org.springframework.data.gemfire.function.annotation.OnRegion;

@OnRegion(region = "posts")
public interface FunctionClient {
  public List<MoodResult> getMood(@Filter Set<String> people);
}
