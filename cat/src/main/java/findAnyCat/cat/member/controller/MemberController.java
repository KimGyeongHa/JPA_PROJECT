package findAnyCat.cat.member.controller;

import findAnyCat.cat.embbed.Address;
import findAnyCat.cat.member.Member;
import findAnyCat.cat.member.controller.request.MemberJoinRequest;
import findAnyCat.cat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/members/new")
    public String joinMemberPage(Model model){
        model.addAttribute("memberForm",new MemberJoinRequest("","","",""));
        return "/members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String joinMember(@Validated MemberJoinRequest memberJoinRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setName(memberJoinRequest.name());
        member.setAddress(new Address(memberJoinRequest.city(),memberJoinRequest.street(),memberJoinRequest.zipcode()));
        memberService.saveMember(member);
        return "redirect:/";
    }

    @RequestMapping("/members")
    public String getMemberList(Model model){
        model.addAttribute("members",memberService.findMembers());
        return "members/memberList";
    }





}
