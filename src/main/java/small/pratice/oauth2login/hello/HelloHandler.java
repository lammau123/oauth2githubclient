package small.pratice.oauth2login.hello;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloHandler {
	@GetMapping(value="/hello", produces="application/json")
	public HelloData hello(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
			@AuthenticationPrincipal OAuth2User oauth2User) {
		return HelloDataBuilder.create().withMessage("hello").build();
	}
}
