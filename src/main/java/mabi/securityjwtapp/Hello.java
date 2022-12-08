package mabi.securityjwtapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mabi.securityjwtapp.models.AuthenticationRequest;
import mabi.securityjwtapp.models.AuthenticationResponse;
import mabi.securityjwtapp.services.MyUserDetailsServices;
import mabi.securityjwtapp.util.JwtUtil;

@RestController
public class Hello {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsServices userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

//	@GetMapping("/hello")
//	@ResponseBody
	@RequestMapping("/hello")
//	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		String hello = "Hello";
//		return "Hello World";
		return hello;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e){
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
