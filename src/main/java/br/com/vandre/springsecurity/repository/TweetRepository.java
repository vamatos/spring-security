package br.com.vandre.springsecurity.repository;

import br.com.vandre.springsecurity.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
}
