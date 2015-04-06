package io.pivotal.happysocial.repositories;

import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.model.PostId;

import java.util.Collection;

import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;

public interface PostRepository extends GemfireRepository<Post, PostId> {

  @Query("select * from /posts where id.person=$1")
  public Collection<Post> findPosts(String personName);

}
