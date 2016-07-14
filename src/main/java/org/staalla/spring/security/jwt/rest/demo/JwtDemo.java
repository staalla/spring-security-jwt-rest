package org.staalla.spring.security.jwt.rest.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.SecretKey;

import org.staalla.spring.security.jwt.rest.security.User;


public class JwtDemo {

	private String secret="pass";

	/**
	 * Tries to parse specified String as a JWT token. If successful, returns
	 * User object with username, id and role prefilled (extracted from token).
	 * If unsuccessful (token is invalid or not containing all required user
	 * properties), simply returns null.
	 *
	 * @param token
	 *            the JWT token to parse
	 * @return the User object extracted from specified token or null if a token
	 *         is invalid.
	 */
	public User parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret)
					.parseClaimsJws(token).getBody();

			User u = new User();
			u.setUsername(body.getSubject());
			u.setId(Long.parseLong((String) body.get("userId")));
			u.setRole((String) body.get("role"));

			return u;

		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	/**
	 * Generates a JWT token containing username as subject, and userId and role
	 * as additional claims. These properties are taken from the specified User
	 * object. Tokens validity is infinite.
	 *
	 * @param u
	 *            the user for which the token will be generated
	 * @return the JWT token
	 */
	public String generateToken(User u) {
		Claims claims = Jwts.claims().setSubject(u.getUsername());
		claims.put("userId", u.getId() + "");
		claims.put("role", u.getRole());

		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512,secret).compact();
	}

	public SecretKey getKey(){
		SecretKey key=MacProvider.generateKey(SignatureAlgorithm.HS512);
		return key;
	}


	public static void main(String[] args) {

		//secret =pass for generating token in demo. Its the same secret in the secured api (set in applciation.properties) so the CURL works.
		//replace the localhost:8380 according to the server port where the api is deployed
		JwtDemo demo = new JwtDemo();
		User u = new User();
		u.setId(1l);
		u.setRole("role");
		u.setUsername("staalla");

		System.out.println("Try the API without token it should result in 401 response");
		System.out.println("curl -X GET -H \"Cache-Control: no-cache\" \"http://localhost:8380/restauth/api/message\" ");


		String token = demo.generateToken(u);
		System.out.println("Authorization : Bearer "+ token);
		System.out.println("Use CURL to request the secured api with the token using below command");
		System.out.println("curl -X GET -H \"Authorization: Bearer "+ token +"\" -H \"Cache-Control: no-cache\" \"http://localhost:8380/restauth/api/message\" ");

	}
}
