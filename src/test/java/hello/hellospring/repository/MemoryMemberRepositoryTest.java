package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();


    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(result, member);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("jejeongmin");
        repository.save(member);
        Member result = repository.findByName(member.getName()).get();
        Assertions.assertEquals(result, member);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("jejeongmin2");
        repository.save(member);
        List<Member> result = repository.findAll();
        Assertions.assertEquals(result.size(), 1);
    }
}
