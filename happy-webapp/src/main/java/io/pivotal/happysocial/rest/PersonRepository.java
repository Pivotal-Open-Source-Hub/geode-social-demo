package io.pivotal.happysocial.rest;

import io.pivotal.happysocial.model.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends CrudRepository<Person, String> {

  Iterable<Person> findByName(String name);
}
