package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Holygrail {

	@Id @GeneratedValue
	@Column(name = "holygrail_id")
	private Long id;
	
	private String text;
}
