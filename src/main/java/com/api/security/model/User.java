package com.api.security.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(unique = true, length = 20)
	private String name;
	
	@Column(length = 60)
	private String password;
	
	@Column(unique = true, length = 100)
	private String email;
	
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY)
	//@JoinTable(name = "users_to_roles", joinColumns = @JoinColumn(referencedColumnName = "user"))   para cambiar los nombre de la tabla intermedia y campos clave
	//@JoinTable(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "roleId"})})  //Aseguramos que no existan claves repetidas en la tabla intermedia
	private List<Role> roles;

	
	//************
	
}
