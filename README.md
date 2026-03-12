# digital-wallet

Database used: postgresql.
You can change the database username and password in /src/main/resources/application.properties

Run locally using WalletApplication and test using Postman.

Endpoints list:
• Add user: POST http://localhost:8080/user
• Credit (top-up) user wallet: POST http://localhost:8080/wallet/top-up
• Debit (payment) from user wallet: POST http://localhost:8080/wallet/payment
• Transfer funds between users: POST http://localhost:8080/wallet/fund-transfer
• Retrieve transaction history for a user: GET http://localhost:8080/wallet/history?user_id=
• Retrieve current balance for a user: GET http://localhost:8080/user/balance?user_id=
