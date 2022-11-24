# Realtime rate exchange for USD-UAH

*A microservice is designed to give information about current rate every 30 seconds*

### Steps to run

1. Clone the project with 
`git clone https://github.com/katerina20/realtime-rate.git`
2. Go to the cloned project and build it with `./mvnw clean package`
3. Up it with `docker-compose up`
4. The endpoint to start receive the information is GET `http://localhost:8080/api/rate/`
5. `docker-compose down` stops the service.

The response is shown in such format:

*Текущий курс: 31,20 Средний курс: 31,10 Дата: 20.11.2022 12:21:30*

For the implementation Spring Boot is used. MongoDb is used as a database.
Microservice makes a request to external API to save current rate every 2 seconds to calculate average day rate.
External API: https://v6.exchangerate-api.com/