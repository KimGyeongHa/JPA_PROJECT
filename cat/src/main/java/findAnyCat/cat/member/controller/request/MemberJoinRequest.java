package findAnyCat.cat.member.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record MemberJoinRequest(
        @NotEmpty(message = "이름을 입력해주세요.")
        String name,
        String city,
        String zipcode,
        String street
) {
    public MemberJoinRequest{

    }
}
