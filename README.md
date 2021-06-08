[![Open Source](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://GitHub.com/Ryzeon/rImgServer)
[![](https://jitpack.io/v/Ryzeon/JDA-CommandAPI.svg)](https://jitpack.io/#Ryzeon/JDA-CommandAPI)

# JDA-CommandAPI

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white">

# How to use: 
  * Add dependency
    * Maven
    ```xml
    <repositories>
        <repository>
		<id>ryzeon-repo</id>
		<url>http://repo.ryzeon.me/repository/ryzeon</url>
        </repository>
    </repositories>
    ```
    ```xml
    <dependency>
        <groupId>me.ryzeon.command</groupId>
        <artifactId>JDA-CommandAPI</artifactId>
        <version>1.2-SNAPSHOT</version>
    </dependency>
    ```
    * Gradle
    ```groovy
    repositories {
        maven { url 'http://repo.ryzeon.me/repository/ryzeon' }
    }
    ```
    ```groovy
    dependencies {
        implementation 'me.ryzeon.command:JDA-CommandAPI:1.2'
    }
    ```
    
   * [**Example Command**](src/examples/java/PingCommadExample.java)
   * [**How to register**](src/examples/java/Bot.java)

