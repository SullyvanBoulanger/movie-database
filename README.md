# Movie database exercise

This is an exercise from a learning course.

[Exercise's Instructions (in French)](./instructions.md)

## Installation

**Prerequisites**

- Java 21
- Maven
- (Optional) Docker Compose to run database

1. Clone this project
```sh
git clone git@github.com:SullyvanBoulanger/movie-database.git
```
## Launch

1. Run Docker or edit `src/main/resources/META-INF/persistence.xml`
```sh
docker compose up -d
```

Or edit this by changing `your-...` values

```xml
    <persistence-unit name="drop-and-create-movies" transaction-type="RESOURCE_LOCAL">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <properties>
            <property name="jakarta.persistence.jdbc.url" value="your-url" />
            <property name="jakarta.persistence.jdbc.user" value="your-username" />
            <property name="jakarta.persistence.jdbc.password" value="your-password" />
            <property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>
        </properties>
    </persistence-unit>
    <persistence-unit name="movies" transaction-type="RESOURCE_LOCAL">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <properties>
            <property name="jakarta.persistence.jdbc.url" value="your-url" />
            <property name="jakarta.persistence.jdbc.user" value="your-username" />
            <property name="jakarta.persistence.jdbc.password" value="your-password" />
        </properties>
    </persistence-unit>
```

2. Run script you want
   1. **ParseJSONFile.java** to drop database, create tables and fill by parsing JSON File
   2.  **Menu.java** to search in database from a dedicated menu in terminal