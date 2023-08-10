package com.webtoonchat.toonchat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerationArgsDto {
	private Float temperature;
	@JsonProperty("repetition_penalty")
	private Float repetitionPenalty;
	@JsonProperty("top_p")
	private Float topP;
	@JsonProperty("top_k")
	private Float topK;
}
