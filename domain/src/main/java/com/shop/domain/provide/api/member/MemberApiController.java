package com.shop.domain.provide.api.member;


import com.shop.domain.provide.domain.member.controller.request.MemberJoinRequest;
import com.shop.domain.provide.domain.member.controller.request.UpdateMemberRequest;
import com.shop.domain.provide.domain.member.service.request.FindMemberRequest;
import com.shop.domain.provide.domain.member.service.request.MemberDTO;
import com.shop.domain.provide.domain.member.service.request.UpdateMemberDTO;
import com.shop.domain.provide.domain.member.service.response.FindMemberResponse;
import jakarta.validation.Valid;
import com.shop.domain.provide.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<FindMemberResponse> memberJoin(
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
