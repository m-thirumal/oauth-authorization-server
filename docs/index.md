# OAuth 2 Authorization server

## What is OAuth 2?

OAuth means Open Authorization

OAuth 2 - Authorization Framework

``` mermaid
sequenceDiagram
  autonumber
  Resource Owner->>Client: Credentials
  Client->>Authorization Server: Credentials
  Authorization Server->>Client: Tokens
  Client->>Resource Server: Bearer `token`
  Resource Server->>Client: Resource

```


* [Definition](Definition.md)