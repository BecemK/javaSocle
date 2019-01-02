package fr.spark.socle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spark.socle.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
public User findByUserName(String userName);
}
