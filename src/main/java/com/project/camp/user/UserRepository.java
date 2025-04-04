package com.project.camp.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByUserId(String userId);
	boolean existsByUserId(String userId);
	boolean existsByNickname(String nickname);
	boolean existsByEmail(String email);
	boolean existsByPhoneNumber(String phoneNumber);
}
