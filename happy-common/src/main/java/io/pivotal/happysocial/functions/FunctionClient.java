package io.pivotal.happysocial.functions;

import io.pivotal.happysocial.model.SentimentResult;

import java.util.List;
import java.util.Set;

import org.springframework.data.gemfire.function.annotation.Filter;
import org.springframework.data.gemfire.function.annotation.OnRegion;
import org.springframework.stereotype.Component;

@Component
@OnRegion(region = "posts")
public interface FunctionClient {
  public List<SentimentResult> getSentiment(@Filter Set<String> people);
}
