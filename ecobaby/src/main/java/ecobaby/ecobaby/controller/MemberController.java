package ecobaby.ecobaby.controller;

import ecobaby.ecobaby.domain.HTTPStatusCode;
import ecobaby.ecobaby.domain.LoginInfo;
import ecobaby.ecobaby.domain.Member;
import ecobaby.ecobaby.service.MemberService;
import ecobaby.ecobaby.service.StatusService;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MemberController {

    private final StatusService statusService;
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService, StatusService statusService) {
        this.memberService = memberService;
        this.statusService = statusService;
    }
    /*
        랭킹확인
     */
    @GetMapping("/main/ranking") //전체 랭킹 반환
    @ResponseBody
    public List<Member> rankingCheck(){
        return memberService.get_ranking();
    }
    
    /*
        로그인 
     */
    @GetMapping("/main/login")
    @ResponseBody
    public HTTPStatusCode login(@RequestBody LoginInfo loginInfo){ //로그인
        boolean valid = memberService.loginValidationCheck(loginInfo.getId(), loginInfo.getPassword());
        return statusService.generate_status_for_validation(valid);
    }
    /*
        회원가입 
     */
    @GetMapping("/main/signup")
    @ResponseBody
    public HTTPStatusCode signup(@RequestBody LoginInfo loginInfo){ //회원가입하기
        boolean valid = memberService.signupValidationCheck(loginInfo.getId());
        return statusService.generate_status_for_duplicate(valid, loginInfo);
    }

    /*
        점수 업데이트
     */
    @GetMapping("/main/game/update_score")
    public void score_update(@RequestBody Member member){
        memberService.save(member);
    }
}
