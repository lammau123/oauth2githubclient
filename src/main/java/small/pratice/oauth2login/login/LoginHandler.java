package small.pratice.oauth2login.login;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;
import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class LoginHandler {
	private final WebClient webClient;
	
	public LoginHandler(WebClient webClient) {
		this.webClient = webClient;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	} 
	
	@GetMapping("/")
	public String index(Model model,
						@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
						@AuthenticationPrincipal OAuth2User oauth2User) {
		model.addAttribute("userName", oauth2User.getName());
		model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
		model.addAttribute("userAttributes", oauth2User.getAttributes());
		return "index";
	}

	@GetMapping("/explicit")
	String explicit(Model model, @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient) {
		String body = this.webClient
				.get()
				.attributes(oauth2AuthorizedClient(authorizedClient))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		model.addAttribute("body", body);
		return "response";
	}

	@GetMapping("/explicit1")
	String explicit(Model model) {
		String body = this.webClient
				.get()
				.attributes(clientRegistrationId("github"))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		model.addAttribute("body", body);
		return "response";
	}

}