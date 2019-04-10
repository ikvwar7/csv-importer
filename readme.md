сборка: mvn clean package

запуск:
 
    docker run -p 127.0.0.1:5432:5432 postgres:10.0 
    cd ./target
    java -jar csv-importer-1.0.jar
    
примеры запросов:

    http://localhost:8080/top-five.html - топ 5 используемых форм
    
    http://localhost:8080/not-ended-user-activity.html - список пользователей, которые начали активность на 
    форме  и не дошли до конца (выодится не для всех видов форм, так как непонятно какой шаг является 
    конечным для различных форм)
    
    http://localhost:8080/last-hour-activity.html - список пользователей и используемых ими форм за последний 
    час
