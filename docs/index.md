# OAuth 2 Authorization server

## What is OAuth 2?

OAuth means Open Authorization

OAuth 2 - Authorization Framework

``` mermaid
sequenceDiagram
  actor O AS  Resource Owner
  participant C AS Client
  participant A AS Authorization Server
  participant R AS Resource Server
  autonumber
  O->>C: Login URL
  C->>A: http://localhost:9000/auth?client_id=resource-server-1&response_type=code&scope=openid profile&redirect_url=http://localhost:9001/login-callback&state=sEdgkiEkpvnsj
  A->>O: Present user with Login page
  O->>A: User logs in
  A->>O: Present Consent page
  O->>A: User authorize client to access the resource server
  A->>C: Authorization Code (Redirect to http://localhost:9001/callback?code=12ddassa-jk12nm32...)
  C->>A: Exchange authorization code for an Access Token (http post/token + code + clientId + Client Secret)
  C->>R: Request data with (Bearer `token`)
  A->>R: Validate Token
  R->>C: Resource

```


* [Definition](Definition.md)