package com.project.camp.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	// 데이터베이스에 저장
	public UserEntity create(String userId, String realName, String nickname,
			String password, String email, String phoneNumber, LocalDate birthday,
			Gender gender) {
		
		String encodedPassword = encodePassword(password);
		UserEntity user = new UserEntity(
	            userId,
	            realName,  
	            nickname,
	            encodedPassword,
	            email,
	            phoneNumber,
	            birthday,
	            gender,
	            LocalDateTime.now()
	        );
		// UserEntity 객체를 데이터베이스에 저장
		return userRepository.save(user);
		
	}
	
	public boolean isDuplicate(String field, String value) {
	    switch (field) {
	        case "userId": return userRepository.existsByUserId(value);
	        case "nickname": return userRepository.existsByNickname(value);
	        case "email": return userRepository.existsByEmail(value);
	        case "phoneNumber": return userRepository.existsByPhoneNumber(value);
	        default: throw new IllegalArgumentException("잘못된 필드명: " + field);
	    }
	}

	
	// 비밀번호 암호화
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	
}
