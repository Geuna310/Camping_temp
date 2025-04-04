package com.project.camp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	// 회원가입 폼 페이지 반환
	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("userCreateForm", new UserCreateForm());
		return "signup_form";
	}
	
	// 회원가입 요청 처리
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute("userCreateForm") UserCreateForm ucf, BindingResult bindingResult) {
		// bindingResult에 오류가 발생하면 제자리
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		// 비밀번호 일치하지 않으면 제자리
		if (!ucf.getPassword1().equals(ucf.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"2개의 비밀번호가 일치하지 않습니다.");
			return "signup_form";
		}
	
		try {
			userService.create(ucf.getUserId(), ucf.getRealName(), ucf.getNickname(),
					ucf.getPassword1(), ucf.getEmail(), ucf.getPhoneNumber(), 
					ucf.getBirthday(), ucf.getGender());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}
		
		return "redirect:/user/login";
		
	}
	
	@GetMapping("/check-duplicate")
	@ResponseBody
	public ResponseEntity<Boolean> checkDuplicate(@RequestParam(name="field") String field,
			@RequestParam(name="value") String value) {
	    try {
	        boolean isDuplicate = userService.isDuplicate(field, value);
	        return ResponseEntity.ok(isDuplicate);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(false);
	    }
	}

	
	
	// 로그인  폼 페이지 반환
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
 	
	// 아 뭐더라.. trim 어쩌고
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, 
				new StringTrimmerEditor(true));
	}


}
