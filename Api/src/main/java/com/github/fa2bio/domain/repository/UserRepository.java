package com.github.fa2bio.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.User;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
