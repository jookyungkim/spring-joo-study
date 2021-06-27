package study.jpashop.dto;

import lombok.Data;

@Data
public class MemberSerchCondition {

    // 회원명 팀명, 나이(ageGoe, ageLog)

    private String username;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}
