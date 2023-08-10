package com.webtoonchat.toonchat.domain.chat;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cid;
	private String botName;
	private String hashtag;
	private String imageUrl;
	private String introduction;

	@ManyToOne
	@JoinColumn(name = "webtoon_id")
	private Webtoon webtoon;

}

//@NoArgsConstructor
//@Getter
//@Setter
//@Document(collection = "character")
//public class Character {
//	@Id
//	private ObjectId id;
//	private String cid;
//	private String botName;
//	private String hashtag;
//	private String imageUrl;
//	private String webtoonId;
//	private String introduction;
//}
