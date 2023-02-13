# OAuth 2 Authorization server

## What is OAuth 2?

OAuth means Open Authorization

OAuth 2 - Authorization Framework

#### What is Authentication and Authorization?

`Authentication` - "Who you are?", is the process of ascertaining that somebody really is who they claim to be.

`Authorization` refers to rules that determine who is allowed to do what. E.g. Thirumal may be authorized to create and delete databases, while Jesicca is only authorized to read.


#### There are many ways of authentication, few of which are worth discussing here:

   1. `Knowledge-based authentication`: The username password combination is a type of knowledge-based authentication. The idea is to verify the user based on the knowledge of the user for example answer to security questions, passwords, something which only the user should know.
   
   
   2. `Possession based authentication`: This type of authentication is based on verifying something which a user possesses. For example, when an application sends you One Time Passwords (OTPs) or a text message.

   Modern authentication practices use a combination of both types, also known as `Multi-Factor authentication`.
   


* [Definition](Definitions.md)

``` mermaid
sequenceDiagram
  actor O AS  Resource Owner
  participant C AS Client
  participant A AS Authorization Server
  participant R AS Resource Server
  autonumber
  O->>C: Login URL
  C->>A: http://localhost:9000/auth?client_id=resource-server-1&response_type=code&scope=openid profile&redirect_url=http://localhost:9001/login-callback&state=sEdgkiEkpvnsj
  Note right of A: code_challenge & method query param is required for PKCE
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

### SQL

```
INSERT INTO public.oauth2_authorization_consent(
	registered_client_id, principal_name, authorities)
	VALUES ('Thirumal', 'admin', 'user');
```

#### Acquire Authorization Code

1. Login using URL: [http://localhost:9000](http://localhost:9000/login)

2. To get Authorization Token: [http://localhost:9000/oauth2/authorize?response_type=code&client_id=client1&redirect_uri=http://127.0.0.1:8000/authorized&scope=read](http://localhost:9000/oauth2/authorize?response_type=code&client_id=client1&redirect_uri=http://127.0.0.1:8000/authorized&scope=read)



