package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.ClusterNotFoundException;
import com.github.fa2bio.domain.model.Cluster;
import com.github.fa2bio.domain.model.Permission;
import com.github.fa2bio.domain.repository.ClusterRepository;

@Service
public class ClusterService {
	
	private static final String MSG_CLUSTER_IN_USE
	= "The cluster with code %d cannot be removed because it is in use";

	@Autowired
	private ClusterRepository clusterRepository;
	
	@Autowired
	private PermissionService permissionService;
	
	@Transactional
	public Cluster save(Cluster cluster) {
		return clusterRepository.save(cluster);
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			clusterRepository.deleteById(id);
			clusterRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ClusterNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_CLUSTER_IN_USE, id));
		}
	}
	
	@Transactional
	public void associatePermission(Long clusterId, Long permissionId) {
		Cluster cluster = fetchOrFail(clusterId);
		Permission permission = permissionService.fetchOrFail(permissionId);
		cluster.adicionarPermissao(permission);
	}
	
	@Transactional
	public void disassociatePermission(Long clusterId, Long permissionId) {
		Cluster cluster = fetchOrFail(clusterId);
		Permission permission = permissionService.fetchOrFail(permissionId);
		cluster.removerPermissao(permission);
	}
	
	public Cluster fetchOrFail(Long clusterId) {
		return clusterRepository.findById(clusterId)
			.orElseThrow(() -> new ClusterNotFoundException(clusterId));
	}
}
