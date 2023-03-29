# Role details in Access Token


Add Roles in claims

```
@Bean
OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(CustomClaims claims) {
    return context -> {
        if (context.getTokenType() == OAuth2TokenType.ACCESS_TOKEN) {
            Authentication principal = context.getPrincipal();
            Set<String> authorities = principal.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            context.getClaims().claims(c -> c.putAll(claims.getClaims(principal)));
            context.getClaims().claim("roles", authorities);
        }
    };
}
```