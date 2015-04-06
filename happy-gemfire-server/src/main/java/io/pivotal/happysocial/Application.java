package io.pivotal.happysocial;

import io.pivotal.happysocial.model.Person;
import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.model.PostId;
import io.pivotal.happysocial.mood.MoodScorer;
import io.pivotal.happysocial.mood.MoodScorerImpl;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.PartitionedRegionFactoryBean;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionShortcut;
import com.gemstone.gemfire.distributed.internal.DistributionConfig;

@Configuration
@EnableGemfireRepositories
public class Application implements CommandLineRunner {
/*
  @Bean
  CacheFactoryBean cacheFactoryBean() {
    return new CacheFactoryBean() {{
      Properties props = new Properties();
      props.setProperty(DistributionConfig.MCAST_PORT_NAME, "0");
      setProperties(props);
    }};
  }

  @Bean
  PartitionedRegionFactoryBean<UUID, Person> personRegionFactory(
      final GemFireCache cache) {
    return new PartitionedRegionFactoryBean<UUID, Person>() {
      {
        setCache(cache);
        setShortcut(RegionShortcut.PARTITION_REDUNDANT);
        setName("people");
        setClose(false);
      }
    };
  }

  @Bean
  PartitionedRegionFactoryBean<PostId, Post> postRegionFactory(
      final GemFireCache cache) {
    return new PartitionedRegionFactoryBean<PostId, Post>() {
      {
        setCache(cache);
        setShortcut(RegionShortcut.PARTITION_REDUNDANT);
        setName("posts");
        setClose(false);
      }
    };
  }

  @Bean
  ReplicatedRegionFactoryBean<String, String> moodWordsFactory (
      final GemFireCache cache) {
    return new ReplicatedRegionFactoryBean<String, String>() {
      {
        setCache(cache);
        setShortcut(RegionShortcut.REPLICATE);
        setName("moodWords");
        setClose(false);
      }
    };
  }
  
  @Bean
  Region<String, String> moodWords(GemFireCache cache) throws Exception {
    return moodWordsFactory(cache).getObject();
  }

  @Bean
  MoodScorer moodScorer(GemFireCache cache) throws Exception {
    return new MoodScorerImpl(moodWords(cache));
  }
*/
  @Autowired
  Cache cache;

  @Override
  public void run(String... strings) throws Exception {
    while (!cache.isClosed()) {
      Thread.sleep(100);
    }
  }

  public static void main(String[] args) throws IOException {
    SpringApplication.run(Application.class, args);
  }
}
