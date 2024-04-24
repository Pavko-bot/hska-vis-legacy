# Webshop Microservices - Docker-Compose Anleitung
Willkommen zur README-Datei für unseren Webshop mit Microservices. Diese Anleitung erklärt, wie du das Projekt mit Docker-Compose starten, stoppen und die zugehörigen Services aufrufen kannst.

## Voraussetzungen
- Installiertes Docker
- Installiertes Docker-Compose

## Starten des Docker-Compose
Um die Microservices zu starten, nutze folgenden Befehl im Terminal, während du im Root-Ordner des Projekts bist:

~~~
docker-compose -f docker-compose-local.yml up
~~~

Dies startet alle Microservices, die in der docker-compose-local.yml definiert sind.

## Herunterfahren des Docker-Compose
Um die Microservices herunterzufahren, ohne die Volumes zu löschen, kannst du folgenden Befehl verwenden:

~~~
docker-compose -f docker-compose-local.yml down
~~~

Dies stoppt die Microservices, ohne Daten oder Konfiguration zu löschen.

Wenn du alle Volumes löschen willst, nutze:

~~~
docker-compose -f docker-compose-local.yml down --volumes
~~~

## Erreichbarkeit der Microservices
Nach dem Starten des Docker-Compose sind die Microservices unter folgenden URLs erreichbar:

- Usermanagement: http://localhost:8080
- Categorymanagement: http://localhost:8081
- Productmanagement: http://localhost:8082
