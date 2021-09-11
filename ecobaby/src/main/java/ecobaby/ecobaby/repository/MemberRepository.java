package ecobaby.ecobaby.repository;

import ecobaby.ecobaby.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);
    List<Member> findAll();
}
