# OAuth 2 Authorization server

## What is OAuth 2?

OAuth means Open Authorization

OAuth 2 - Authorization Framework

``` mermaid
sequenceDiagram
  actor O AS  Resource Owner
  participant C AS Client
  autonumber
  O->>C: Login URL
  C->>Authorization Server: http://localhost:9000/auth?client_id=resource-server-1&response_type=code&scope=openidprofile&redirect_url=http://localhost:9001/login-callback&state=sEdgkiEkpvnsj
  Authorization Server->>Resource Owner: Present user with Login page
  O->> Authorization Server: User logs in
  Authorization Server->>O: Present Consent page
  Resource Owner->> Authorization Server: User authorize client to access the resource server
  Authorization Server->>C: Authorization Code (Redirect to http://localhost:9001/callback?code=12ddassa-jk12nm32...)
  C->>Authorization Server: Exchange authorization code for an Access Token (http post/token + code + clientId + Client Secret)
  C->>Resource Server: Request data with (Bearer `token`)
  Authorization Server ->>Resource Server: Validate Token
  Resource Server->>C: Resource

```


* [Definition](Definition.md)