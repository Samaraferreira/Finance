package br.com.finance.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="transactions")
@NamedNativeQueries({
	@NamedNativeQuery(name="LIST_TRANSACTIONS", query = ""
			+ "SELECT id, title, description, amount, type, date FROM transactions", resultClass = Transaction.class),
	@NamedNativeQuery(name="INSERT_TRANSACTION", query = ""
			+ "INSERT INTO transactions (title, description, amount, type, date) values "
			+ "(:title, :description, :amount, :type, :date)"),
})
public class Transaction extends PanacheEntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", length = 250, nullable = false)
	private String title;
	
	@Column(name="description", length = 400, nullable = false)
	private String description;
	
	@Column(name="amount", nullable = false)
	private Float amount;
	
	@Column(name="type", nullable = false)
	private Boolean type; // true = incomes; false = expenses
	
	@Column(name="date", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime date;
	
	public Transaction() {
		super();
	}

	public Transaction(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
