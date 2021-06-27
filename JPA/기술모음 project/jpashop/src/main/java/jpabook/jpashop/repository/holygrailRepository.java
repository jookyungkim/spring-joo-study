package jpabook.jpashop.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Holygrail;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class holygrailRepository {

	private final EntityManager em;
	
	public void save(Holygrail holygrail) {
		em.persist(holygrail);
	}
	
	public Holygrail findOne(Long id)
	{
		return em.find(Holygrail.class, id);		
	}
}
