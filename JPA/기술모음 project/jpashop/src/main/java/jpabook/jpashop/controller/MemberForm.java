package jpabook.jpashop.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

	@NotEmpty(message = "이름이 중복됩니다.")
	private String name;
	
	private String city;
	private String street;
	private String zipcode;
}
