## POC para validação de orquestração de objetos com JPA e Kotlin

![Build branch main](https://github.com/raphaelfontoura/kotlin-jpa-poc-transaction-history-balance/actions/workflows/build-workflow.yml/badge.svg?branch=main)

---
### Endpoints

> GET /transaction

> GET /transaction/{transaction-id}

> GET /transaction/client/{client-id}

> POST /transaction
>```json
>{
>  "client_id": "811f5612-1261-4733-8f0a-e823cbad9f18",
>  "amount": 22000
>}
>```

>PATCH /transaction/{transaction-id}
>```json
>{
>  "status": "PAID"
>}
>```


### Tecnologias
1. Kotlin
2. Spring Web
3. Spring Data JPA
4. H2

