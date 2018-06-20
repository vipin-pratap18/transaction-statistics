# transaction-statistics

## Getting Started

This is a micro-services created in java and spring boot. This API contains two endpoints, one for inserting transactions in and one for providing statitics of transactions which are happened in last 60 seconds.

In order to execute the project just download the repo and run 

## Building project

```
mvn clean install
```

This will download all the necessary dependencies and build the project and then execute the jar from target directory.
It is using an in memory solution to store and retrieve data, no external db or in memory db required.

## Endpoints

```
POST request, and input body is.
{
     "timestamp" : 1529471828410,
    "amount" : 20.47
}

http://localhost:8081/txn-api/v1/transactions

```

Returns: Empty body with either 201 or 204.
- 201 - in case of success, means transaction recieved for last 60 seconds
- 204 - if transaction is older than 60 seconds, do not discard it but do not include it for statistics

```
GET request, to get the statistics of transactions happened in last 60 second.
http://localhost:8081/txn-api/v1/statistics

```

This API gives statistics of transactions happened in last 60 seconds in constant time.
