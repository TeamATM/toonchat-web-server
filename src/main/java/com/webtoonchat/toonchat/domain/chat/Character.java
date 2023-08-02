package com.webtoonchat.toonchat.domain.chat;


import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "character")
public class Character {
	@Id
	private String id;
	private String botName;
	private String hashtag;
	private String imageUrl;
}
