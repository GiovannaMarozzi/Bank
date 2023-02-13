# Bank
<p><b>
  Bank Software Development

  Development of Bank Sistems.
  Linguage: Java version 17
</b></p>



<p>
The system simulates a bank system, in which it is possible to carry out the "Withdrawal", "Deposit" and "Transfer" transitions. Spring boot, spring security is used with oauth authentication.
There is at least one endpoint for each transition that is made, and also at least one endpoint for each authentication required.

For authentication it is necessary to go to the "Authorization" -> Type = Bearer Token and in the available input, put the token generated when logging in with the registered account.
Tool used for POSTMAN authentications.

</p>

<p>
There is also a specific branch for each case, namely: Master, Milestone-api, Development, Development-test

![Captura de tela de 2023-02-13 19-13-19](https://user-images.githubusercontent.com/36972799/218590064-b260fd0d-2ee7-449e-8df3-749cac7931a5.png)


</p>

Endpoints:
Registration of a new user in the bank (oauth authentication is not required): (POST) localhost:8080/autentication/cad

```json
Json required:
{
    "nome": "Teste",
    "login": "teste@gmail.com",
    "password": "$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO",
    "cpf_or_cnpj": 12345678955,
    "type_document": "cpf",
    "rg": 536079766,
    "cel": "11 962722649",
    "saldo": 0.00
}

```
------------------------------------

Generation of tokens for authentication: (POST) localhost:8080/autentication/auth
```json
Json required:
{
    "login": "teste2@gmail.com",
    "password": "123456"
}
```


------------------------------------

Account blocking: (PUT) localhost:8080/autentication/block={document}

------------------------------------

<b>Obs.: From this endpoint all oauth authentication is required.</b>

Deposit transaction: (POST) localhost:8080/transactions/deposit

```json
Json required:
{
        "nome": "Teste",
        "number": 2498345,
        "cpf_or_cnpj": 12345678974,
        "rg": 258852147,
        "value": 200.00,
        "type": "Deposito"
}
```

------------------------------------
Withdrawal transaction: (POST) localhost:8080/transactions/withdraw

```json
Json required:
{
        "nome": "Teste",
        "number": 2498345,
        "cpf_or_cnpj": 12345678974,
        "rg": 258852147,
        "value": 200.00,
        "type": "Saque"
}
```
------------------------------------
Transfer transaction: (PUT) localhost:8080/transactions/transfer

```json
Json required:
{
        "nome": "Teste",
        "number": 2498345,
        "accountNumber": 123456,
        "cpf_or_cnpj": 12345678974,
        "rg": 258852147,
        "value": 200.00,
        "type": "Saque"
}
```

------------------------------------

Extract: (GET) localhost:8080/transactions/extract/document={document}

------------------------------------
Transfer Transaction: (PUT) localhost:8080/transactions/transfer

```json
Json required:
{
        "nome": "Teste",
        "number": 2488583,(conta que irá ser retirado)
        "accountTransfer": 4428808,(conta que irá receber)
        "cpf": 12345678974,
        "rg": 258852147,
        "value": 20.00,
        "type": "Transferência"
}
```
------------------------------------

Show all registered accounts: (GET) localhost:8080/account

------------------------------------

------------------------------------

Show account from registration number: (GET) localhost:8080/account/document={number}

------------------------------------
