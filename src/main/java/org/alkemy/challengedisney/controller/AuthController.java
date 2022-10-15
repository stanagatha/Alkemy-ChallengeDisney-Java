package org.alkemy.challengedisney.controller;

import org.alkemy.challengedisney.configuration.JwtTokenUtil;
import org.alkemy.challengedisney.dto.CustomUserDTO;
import org.alkemy.challengedisney.model.CustomUser;
import org.alkemy.challengedisney.service.CustomUserService;
import org.alkemy.challengedisney.service.UserDetailsServiceImpl;
import org.alkemy.challengedisney.util.CustomMail;
import org.alkemy.challengedisney.vo.RequestUserVO;
import org.alkemy.challengedisney.vo.ResponseRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private CustomUserService customUserService;
	
	@Autowired
	private CustomMail mail;
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody RequestUserVO authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return new ResponseEntity<ResponseRequestVO>(new ResponseRequestVO(token),null,HttpStatus.OK);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/register")
	public ResponseEntity<CustomUser> createUser(@RequestBody CustomUserDTO user) throws Exception {
		user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassWord()));
		CustomUser userSaved = customUserService.save(new CustomUser(
				user.getUserName(),
				user.getPassWord(),
				user.getEmail()
				));
		mail.send(userSaved.getEmail(), "Disney Challenge", "Su cuenta se ha registrado correctamente en el portal.");
		return new ResponseEntity<CustomUser>(userSaved,null,HttpStatus.CREATED);
	} 
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}