# eshop

1. Create Spring/Spring Boot application with REST-like architecture to handle HTTP-requests.
2. Request and response format - JSON.
3. Pay attention to project structure (model, service, api, etc).
4. Data storage - choose any SQL/NoSQL solution, i.e. PostgreSQL or MongoDB.
5. The output of this task should be a Git repository with source code, which contains README file with all the instructions to start your application locally.
### Requirements:

There are 3 entities: Order, Item and Payment. Each order can have multiple Payments and multiple Items.
Items has next attributes: name, price.
Payment has next attributes: number, sum, payment date and time.
Order has next attributes: number, status, total of items, total of payments. Order has next lifecycle: CREATED, PROCESSING, SHIPPING, DELIVERED. Order can’t be moved to SHIPPING and DELIVERING statuses, until all items are paid. You can’t add items to Order with SHIPPING and DELIVERING status.
CRUD operations for each entity.
Handle all the possible scenarios (OK, BAD_REQUEST, NOT_FOUND, etc).

### Advanced requirements (do some or all):

1. Dockerize your application(s).
2. Add a simple micro service in additional to developed. Your “Order management” microservice will produce events to MessageBus (Kafka/RabbitMQ) in reaction to any CRUD request. Body of these events is type of operation and affected object. New service will listen and store these events in the DB.
### Instructions

1. To run these project you should have Git, Docker, Docker compose, Maven and java 11 installed on 
your machine.
2. Clone repository to your machine applications folder.
3. Project configured for start on local Docker application.
4. Open command line application, go to the root folder and run command: **docker-compose up** to start all 
necessary services.
5. You can test api via Postman. Open Postman and load **eshop.postman_collection.json** file from root project folder,
now you can test all api endpoints.
6. Also you can manage kafka topic via link http://localhost:9090/topics.