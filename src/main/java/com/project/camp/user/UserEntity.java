package com.project.camp.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** UserCreateForm과 동시에 이중 유효성 검사 */
	@Column(nullable = false, unique = true, length = 15)
	private String userId;
	
	@Column(nullable = false, length = 5)
	private String realName;
	
	@Setter
	@Column(nullable = false, unique= true, length = 25)
	private String nickname;
	
	// 비크립트로 암호화한 비밀번호는 60자 고정 
	@Setter
	@Column(nullable = false, length =  60)
	private String password;
	
	@Setter
	@Column(nullable = false, unique = true, length = 254) // RFC 5321 표준 이메일 길이 제한
	private String email;
	
	@Setter
	@Column(nullable = false, unique = true, length = 13)
	private String phoneNumber;
	
	@Setter
	@Column(nullable = false)
	private LocalDate birthday;
	
	@Setter
	@Enumerated(EnumType.STRING) // enum을 문자열로 저장
	@Column(nullable = false, length = 1)
	private Gender gender; // M / F / N 으로 저장

	@Column(nullable = false)
	private LocalDateTime createdAt;

	public UserEntity(String userId, String realName, String nickName, String password, String email,
			String phoneNumber, LocalDate birthday, Gender gender, LocalDateTime createdAt) {
		
		this.userId = userId;
		this.realName = realName;
		this.nickname = nickName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.gender = gender;
		this.createdAt = createdAt;
	}
	
}
