# Introspect

Introspect is used to check the status (is active or not) of token.

```
curl --location 'http://localhost:9000/oauth2/introspect' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'token=eyJraWQiOiJkMTE0YzI5Yy04ZDUwLTQxZWMtODJmNy02MmU0MWU4ZmVjMDMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyYzM0NjUzNy1hMWU0LTQ4NjktODVlMC01YWFiNzNhN2E1ZTkiLCJhdWQiOiIxIiwibmJmIjoxNjc4NTUzMDYwLCJzY29wZSI6WyJyZWFkIl0sInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaXNzIjoiaHR0cDovLzMuNi4yMzkuMTk4OjkwMDAiLCJleHAiOjE2Nzg1NTYwNjAsImlhdCI6MTY3ODU1MzA2MH0.RuNKVbXHYTH1QstF-XKfKbRyIN9jH1gMqX-kmAnUyPLtG1aM2IYm_dN6Bh8lVjTGcQAR4AgC6mUHQEo0SVfuuTG09-KGfJ18jTRPC1jaKJaK2rpCR6N_XRcTDK8WUtb5IPRZdxSM1cUri1VGZlMponZPLt927_E-tRlQljTfEZD1-yfnX652pvETSifZzo75VtljhUaxuJIdLc5BoB3UCS6hdp_drXyBCpEFX5hx9xKaPJ3f9fP9MnzLFKV9p0o5gNzBzB_VKr_IGmArlDVt5AxLRuHFQmBASew2pA9ifTNsCM_WD9RtKWATkd4nGfFY89Emz1G7nw3-rpBELM5P-w' \
--data-urlencode 'client_id=1' \
--data-urlencode 'client_secret=Thirumal'
```

![](./img/introspect/introspect.png)