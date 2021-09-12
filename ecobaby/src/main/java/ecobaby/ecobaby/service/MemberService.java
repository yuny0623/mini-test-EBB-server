package ecobaby.ecobaby.service;

import ecobaby.ecobaby.domain.Member;
import ecobaby.ecobaby.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /*
        로그인 유효성 검사
     */
    public boolean loginValidationCheck(String id, String password) { //회원가입시 중복체크
        Member result = memberRepository.findById(id); //id가 있는지 체크하기
        if (result == null){ // id가 없으면?
            return false;                     //false 반환
        }else{ //id가 있으면?
            return result.getPassword().equals(password);
        }
    }
    /*/
        회원가입 시 id 중복체크
     */
    public boolean signupValidationCheck(String id) { //회원가입시 중복체크
        Member result = memberRepository.findById(id); //id가 있는지 체크하기
        return result == null; // 해당 회원이 없으면 참
    }

    /*
        모든 회원 정보 반환
     */
    public List<Member> get_ranking() {
        List<Member> members = memberRepository.findAll();
        Collections.sort(members, Collections.reverseOrder());
        return  members;
    }

    /*
        멤버 객체 저장
     */
    public void save(Member member) {
        memberRepository.save(member);
    }
}
