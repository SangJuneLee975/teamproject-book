package org.bookbook.domain;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordVO {

	private String userid;
	
	@NotBlank(message="필수항목입니다.")
	private String orgPassword;
	
	@NotBlank(message="필수항목입니다.")
	private String newPassword;
	
	@NotBlank(message="필수항목입니다.")
	private String newPassword2;
	
	private String encPassword; //서비스에서 설정
	//4가지가 폼을 통해서 전달받는 값입니다
	//username은 유저타입 히든
	// 나머지는 직접 입력받
	//암호화하는 것은 서비스에서 설정
}
