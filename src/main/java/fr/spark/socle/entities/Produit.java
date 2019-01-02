package fr.spark.socle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produit {

	@Id @GeneratedValue
	private Long id;
	private String produitName;
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Produit(String produitName) {
		super();
		this.produitName = produitName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProduitName() {
		return produitName;
	}
	public void setProduitName(String produitName) {
		this.produitName = produitName;
	}
	
}
