package jpabook.jpashop.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jpabook.jpashop.domain.Holygrail;
import jpabook.jpashop.repository.holygrailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j

public class HolygrailController {

	private final holygrailRepository holygrailRepository;
	
	@Transactional
	@GetMapping("/holygrail")
	public String form(Model model) {
		
		Holygrail hol = new Holygrail();
		hol.setText("aaaa");
		
		holygrailRepository.save(hol);
		
		holygrailRepository.findOne(hol.getId());
		
		model.addAttribute("hol", hol);
		log.info("��!!");
		return "holygrailForm";
		
	}
}
