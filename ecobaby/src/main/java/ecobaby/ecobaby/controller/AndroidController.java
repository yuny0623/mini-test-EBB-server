package ecobaby.ecobaby.controller;

import ecobaby.ecobaby.domain.Member;
import ecobaby.ecobaby.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AndroidController {

    private MemberRepository memberRepository;

    @Autowired
    public AndroidController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @RequestMapping("/main/ranking") //랭킹 반환하기
    public List<Member> rankingCheck(){
        return memberRepository.findAll();
    }

    @RequestMapping(value = "/main/signup", method = RequestMethod.POST)
    @ResponseBody
    public boolean signin(@RequestBody Member member){ //회원가입
        boolean valid = validationCheck(member.getId(), member.getPassword());
        if (valid){
            return true; //우효하면 true반환
        }
        else{
            return false;
        }
    }
    // 회원가입은
    // 1. 해당 id가 있는지 체크하고
    // 2. id와 password가 맞는지를 체크하면 됨/
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
    //--------------------------------------------------------------------------

    @RequestMapping(value = "/main/signup", method = RequestMethod.POST)
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
}
