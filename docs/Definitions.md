
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

  ** Secure app running on Server

* Public Client - Cannot keep `Client Id & Secret` safe

  ** Native Apps on user device
  ** Single Page browser based app
  

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

#### Refresh Token

  Refresh token grant type is used to exchange a refresh token for an access token
  
  
