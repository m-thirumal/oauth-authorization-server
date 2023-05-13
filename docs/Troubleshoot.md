# Troubleshoot

## Access Token issued using Refresh token is not working

Problem:-

```json
org.springframework.security.authentication.AuthenticationServiceException: An error occurred while attempting to decode the Jwt: Malformed Jwk set
    at org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider.getJwt(JwtAuthenticationProvider.java:103) ~[spring-security-oauth2-resource-server-6.0.1.jar:6.0.1]
```

Solution:-

Make sure, you are using public IP address, or same IP address on all of your resource and authorization server.


