package io.pivotal.happysocial.sentiment;

import io.pivotal.happysocial.model.Post;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class SentimentAnalyzerImpl implements SentimentAnalyzer {

  private Map<String, String> sentimentWords;

  public SentimentAnalyzerImpl(Map<String, String> sentimentWords) {
    this.sentimentWords = sentimentWords;
  }

  @Override
  public String analyze(Collection<Post> posts) {
    
    List<String> words = posts
    .stream()
    .flatMap(p -> Arrays.asList(p.getText().split("\\W")).stream()).collect(Collectors.toList());
    Map<String, Long> counts = posts
        .stream()
        .flatMap(p -> Arrays.asList(p.getText().split("\\W")).stream())
        .filter(s -> sentimentWords.containsKey(s))
        .collect(
            Collectors.groupingBy(s -> sentimentWords.get(s), Collectors.counting()));

    Optional<Entry<String, Long>> sentiment = counts.entrySet().stream()
        .sorted(Comparator.comparingLong((Entry<String, Long> e) ->  e.getValue()).reversed())
        .findFirst();

    if (sentiment.isPresent()) {
      return sentiment.get().getKey();
    } else {
      return "neutral";
    }
  }
}
