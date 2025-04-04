package com.project.camp.user;

import java.time.LocalDate;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영어 알파벳과 숫자만 입력할 수 있습니다.")
	@Size(min = 6, max = 15, message = "아이디는 6자 이상 15자 이하여야 합니다." )
	@NotBlank(message = "사용자 ID는 필수항목입니다.")
	private String userId;
	
	@Pattern(regexp = "^[가-힣]*$", message = "한글만 입력할 수 있습니다.")
	@Size(min=2, max = 5, message = "이름은 2자 이상 5자 이하여야 합니다.")
	@NotBlank(message = "이름은 필수항목입니다.")
	private String realName;
	
	@Size(min = 6, max = 25, message = "닉네임은 6자 이상 25자 이하여야 합니다.")
	private String nickname;
	
	@Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
	@NotBlank(message = "비밀번호는 필수항목입니다.")
	private String password1;
	
	@NotBlank(message = "비밀번호 확인은 필수항목입니다.")
	private String password2;
	
	@Size(max = 254, message =  "이메일 길이는 254자를 넘어갈 수 없습니다.")
	@NotBlank(message = "이메일은 필수항목입니다.")
	@Email // 해당 속성의 값이 이메일의 형식과 일치하는지 검증 
	private String email;
	
	@Size(max = 13)
	@NotBlank(message = "전화번호는 필수항목입니다.")
	private String phoneNumber;
	
	@NotNull
	private LocalDate birthday;
	
	@NotNull
	private Gender gender;
	
	
}
