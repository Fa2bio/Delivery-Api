package com.github.fa2bio.domain.repository;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Permission;

@Repository
public interface PermissionRepository extends CustomJpaRepository<Permission, Long> {
	
}
