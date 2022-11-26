package com.github.fa2bio.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Usuario;

@Repository
public interface UserRepository extends CustomJpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
}
