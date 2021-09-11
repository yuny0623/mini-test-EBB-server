package ecobaby.ecobaby.controller;

import ecobaby.ecobaby.domain.HTTPStatusCode;
import ecobaby.ecobaby.domain.Member;
import ecobaby.ecobaby.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AndroidController {

    private final MemberRepository memberRepository;
    private HTTPStatusCode er;

    @Autowired
    public AndroidController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /*
        랭킹확인
     */
    @GetMapping("/main/ranking") //전체 랭킹 반환
    @ResponseBody
    public List<Member> rankingCheck(){
        return memberRepository.findAll();
    }
    
    /*
        로그인 
     */
    @PostMapping("/main/login")
    @ResponseBody
    public HTTPStatusCode login(@RequestBody Member member){ //로그인
        boolean valid = validationCheck(member.getId(), member.getPassword());
        if (valid){
            er = new HTTPStatusCode(200, true, "로그인이 성공적으로 이루어졌습니다.");
        }
        else{
            er = new HTTPStatusCode(400, false, "유효하지 않은 패스워드입니다.");
        }
        return er;
    }

    private boolean validationCheck(String id, String password) { //회원가입시 중복체크
        Member result = memberRepository.findById(id); //id가 있는지 체크하기
        if (result == null){ // id가 없으면?
            return false;                     //false 반환
        }else{ //id가 있으면?
            return result.getPassword().equals(password);
        }
    }
    /*
        회원가입 
     */
    @PostMapping("/main/signup")
    @ResponseBody
    public HTTPStatusCode signup(@RequestBody Member member){ //회원가입하기

        boolean duplicate = dupilcateCheck(member.getId());
        if (duplicate){
            er = new HTTPStatusCode(400, false, "중복된 회원 아이디입니다.");
        }
        else{
            memberRepository.save(member); // 저장하기
            er = new HTTPStatusCode(200, true, "회원가입이 완료되었습니다.");
        }
        return er;
    }
    private boolean dupilcateCheck(String id) { //회원가입시 중복체크
        Member result = memberRepository.findById(id); //id가 있는지 체크하기
        return result == null;
    }

    /*
        점수 업데이트
     */
    @GetMapping("/main/game/update_score")
    @ResponseBody
    public Member score_update(@RequestBody Member member){
        memberRepository.save(member);
        return member;
    }
}
