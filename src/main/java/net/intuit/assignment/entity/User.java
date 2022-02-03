package net.intuit.assignment.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "USER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4408418647685225829L;
	@Id
	private String uid;
	private String name;
	private String email;
	private boolean isEmailVerified;
	private String issuer;
	private String picture;

}
