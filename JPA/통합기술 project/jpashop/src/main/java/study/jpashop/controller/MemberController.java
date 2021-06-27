package study.jpashop.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.jpashop.domin.Address;
import study.jpashop.domin.Member;
import study.jpashop.form.MemberForm;
import study.jpashop.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String creatForm(Model model) {

        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
}

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if(result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address();
        address.setCity(form.getCity());
        address.setStreet(form.getStreet());
        address.setZipcode(form.getZipcode());

        Member member = Member.builder()
                .name(form.getName())
                .address(address)
                .build();



        memberService.save(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String LIst(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
