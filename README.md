# telegramBot
BOT_NAME = "ApollinariaBot";
BOT_TOKEN = "1625385211:AAHZz7HLy8RR5hUgzmnS9JER8UDgDPNoBgM";
Бот запускается в src/main/java/AppBot.java

СУБД PostgreSQL. Файл с SQL-текстом для создания базы данных, 
таблицы и заполнения данными по директории
src/main/java/by/zolotaya/telegrambot/dao/textForDatabase
(можно поменять адрес сохранения логов в src/main/resources/log4j.properties)

Apache Tomcat запускает web-service приложения для управления данными о городах.
