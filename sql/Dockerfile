# Verwende das offizielle MySQL-Image als Basis
FROM mysql:latest

# Setze Umgebungsvariablen für die Datenbanken und Benutzer
ENV MYSQL_DATABASE1=category_service \
    MYSQL_DATABASE2=product_service \
    MYSQL_DATABASE3=user_service \
    MYSQL_ROOT_PASSWORD=root

# Führe SQL-Skripte aus, um Datenbanken und Benutzer zu erstellen
COPY create-tables.sql /docker-entrypoint-initdb.d/create-tables.sql
COPY initial.sql /docker-entrypoint-initdb.d/initial.sql
