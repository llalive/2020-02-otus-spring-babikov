# DAO на Spring JDBC
## Создать приложение хранящее информацию о книгах в библиотеке
**Задание:** 
1. Использовать Spring JDBC и реляционную базу (H2 или настоящую реляционную БД). Настоятельно рекомендуем использовать NamedParametersJdbcTemplate.
1. Предусмотреть таблицы авторов, книг и жанров.
1. Предполагаются отношения один-ко-многим (у книги один автор, но у автора может быть несколько книг, то же касается книг и жанров). Опциональное усложнение - отношения много-ко-многим.
1. Интерфейс выполняется на Spring Shell (CRUD книги обязателен, операции с авторами и журналами - как будет удобно).
1. Скрипт создания таблиц и скрипт заполнения данными должны автоматически запускаться
с помощью spring-boot-starter-jdbc.
1. Покрыть тестами, насколько это возможно.