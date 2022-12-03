package com.github.fa2bio.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dateRegister;
	
	@ManyToMany
	@JoinTable(name = "user_cluster", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "cluster_id"))
	private Set<Cluster> clusters = new HashSet<>();
	
	public boolean addGroup(Cluster cluster) {
		return getClusters().add(cluster);
	}
	
	public boolean removeGroup(Cluster cluster) {
		return getClusters().remove(cluster);
	}
	
	public boolean passwordCoincide(String password) {
		return getPassword().equals(password);
	}
	
	public boolean passwordDontCoincide(String password) {
		return !passwordCoincide(password);
	}
}
