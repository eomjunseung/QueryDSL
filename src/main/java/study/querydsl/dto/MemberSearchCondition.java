package study.querydsl.dto;

import lombok.Data;

@Data //화면에서 넘어오는 조건이 담김
public class MemberSearchCondition {
    //회원명, 팀명, 나이(ageGoe, ageLoe)
    private String username;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}
