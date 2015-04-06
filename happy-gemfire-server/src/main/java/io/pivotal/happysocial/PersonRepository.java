package io.pivotal.happysocial;

import io.pivotal.happysocial.model.Person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {

}
