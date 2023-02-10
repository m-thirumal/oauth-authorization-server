# OAuth 2.1 Authorization server - Spring Boot 3 and  above

## What is OAuth 2?

OAuth means Open Authorization

OAuth 2 - Authorization Framework

``` mermaid
sequenceDiagram
  autonumber
  Resource Owner->>Client: Credentials
  loop Healthcheck
      John->>John: Fight against hypochondria
  end
  client->>Authorization Server: Great!
  Authorization Server->>Client: Tokens
  Client-->>Resource Server: Bearer `token`

```