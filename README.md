# TimeTracker

**Система учета времени выполнения методов**

**Описание**

Эта система позволяет отслеживать и анализировать время выполнения методов в приложении с использованием Spring AOP. Система поддерживает как синхронное, так и асинхронное отслеживание времени выполнения.

**Установка и использование**

1. Добавьте следующую зависимость в ваш проект Maven:

```xml
<dependency>
    <groupId>org.petukhov.timetracker</groupId>
    <artifactId>timetracker</artifactId>
    <version>1.0.0</version>
</dependency>
```

2. Аннотируйте методы, время выполнения которых вы хотите отслеживать, аннотациями `@TrackTime` или `@TrackAsyncTime`.
3. Запустите приложение.

**Использование аннотаций**

* **@TrackTime:** Используется для синхронного отслеживания времени выполнения метода.
* **@TrackAsyncTime:** Используется для асинхронного отслеживания времени выполнения метода.

**REST API**

Система предоставляет следующий REST API для получения статистики по времени выполнения методов:

* **GET /api/method-execution-time**: Получить все сущности `MethodExecutionTime`.
* **GET /api/method-execution-time/stats**: Получить статистику по сущности `MethodExecutionTime`.

**Конфигурация**

Система может быть настроена с помощью следующих свойств в файле `application.properties`:

* **timetracker.enabled**: Включить или отключить систему отслеживания времени выполнения методов.
* **timetracker.async-execution-enabled**: Включить или отключить асинхронное отслеживание времени выполнения методов.
* **timetracker.database-url**: URL-адрес базы данных для хранения данных о времени выполнения методов.
* **timetracker.database-username**: Имя пользователя базы данных.
* **timetracker.database-password**: Пароль базы данных.

**Пример использования**

В следующем примере показано, как использовать аннотации `@TrackTime` и `@TrackAsyncTime` для отслеживания времени выполнения методов:

```java
import org.petukhov.timetracker.annotation.TrackAsyncTime;
import org.petukhov.timetracker.annotation.TrackTime;

public class ExampleService {

    @TrackTime
    public void syncMethod() {
        // ...
    }

    @TrackAsyncTime
    public void asyncMethod() {
        // ...
    }
}
```

**Лицензия**

Этот проект может использоваться в учебных целях.