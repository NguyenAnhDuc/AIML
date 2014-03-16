package fti.aiml.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fti.aiml.domail.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}
