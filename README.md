# eshop
1. To run these project you should have Git, Docker, Docker compose, Maven and java 11 installed on 
your machine.
2. Clone repository to your machine applications folder.
3. Project configured for start on local machine, so if it will start from another place you should change settings in 
**docker-compose.yml** and **application.yml** files.
4. Open command line application, go to the project folder and run command: **docker-compose up** to start all 
necessary services.
5. To run applications, go to their root folders and run command: **mvn spring-boot:run**.
6. You can test api via Postman. Open Postman and load **eshop.postman_collection.json** file from root project folder,
now you can test all api endpoints.
7. Also you can manage kafka topic via link http://localhost:9090/topics.