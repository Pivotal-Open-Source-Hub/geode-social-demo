package io.pivotal.happysocial;

import io.pivotal.happysocial.model.Person;

import java.net.InetSocketAddress;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.client.ClientCacheFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.client.PoolFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.cache.client.Pool;

@SpringBootApplication
@EnableGemfireRepositories
public class Application {

  @Bean
  ClientCacheFactoryBean clientCacheFactoryBean(final Pool pool) {
    return new ClientCacheFactoryBean() {
      {
        setPool(pool);
      }
    };
  }

  @Bean
  PoolFactoryBean poolFactoryBean() {
    return new PoolFactoryBean() {
      {
        setLocators(Arrays.asList(new InetSocketAddress(10334)));
      }
    };
  }

  @Bean
  ClientRegionFactoryBean<String, Person> clientRegionFactory(
      final GemFireCache cache) {
    return new ClientRegionFactoryBean<String, Person>() {
      {
        setCache(cache);
        setName("people");
      }
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
