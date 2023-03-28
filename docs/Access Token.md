# Access Token

Access token is short lived, used to access the resource.

# Contents of a token

* identifier

* JWT/ID Token - can contain user, authorities, roles, scope,.. details and token details

* Opaque Token - identifier - the token will not have user / role / authorities details

# How create access token.

* Using grant

* Using refresh token

### Create Access token using Grant

`GET` method to get access token using refresh token...

```
curl --location 'localhost:9000/oauth2/token' \
--header 'Authorization: Basic MTpUaGlydW1hbA==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=authorization_code' \
--data-urlencode 'code=Saq4y3tOX2egzOdcZr7bB1xUhtSAb9ZtZXP8xw6hhQukOTinFIY0beufHu303tD_nNdgslmZXY0DXgs4gZUMgkPvs0t1P__ajdwYscZIEDZBcFpvooxIReFawD3OKXJJ' \
--data-urlencode 'redirect_uri=http://127.0.0.1:8000/authorized'
```
![Using Postman](./img/access-token/access-token-using-grant.png)


### Create Access Token using Refresh token

`POST` method to get access token using refresh token...

```
curl --location 'http://localhost:9000/oauth2/token' \
--header 'Authorization: Bearer eyJraWQiOiI1NzIzMTllYS1jNWFmLTRkM2EtYmIwNy03MjhhOGM0NTFiNmUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI3MWVmNTViZS1hODNiLTQ1M2MtYmNkYS1mZWViYzg2M2JlNTUiLCJhdWQiOiIxIiwibmJmIjoxNjc5NzY0NDkwLCJzY29wZSI6WyJyZWFkIl0sInJvbGVzIjpbIlVTRVIiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiQ3JlYXRvciI6IlRoaXJ1bWFsIiwiZXhwIjoxNjc5NzY3NDkwLCJpYXQiOjE2Nzk3NjQ0OTB9.1W1ZuuCPy46ZVvQWEoVVKHQs4hdO_-1PyUe16fQk3KJeJs8Zu3KlFlKkr7AzpJR11_TuZ14atLexeKI7cZFA_dfBjP_pQq4j0RC7S8rXGaetXTjG--PykV2x4TMnj_bvJkp_6ZVMGbKkXT6CbysqzLRbY8e6ZZVkDhMKAa4avswdB4MgPq0DHqqjh21Gre8_1pm7Op25PGySGP1xfHnGXgY1fdBFCjGcHL8TJyQgrEl11qZo4CrvrDeevmCPLfMwYUla2GJIocWO9oCTAyVgmU4H2jMaCWyRrtOWuhP9683NTefPuprm73_blqWKvcEgULxijT_6HVQKJwPzSgYFPg' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=refresh_token' \
--data-urlencode 'client_id=1' \
--data-urlencode 'client_secret=Thirumal' \
--data-urlencode 'refresh_token=cXFPqw-YyV7P5Jp_3YAxzBt7iomaN_ki34-yKIC4NjbF_ReFqn8fMf2tYbsWfzBDt4IGACkOxRZUWrjQF1VvWjkF3SrevpGaVvF4ALkw72LMgzLKxmAtjCsa6pUQDjvn'
```
![Using Postman](./img/access-token/access-token-using-refresh-token.png)