package io.pivotal.happysocial.mood;

import io.pivotal.happysocial.model.Post;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gemstone.gemfire.cache.Region;

public class MoodScorerImpl implements MoodScorer {

  private Map<String, String> moodWords;

  public MoodScorerImpl(Map<String, String> moodWords) {
    this.moodWords = moodWords;
  }

  @Override
  public String computeMood(Collection<Post> posts) {
    
    List<String> words = posts
    .stream()
    .flatMap(p -> Arrays.asList(p.getText().split("\\W")).stream()).collect(Collectors.toList());
    Map<String, Long> counts = posts
        .stream()
        .flatMap(p -> Arrays.asList(p.getText().split("\\W")).stream())
        .filter(s -> moodWords.containsKey(s))
        .collect(
            Collectors.groupingBy(s -> moodWords.get(s), Collectors.counting()));

    Optional<Entry<String, Long>> mood = counts.entrySet().stream()
        .sorted(Comparator.comparingLong((Entry<String, Long> e) ->  e.getValue()).reversed())
        .findFirst();

    if (mood.isPresent()) {
      return mood.get().getKey();
    } else {
      return "neutral";
    }
  }
}
