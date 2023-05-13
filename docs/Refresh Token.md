# Refresh Token

[Refresh token](Refresh%20Token.md) grant type is used to exchange a refresh token for an access token


# Refresh Token that never expires

If the `refresh_expires_in` is set to `0`, then the refresh token will not expire

```bash
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

## How to request the refresh token that never expires

!> Set `scope` value `profile_offline_access` on token access

# Get Access Token using Refresh Token

```bash
curl --location 'http://localhost:9000/oauth2/token' \
--header 'Authorization: Bearer eyJraWQiOiI1NzIzMTllYS1jNWFmLTRkM2EtYmIwNy03MjhhOGM0NTFiNmUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI3MWVmNTViZS1hODNiLTQ1M2MtYmNkYS1mZWViYzg2M2JlNTUiLCJhdWQiOiIxIiwibmJmIjoxNjc5NzY0NDkwLCJzY29wZSI6WyJyZWFkIl0sInJvbGVzIjpbIlVTRVIiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiQ3JlYXRvciI6IlRoaXJ1bWFsIiwiZXhwIjoxNjc5NzY3NDkwLCJpYXQiOjE2Nzk3NjQ0OTB9.1W1ZuuCPy46ZVvQWEoVVKHQs4hdO_-1PyUe16fQk3KJeJs8Zu3KlFlKkr7AzpJR11_TuZ14atLexeKI7cZFA_dfBjP_pQq4j0RC7S8rXGaetXTjG--PykV2x4TMnj_bvJkp_6ZVMGbKkXT6CbysqzLRbY8e6ZZVkDhMKAa4avswdB4MgPq0DHqqjh21Gre8_1pm7Op25PGySGP1xfHnGXgY1fdBFCjGcHL8TJyQgrEl11qZo4CrvrDeevmCPLfMwYUla2GJIocWO9oCTAyVgmU4H2jMaCWyRrtOWuhP9683NTefPuprm73_blqWKvcEgULxijT_6HVQKJwPzSgYFPg' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=refresh_token' \
--data-urlencode 'client_id=1' \
--data-urlencode 'client_secret=Thirumal' \
--data-urlencode 'refresh_token=PaQ0EXNaBwGfmuAjk86uYBQlnVEk7B__n9bPLQ2Pz_T8p3IWDSa3oIV76OpuI3f3NFzxH2H6hUJ8-1gAj9VerIk72I9YAhg8QxI53eMMhk72UiiCGUfXVwBAR93GZMLF'
```

![](./img/access-token/access-token-using-refresh-token.png)