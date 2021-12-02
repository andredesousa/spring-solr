package app.repository;

import app.entity.User;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SolrCrudRepository<User, Integer> { }
