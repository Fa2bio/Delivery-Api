package com.github.fa2bio.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "rate_shipping", nullable = false)
	private BigDecimal rateShipping;
	
	@ManyToOne
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;
	
	@Embedded
	private Address address;
	
	private Boolean active = Boolean.TRUE;
	
	private Boolean open = Boolean.TRUE;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime creationDate;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime UpdateDate;
	
	@ManyToMany
	@JoinTable(name = "restaurant_payment_method",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private Set<PaymentMethod> paymentMethods = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "restaurant_user_responsible",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> responsibles = new HashSet<>();
	
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();
	
	public boolean addPaymentMethod(PaymentMethod paymentMethod) {
		return getPaymentMethods().add(paymentMethod);
	}
	
	public boolean deletePaymentMethod(PaymentMethod paymentMethod) {
		return getPaymentMethods().remove(paymentMethod);
	}
	
	public boolean acceptPaymentForm(PaymentMethod paymentMethod) {
		return getPaymentMethods().contains(paymentMethod);
	}
	
	public boolean dontAcceptPaymentForm(PaymentMethod paymentMethod) {
		return !acceptPaymentForm(paymentMethod);
	}
	
	public boolean addResponsible(User user) {
		return getResponsibles().add(user);
	}
	
	public boolean deleteResponsible(User user) {
		return getResponsibles().remove(user);
	}
	
	public void open() {
		setOpen(true);
	}
	
	public void close() {
		setOpen(false);
	}
	
	public void activate() {
		setActive(true);
	}
	
	public void inactivate() {
		setActive(false);
	}
	
	public boolean isOpen() {
		return this.open;
	}
	
	public boolean isClose() {
		return !isOpen();
	}
	
	public boolean isActivate() {
		return this.active;
	}
	
	public boolean isInactivate() {
		return !isActivate();
	}
	
	public boolean openAllowed() {
		return isActivate() && isClose();
	}
	
	public boolean closeAllowed() {
		return isOpen();
	}
	
	public boolean activateAllowed() {
		return isInactivate();
	}
	
	public boolean inactivateAllowed() {
		return isActivate();
	}
}
