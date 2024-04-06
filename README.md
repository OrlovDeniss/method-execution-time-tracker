# Method execution time tracker
Отслеживает время выполнения методов 


## Стэк
Java 17 Maven 4, Spring Boot, Spring AOP, Spring Data, PostgreSQl

## API
URL: http://localhost:8080

- GET /time-metrics/statistics - получить статистику по методам

Подробнее: http://localhost:8080/swagger-ui/index.html

## Сборка и запуск
1. Скопируйте репозиторий:
```Bash
git clone https://github.com/OrlovDeniss/method-execution-time-tracker.git
```
2. Перейдите в каталог проекта: 
```Bash
cd method-execution-time-tracker
```
3. Скомпилируйте исходные файлы:
```Bash
mvn clean package
```
4. Запустите проект из папки target:
```Bash
java -jar method-execution-time-tracker-0.0.1-SNAPSHOT.jar
```