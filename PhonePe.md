# Problem Statement: Low-Level Design of PhonePe
## Objective:
* Design the low-level architecture for an online payment application similar to PhonePe.
The system should support common functionalities such as sending and receiving money, handling
multiple payment methods, and providing a smooth user experience. The design should ensure scalability,
reusability, and modularity, allowing for the easy addition of new payment features or services.

* Requirements
Functional Requirements:
User Authentication & Authorization:

* Users must be able to register, log in, and log out using phone numbers and OTPs.
The system should maintain secure sessions for logged-in users.
Money Transfer:

* Users should be able to transfer money to other users or merchants.
The system must support peer-to-peer (P2P) payments, as well as peer-to-merchant (P2M) payments.
Multiple payment methods (bank account, UPI, wallet balance, credit/debit card) should be supported.
Bill Payments & Recharge:

* Users should be able to pay utility bills (electricity, water, etc.) and recharge mobile phones.
Transaction History:

* Users must be able to view past transactions, including money transfers, bill payments, and recharges.
Balance Check:

* Users should be able to check their wallet balance and linked bank account balance.
Notifications:

* Users should receive real-time notifications for successful and failed transactions.
Refunds & Dispute Resolution:

* The system should handle failed transactions and offer refunds to users in case of failures.
Users must be able to raise disputes for unsuccessful or incorrect transactions.

* Non-Functional Requirements:
Security:

* All sensitive data (e.g., bank details, passwords, payment tokens) must be encrypted.
Support for two-factor authentication (2FA) and secure OTP mechanisms.
Scalability:

* The system should be able to handle millions of users and transactions per day.
It should scale horizontally to support sudden spikes in traffic (e.g., during festivals or sales).

* High Availability:

* The system must ensure 99.9% uptime, as it deals with financial transactions that require constant availability.
Performance:

* The response time for critical operations such as sending or receiving money should be minimal (less than 1 second).
Ensure that transactions are processed quickly to avoid delays in money transfer.
Extensibility:

* The system should be modular, allowing new payment methods and features (such as cashback or promotional offers) to be added easily.
Key Components of the Design
User Service:

* Manages user registration, login, and profile management.
Handles authentication (OTP-based login) and session management.
Payment Service:

* Handles payment initiation and processing.
Supports integration with multiple payment gateways (for cards, UPI, etc.).
Manages refunds and reversals in case of failed payments.
Transaction Service:

* Responsible for managing the lifecycle of transactions (initiated, pending, completed, failed).
Ensures atomicity of transactions to avoid partial or failed transfers.
Keeps a record of all transactions and logs for audit purposes.
Wallet Service:

* Manages the user’s wallet balance.
Supports top-ups, withdrawals, and balance inquiries.
Bill Payment Service:

* Interfaces with external utility providers (e.g., electricity, water, gas) to support bill payments.
Manages bill generation, tracking, and payment history.
Notification Service:

* Sends notifications (SMS, push notifications, emails) for important events like successful payments,
failed transactions, and OTP delivery.
Dispute Resolution Service:

* Manages disputes raised by users.
Tracks the status of disputes and ensures resolution (refund or rectification).
External Gateway Integrations:

* Interfaces with banks, UPI servers, and other financial entities to handle external payments.
Supports payment gateway integrations for card payments, net banking, etc.

Assumptions
User Base: The system needs to handle at least 100 million active users.
Transactions: It should handle a peak load of 1 million transactions per second during high-traffic events.
External Dependencies: Payments may fail due to issues with banks, networks, or
payment gateways, so the system must handle failures gracefully and ensure refunds or retries.
Class Diagram Overview
User: Handles user data (userID, phoneNumber, balance).
Transaction: Tracks transactions (transactionID, sender, receiver, amount, status).
Wallet: Manages user’s wallet (balance, top-up, withdrawal).
PaymentGateway: Interacts with external payment services (UPI, bank, credit/debit cards).
Notification: Sends notifications (SMS, push, email).
BillPayment: Manages billers, payment history, and status.
Refund: Handles failed transactions and refunds.





write Controllers, ObjectMappers, Repositories with JPA, Exceptions, Models, Service Layeers with DB Locks