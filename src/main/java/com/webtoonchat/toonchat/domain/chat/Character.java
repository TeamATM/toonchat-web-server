package com.webtoonchat.toonchat.domain.chat;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "characters")
@Data
@NoArgsConstructor
@Entity
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String botName;
	private String cid;
	private String hashtag;
	private String imageUrl;
	private String introduction;
	private String webtoonName;
	private String corpName;

}
