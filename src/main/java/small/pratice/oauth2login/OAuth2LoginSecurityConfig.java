package small.pratice.oauth2login;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> authorize
            	.antMatchers("/login", "/explicit", "/explicit1").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2.loginPage("/login"))
            .oauth2Client(withDefaults());
    }
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/webjars/**");
	}
}
