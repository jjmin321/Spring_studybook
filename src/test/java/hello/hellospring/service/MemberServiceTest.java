package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemberServiceTest {
    MemberService memberService = new MemberService();
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @Test
    public void joinTest() {
        Member member = new Member();
        member.setName("First Member");
        Long savedId = memberService.join(member);
        Member result = memberService.findOne(savedId).get();
        Assertions.assertThat(result).isEqualTo(member);
        memoryMemberRepository.clearStore();
    }

    @Test
    public void validateDuplicateMemberTest() {
        Member member1 = new Member();
        member1.setName("First Member");
        Member member2 = new Member();
        member2.setName("First Member");
        memberService.join(member1);
        try {
            memberService.join(member2);
        }catch(IllegalStateException e) {
            System.out.println("에러 발생 성공");
        }
        memoryMemberRepository.clearStore();
    }

    @Test
    public void findMembers() {
        Member member = new Member();
        member.setName("Jejeongmin");
        List<Member> result = memberService.findMembers();
        if (result == null){
            throw new IllegalStateException("결과가 없습니다.");
        }
    }

    @Test
    public void findOne() {
        Member member = new Member();
        member.setName("spring");
        Optional<Member> result = memberService.findOne(member.getId());
        result.ifPresent(m -> {
            throw new IllegalStateException("결과가 없습니다");
        });
    }
}
