# Refresh Token

[Refresh token](Refresh%20Token.md) grant type is used to exchange a refresh token for an access token



# Refresh Token that never expires

If the `refresh_expires_in` is set to `0`, then the refresh token will not expire

```
{
	"access_token": "",
	"expires_in"  : "",
	"refresh_token": "",
	"refresh_expires_in": "",
	"token_type"  : "",
	"session_state": "",
	"scope"       : ""
}

```

### How to request the refresh token that never expires

Set `scope` value `profile_offline_access` on token access


