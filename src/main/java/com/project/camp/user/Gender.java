package com.project.camp.user;

import lombok.Getter;

@Getter
public enum Gender {
	N("선택 안 함"),
	M("남성"), 
	F("여성");
	
	private final String displayName;
	
	Gender(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
}
