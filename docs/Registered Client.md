# Registered Client

`ClientId` and `ClientSecret` are credentials that the OAuth Client application will use to authenticate with the server. 

There are two types of OAuth Clients: 

* Public
* Confidential

By providing value for ClientSecret, we make this OAuth client a confidential client. 

If the OAuth Client application is a Javascript application, then no need to configure the Client Secret value. Use PKCE instead.

POST

```bash
{
  "clientName": "client1",
  "clientAuthenticationMethods": ["client_secret_basic", "client_secret_post"],
  "authorizationGrantTypes": ["refresh_token"],
  "redirectUris": ["http://127.0.0.1:8000/authorized","http://127.0.0.1:8000/login/oauth2/code/users-client-oidc"],
  "scopes": ["openid","profile"],
  "clientSettings": {
    "settings": {
      "additionalProp1": {},
      "additionalProp2": {},
      "additionalProp3": {}
    },
    "requireProofKey": true,
    "jwkSetUrl": "string",
    "requireAuthorizationConsent": true,
    "tokenEndpointAuthenticationSigningAlgorithm": {
      "name": "string"
    }
  },
  "tokenSettings": null
}


```