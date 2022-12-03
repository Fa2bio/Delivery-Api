package com.github.fa2bio.domain.repository;

import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Cluster;

@Repository
public interface ClusterRepository extends CustomJpaRepository<Cluster, Long>{

}
