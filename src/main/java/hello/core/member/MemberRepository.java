package hello.core.member;

public interface MemberRepository {

    void save (Member member); //저장
    Member findById(Long memberId); //찾기
}
