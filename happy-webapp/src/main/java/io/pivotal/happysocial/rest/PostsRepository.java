package io.pivotal.happysocial.rest;

import io.pivotal.happysocial.model.Post;
import io.pivotal.happysocial.model.PostId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "posts", path = "posts")
public interface PostsRepository extends CrudRepository<Post, PostId> {
}
