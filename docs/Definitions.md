
## Roles

| Roles                      | Description                                         |
| ---------------------------| ------------------------------------                |
| Resource Owner             | User                                                |
| Authorization Server       | Server that issues token to the client application  |
| Client                     | :mobile_phone:, :desktop:, :tv:, :material-firefox: |
| Resource Server            | :material-api:                                      |


## Client Types

There are two types 

* Confidentail Client - Can keep `Client Secret` safe

  * Secure app running on Server

* Public Client - Cannot keep `Client Id & Secret` safe

  * Native Apps on user device
  * Single Page browser based app
  

## Access Token

* Identity token 

* Self contained token

## Grant Type 

- Way an application gets an access token


| Client Type                | Grant Type                |                                      |
| ---------------------------| --------------------------|--------------------------------------|
| Server Side Web App        | Authorization Code        | Password Grant                       |
| Server Script with no UI   | Client Credentials        | -                                    |
| JavaScript Single Page App | PKCE Enhanced Auth code   | Implicit Flow / Password Grant       |
| Mobile Native App          | PKCE Enhanced Auth code   | Implicit Flow / Password Grant       |
| Device                     | Device Code               | Password Grant                       |


* PKCE - Proof of Key for Code Exchange

Request for OAuth code

```
http://localhost:9000/auth

?client_id=""

&response_type=code

&scope=openid

&redirect_uri=

&state=

&code_challenge=

&code_challenge_method=S256
```

Exchange OAuth code for access token

Grant types are 	`authorization_code, client_credentials` based on the type of clients

```
http://localhost:9000/token

--header 'Content-Type: application/x-www-form-urlencoded'

--data-urlencode 'grant_type=authorization_code'

--data-urlencode 'client_id=resource-server-1'

--data-urlencode 'code='

--data-urlencode 'redirect_uri='

--data-urlencode 'code_verifier='

```

Access Tokens


```
{
	"access_token": "",
	"expires_in"  : "",
	"token_type"  : "",
	"scope"       : ""
}
```


#### Refresh Token

  [Refresh token](Refresh%20Token.md) grant type is used to exchange a refresh token for an access token
  
  
## Scope

Limit user access on resource server

##