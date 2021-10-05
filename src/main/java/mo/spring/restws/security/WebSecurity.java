package mo.spring.restws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mo.spring.restws.services.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private final UserService userDetailsServices;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsServices = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.csrf().disable() // ndesactiver csrf
			.authorizeRequests() 
			.antMatchers(HttpMethod.POST, SecurityConstants.SING_UP_URL).permitAll() // n allower lmethod Post 3la dak route
			.anyRequest().authenticated()
			.and()
//			.addFilter(new AuthenticationFilter(authenticationManager()));
			.addFilter(getAuthenticationFilter()) // nkhdem system d'authontication
			.addFilter(new AuthorizationFilter(authenticationManager())) // nkhdem l'authorization d JWT
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // nkhdem les sessions stateless  
	}
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception{
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		
		// bach nbdel default route '/login' dyal l'authentication  
		filter.setFilterProcessesUrl("/users/login");
		
		return filter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServices).passwordEncoder(bCryptPasswordEncoder);
	}
}
