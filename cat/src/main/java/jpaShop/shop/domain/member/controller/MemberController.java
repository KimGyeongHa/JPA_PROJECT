package jpaShop.shop.domain.member.controller;

import jakarta.validation.Valid;
import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import jpaShop.shop.domain.member.service.MemberService;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.member.service.response.FindMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/members/new")
    public String joinMemberPage(Model model, MemberJoinRequest memberJoinRequest){
        model.addAttribute("memberForm",memberJoinRequest);
        return "/members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String joinMember(@Valid MemberJoinRequest memberJoinRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "members/createMemberForm";
        }

        memberService.saveMember(MemberDTO.of(memberJoinRequest));
        return "redirect:/members";
    }

    @RequestMapping("/members")
    public String getMemberList(Model model){
        model.addAttribute("members",memberService.findAllMembers().findMemberResponseList());
        return "members/memberList";
    }





}
