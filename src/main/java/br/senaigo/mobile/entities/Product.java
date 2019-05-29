package br.senaigo.mobile.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.ResourceSupport;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Product extends ResourceSupport {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long idProduct;
	private String name;
	private String description;
	private BigDecimal value;
}
