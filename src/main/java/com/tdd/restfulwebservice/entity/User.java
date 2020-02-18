package com.tdd.restfulwebservice.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@ApiModel(description = "User details")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "Name should have at least 2 chacaracters")
	//@ApiModelProperty(notes = "Name should have at least 2 chacaracters")
	private String name;

	@Past
	//@ApiModelProperty(notes = "Birth date should be in the past")
	private LocalDate birthDate;

	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
