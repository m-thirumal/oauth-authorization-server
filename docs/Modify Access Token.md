## Modify/Add additional information to access token

Note: Add only the required information to the access token.



Add the following bean and the claims you would like to add. Check [AuthorizationServerConfig.java](../src/main/java/in/thirumal/config/AuthorizationServerConfig.java) in the project


```
    @Bean
    OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            if (context.getTokenType() == OAuth2TokenType.ACCESS_TOKEN) {
                Authentication principal = context.getPrincipal();
                Set<String> authorities = principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());
                context.getClaims().claims(c -> c.put("Creator", "Thirumal"));
                context.getClaims().claim("roles", authorities);
            }
        };
    }
 ```