package jpaShop.shop.api.member;


import jakarta.validation.Valid;
import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import jpaShop.shop.domain.member.controller.request.UpdateMemberRequest;
import jpaShop.shop.domain.member.service.MemberService;
import jpaShop.shop.domain.member.service.request.FindMemberRequest;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.member.service.request.UpdateMemberDTO;
import jpaShop.shop.domain.member.service.response.MemberJoinResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberJoinResponse> memberJoin(
           @Valid @RequestBody MemberJoinRequest memberJoinRequest
    ){
        FindMemberRequest findMemberRequest = FindMemberRequest.of(memberService.saveMember(MemberDTO.of(memberJoinRequest)));
        return ResponseEntity.ok(memberService.findMember(findMemberRequest));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(
            @PathVariable("memberId") Long memberId,
            @Valid @RequestBody UpdateMemberRequest updateMemberRequest
    ){
        memberService.updateMember(memberId, UpdateMemberDTO.of(updateMemberRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }








}
