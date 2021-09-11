package ecobaby.ecobaby.controller;

import ecobaby.ecobaby.domain.Member;
import ecobaby.ecobaby.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AndroidController {

    private MemberRepository memberRepository;

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
    public boolean login(@RequestBody Member member){ //로그인
        boolean valid = validationCheck(member.getId(), member.getPassword());
        if (valid){
            return true;  //유효하면 true반환
        }
        else{
            return false; //유효하지 않으면 false반환
        }
    }
    private boolean validationCheck(Long id, String password) { //회원가입시 중복체크
        Member result = memberRepository.findById(id); //id가 있는지 체크하기
        if (result == null){ // id가 없으면?
            return false;                     //false 반환
        }else{ //id가 있으면?
            if (result.getPassword().equals(password)){
                return true;
            }
            else{
                return false;
            }
        }
    }
    /*
        회원가입 
     */
    @PostMapping("/main/signup")
    @ResponseBody
    public boolean signup(@RequestBody Member member){ //회원가입하기
        boolean valid = dupilcateCheck(member.getId());
        if (valid){
            return false; //중복이면 false 반환
        }
        else{
            memberRepository.save(member); // 저장하기
            return true; //중복 아니면 true 반환
        }
    }
    private boolean dupilcateCheck(Long id) { //회원가입시 중복체크
        Member result = memberRepository.findById(id); //id가 있는지 체크하기
        if (result == null){ //같은 id가 있다면?
            return true;                     //false 반환
        }else{
            return false;
        }
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
