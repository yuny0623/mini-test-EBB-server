package ecobaby.ecobaby.service;

import ecobaby.ecobaby.domain.HTTPStatusCode;
import ecobaby.ecobaby.domain.LoginInfo;
import ecobaby.ecobaby.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    private HTTPStatusCode status;
    private final MemberService memberService;

    @Autowired
    public StatusService(MemberService memberService) {
        this.memberService = memberService;
    }

    public HTTPStatusCode generate_status_for_validation(boolean valid){
        if (valid){
            status = new HTTPStatusCode(200, true, "로그인이 성공적으로 이루어졌습니다.");
        }
        else{
            status = new HTTPStatusCode(400, false, "유효하지 않은 패스워드입니다.");
        }
        return status;
    }

    public HTTPStatusCode generate_status_for_duplicate(boolean valid, LoginInfo loginInfo){
        if (valid){
            Member member = new Member(loginInfo.getId(), loginInfo.getPassword(), 0);
            memberService.save(member); // 저장하기
            status = new HTTPStatusCode(200, true, "회원가입이 완료되었습니다.");
        }
        else{
            status = new HTTPStatusCode(400, false, "중복된 회원 아이디입니다.");
        }
        return status;
    }

    public HTTPStatusCode generate_status_for_update(boolean valid){
        if(valid){
            status = new HTTPStatusCode(200, true, "점수 갱신이 성공하였습니다.");
        }
        else{
            status = new HTTPStatusCode(400, false, "점수 갱신이 거부되었습니다.");
        }
        return status;
    }

}
