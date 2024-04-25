# Webshop Microservices - Docker-Compose Anleitung

Willkommen zur README-Datei für unseren Webshop mit Microservices. Diese Anleitung erklärt, wie du das Projekt mit Docker-Compose starten, stoppen und die zugehörigen Services aufrufen kannst.

## Voraussetzungen

- Installiertes Docker
- Installiertes Docker-Compose

## Starten des Docker-Compose

Um die Microservices zu starten, nutze folgenden Befehl im Terminal, während du im Root-Ordner des Projekts bist:

```
docker-compose -f docker-compose-local.yml up
```

Dies startet alle Microservices, die in der docker-compose-local.yml definiert sind.

## Herunterfahren des Docker-Compose

Um die Microservices herunterzufahren, ohne die Volumes zu löschen, kannst du folgenden Befehl verwenden:

```
docker-compose -f docker-compose-local.yml down
```

Dies stoppt die Microservices, ohne Daten oder Konfiguration zu löschen.

Wenn du alle Volumes löschen willst, nutze:

```
docker-compose -f docker-compose-local.yml down --volumes
```

## Erreichbarkeit der Microservices

Nach dem Starten des Docker-Compose sind die Microservices unter folgenden URLs erreichbar:

- Usermanagement: http://localhost:8080
- Categorymanagement: http://localhost:8081
- Productmanagement: http://localhost:8082

## Troubleshooting

### Fehlende Berechtigungen beim Starten des DBMS Containers

Falls du WSL unter Windows 10 18.03+ oder neuer verwendest, kann es sein, dass beim Starten des Databankmanagementsystems ein Fehler auftritt, in dem es um mangelnde Berechtigungen für Dateien im zugehörigen Volume geht.
Das hat den Grund, dass der mount-Punkt falsch konfiguriert ist bzw. Docker für Windows einen anderen Volume-Pfad erwartet.

Um das Problem zu lösen, kannst du ein WSL Terminal öffnen und folgende Änderungen an der `wsl.conf` Datei vornehmen:

```
sudo nano /etc/wsl.conf

# Füge diese Konfigurationen hinzu:
[automount]
root = /
options = "metadata"
```

Um sicherzugehen dass die Änderungen greifen, nehme die gleiche Konfiguration auch in der Datei `.wslconfig` vor. Diese findest du für gewöhnlich über den Windows Datei-Explorer in deinem Benutzerverzeichnis `C:\Users\<Dein-Nutzername>`.
