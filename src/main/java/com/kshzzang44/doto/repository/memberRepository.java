package com.kshzzang44.doto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshzzang44.doto.entity.MemberInfoEntity;

@Repository
public interface memberRepository extends JpaRepository<MemberInfoEntity, Long>{
    public Integer countByEmail(String email);
    public MemberInfoEntity findByEmailAndPwd(String email, String pwd);
}
