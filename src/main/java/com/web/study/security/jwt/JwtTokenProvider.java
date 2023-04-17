package com.web.study.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.web.study.dto.response.auth.JwtTokenRespDto;
import com.web.study.exception.CustomException;
import com.web.study.security.PrincipalUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
   // Key는 비밀키, 공개키와 같은 암호화 키(Key)를 추상화한 인터페이스(시크릿 키)
   private final Key key;
   
   public JwtTokenProvider(@Value("${jwt.secretKey}") String secreatKey) {
      // key를 암호화 하여 반환합니다.
      byte[] keyBytes = Decoders.BASE64.decode(secreatKey);
      this.key = Keys.hmacShaKeyFor(keyBytes);
   }
   
   public JwtTokenRespDto creatToken(Authentication authentication) {
      // 문자열로 변경해주는 객체이다.
      StringBuilder authoritiesBuilder = new StringBuilder();
      // authentication에서 리스트 형태인 authorities를 forEach문을 통해서 
      // authoritiesBuilder에 넣어 줍니다.
      authentication.getAuthorities().forEach(grantedAuthority -> {
         authoritiesBuilder.append(grantedAuthority.getAuthority());
         authoritiesBuilder.append(",");
      });
      
      // 마지막 문자열에 ,에 들어가 있기에 마지막 문자열을 제거해 줍니다.
      authoritiesBuilder.delete(authoritiesBuilder.length() - 1, authoritiesBuilder.length());
      
      // 문자열로 변환
      String authorities = authoritiesBuilder.toString();
      
      // 토큰 만료시간을 정해주기 위해 Date를 통해 현재 시간 가지고 옴
      long now = (new Date()).getTime();
      // 1000 == 1초
      // 토큰 만료 시간 설정
      Date tokenExpiresDate = new Date(now + (1000 * 60 * 30)); //토큰 만료 시간
      
      // 메서드는 인증된 사용자 정보를 반환하는 메서드 입니다.
      PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
      
      
      String accessToken = Jwts.builder()
            // setSubject() 메서드를 사용하여 JWT의 subject(주체)를 인증된 사용자 이름으로 설정합니다.
            .setSubject(authentication.getName())
            // claim() 메서드를 사용하여 JWT에 추가할 클레임 정보를 설정하는데, 여기서는 사용자 아이디와 권한 정보를 설정
            .claim("userId",userDetails.getUserId())
            .claim("auth", authorities)
            // setExpiration() 메서드를 사용하여 JWT의 만료 시간을 설정하며, 이 시간 이후에는 JWT를 사용할 수 없습니다. 
            .setExpiration(tokenExpiresDate)
            // signWith() 메서드를 사용하여 JWT의 서명 알고리즘과 서명 키를 설정합니다.
            .signWith(key, SignatureAlgorithm.HS256)
            // compact() 메서드를 호출하여 JWT 문자열을 생성
            .compact();
      
      return JwtTokenRespDto.builder()
            // grantType() 메서드를 사용하여 발급된 JWT의 타입을 "Bearer"로 설정합니다.
            .grantType("Bearer")
            // accessToken() 메서드를 사용하여 발급된 JWT 문자열을 설정 합니다.
            .accessToken(accessToken)
            .build();
   }
   
   public boolean validateToken(String token) {
      try {
         Jwts.parserBuilder()
         .setSigningKey(key)
         .build()
         .parseClaimsJws(token);
       
         return true;
         
      }catch (SecurityException | MalformedJwtException e) {
         // Security라이브러리에 오류가 있거나, 잘못된 형식의 Jwt가 들어왔을 때 예외
      	//SignatureException이 포함되어 있음.
         log.info("Invalid JWT Token", e);
      }
      catch (ExpiredJwtException e) {
		// 토큰의 유효기간이 만료된 경우의 예외
    	 log.info("Expired JWT Token", e);
	}
      catch (UnsupportedJwtException  e) {
		//JWT의 형식을 지키지 않은 경우 (Header.Payload.Signatuer)
    	  log.info("Unsupported JWT Token", e);
    }
      catch (IllegalArgumentException e) {
  		//JWT토큰이 없을 때
      	  log.info("IllegalArgument JWT Token", e);
    }
      catch (Exception e) {
         log.info("JWT Token Error", e);
    }
      
      return false;
         
   }
   
   public Authentication getAuthentication(String accessToken) {
      
	  Claims claims = parseClaims(accessToken);
	  Object roles = claims.get("auth");
	  
	   if(roles == null) {
		   throw new CustomException("권한 정보가 없는 토큰입니다.");
	   }
	   
	   List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	   
	   String[] rolesArray = roles.toString().split(",");
	   
	   Arrays.asList(rolesArray).forEach(role -> {
		   authorities.add(new SimpleGrantedAuthority(role));
	   });
	   
	   UserDetails userDetails = new User(claims.getSubject(), "", authorities);
	   return new UsernamePasswordAuthenticationToken (userDetails, "", authorities);
	  
   } 
   
   private Claims parseClaims(String accessToken) {
	   try {
		   return Jwts.parserBuilder()
				   .setSigningKey(key)
				   .build()
				   .parseClaimsJws(accessToken)
				   .getBody();
		   
	   } catch (ExpiredJwtException e) {
		   return e.getClaims();
	}
   }
}