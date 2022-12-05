 package com.github.fa2bio.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;

import com.github.fa2bio.domain.exception.BusinessException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Orderr {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uuiCode;
	
	private BigDecimal subtotal;
	private BigDecimal rateShipping; 
	private BigDecimal totalAmount;

	@Embedded
	private Address deliveryAddress;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.CREATED;
	
	@CreationTimestamp
	private OffsetDateTime creationDate;
	private OffsetDateTime confirmationDate;
	private OffsetDateTime cancellationDate;
	private OffsetDateTime deliveryDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "user_client_id", nullable = false)
	private User client;
	
	@OneToMany(mappedBy = "orderr", cascade = CascadeType.ALL)
	private List<ItemOrderr> items = new ArrayList<>();
	
	public void calculateAmount() {
		getItems().forEach(ItemOrderr::calculateTotalPrice);
		
		this.subtotal = getItems().stream()
			.map(item -> item.getTotalPrice())
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		this.totalAmount = this.subtotal.add(this.rateShipping);
	}

	public void confirm() {
		setStatus(OrderStatus.CONFIRMED);
		setConfirmationDate(OffsetDateTime.now());
	}
	
	public void deliver() {
		setStatus(OrderStatus.DELIVERED);
		setDeliveryDate(OffsetDateTime.now());
	}
	
	public void cancel() {
		setStatus(OrderStatus.CANCELED);
		setCancellationDate(OffsetDateTime.now());
	}
	
	
	public boolean canBeConfirmed() {
		return getStatus().canChangeTo(OrderStatus.CONFIRMED);
	}
	
	public boolean canBeDeliver() {
		return getStatus().canChangeTo(OrderStatus.DELIVERED);
	}
	
	public boolean canBeCancel() {
		return getStatus().canChangeTo(OrderStatus.CANCELED);
	}
	
	private void setStatus(OrderStatus newStatus) {
		if(getStatus().cannotChangeTo(newStatus)) {
			throw new BusinessException(String.format("Order status %s cannot be changed from %s to %s",
					getUuiCode(),
					getStatus().getDescription(),
					newStatus.getDescription()));
		}
		
		this.status = newStatus;
	}
	
	@PrePersist
	private void generateCode() {
		setUuiCode(UUID.randomUUID().toString());
	}
}
