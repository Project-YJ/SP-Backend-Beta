package com.project.yjshop.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserid(String userid);
    Boolean existsByUseridOrNickname(String userid, String nickname);
}
