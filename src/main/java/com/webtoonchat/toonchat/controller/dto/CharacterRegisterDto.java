package com.webtoonchat.toonchat.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CharacterRegisterDto {
	@NotBlank
	private String name;
	@NotBlank
	private String statusMessage;
	@NotBlank
	private String hashTag;
	@NotBlank
	private String persona;
}
