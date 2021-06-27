package study.jpashop.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.jpashop.domin.Member;
import study.jpashop.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();
        member.changeName(request.getName());

        Long id = memberService.save(member);
        return new CreateMemberResponse(id);

    }

    @GetMapping("api/v2/members")
    public Result memberV2() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @PutMapping("api/v2/members/{id}")
    public Result updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        Optional<Member> findMember = memberService.findById(id);

        return new Result(1, findMember);
    }

    @Data
    static class CreateMemberResponse {

        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {

        @NotEmpty
        private String name;

    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T Data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;

    }

    @Data
    @AllArgsConstructor
    static class UpdataMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberRequest {
        private String name;
    }
}
