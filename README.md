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

# Webshop Microservices - Kubernetes Anleitung

## Voraussetzungen

- Installiertes Docker
- Installiertes Kubernetes
- Installiertes Minikube und Kubectl

## Starten des Clusters

Starte zuerst Minikube mit dem Befehl (gegebenfalls muss noch die Flag `--driver=docker` hinzugefügt werden):

```
minikube start
```

Um das `PersistenctVolume` und den `PersistentVolumeClaim` zu deployen, nutze folgenden Befehl in einem Terminal, während du im Root-Ordner des Projekts bist:

```
kubectl apply -f .\sql\mysql-pv.yaml
```

Prüfe, dass das Deployment erfolgreich war, indem du folgende Befehle ausführst:

```
kubectl get pv
kubectl get pvc
```

Um die Services zu deployen, nutze folgenden Befehl in einem Terminal, während du im Root-Ordner des Projekts bist:

```
kubectl apply -f microservices.yaml
```

Dies startet alle Pods und Services, die in der microservices.yaml definiert sind.

Falls die Docker Images (Apache, Product, Category, User und MySQL) vom entsprechenden Repository, welches in der `microservices.yaml` Datei spezifiziert ist, nicht gepullt werden können, nehme folgende Schritte vor.

Baue die Docker Images selbst, indem du dich über das Terminal bei Docker einloggst und folgende Befehle aus dem Root-Ordner ausführst:

```
# Für das Bauen des MySQL Docker Images muss der Pfad lediglich sql lauten

docker build --tag=<app_name> microservices/<app_name> --no-cache
docker tag <app_name> <dein_docker_username>/<app_name>:latest
docker push <dein_docker_username>/<app_name>
```

Ändere dann die Image-Pfade, von denen du in der `microservices.yaml` Datei pullst, entsprechend ab.

Nach dem Ausführen des vorherigen Befehls sollten die Pods und Services starten und du kannst den Status entsprechend mit diesen Befehlen verfolgen:

```
kubectl get pods
kubectl get services
```

Du hast auch die Möglichkeit, genauere Statistiken anzusehen, eine Bash im Pod zu öffnen (und beispielsweise Error-Logs anzuschauen) und die standardmäßigen Logs eines Pods auszulesen. Für weitere Details, klicke [hier](https://kubernetes.io/docs/reference/kubectl/quick-reference/).

## Zugriff auf das Cluster

Um den Apache Reverse Proxy zu erreichen, muss dieser auf dem entsprechenden Port zunächst forwarded werden.

Führe dafür folgenden Befehl aus:

```
kubectl port-forward service/apache 8001:80
```

Nun sollte der Proxy auf [localhost:8001](http://localhost:8001) verfügbar sein.

Von dort aus solltest du wie gewohnt die Microservices aufrufen können, z.B.:

- [localhost:8001/users/exists?username=admin](http://localhost:8001/users/exists?username=admin)
- [localhost:8001/categories](http://localhost:8001/categories)
- [localhost:8001/product/3](http://localhost:8001/product/3)

## Das Cluster updaten

Falls du Änderungen vorgenommen hast, müssen dafür die Docker Images für die entsprechenden Microservices neu gebaut werden. Schaue dir hierfür die Befehle in [Starten des Clusters](#starten-des-clusters) an.
Wenn du die Docker Images dann in das Docker Repository pushst, kann es sein, dass zuvor das alte Image gelöscht werden muss.

Sobald die neuen Images gebaut und gepusht sind, lösche das aktuelle Setup auf dem Cluster und starte es neu:

```
kubectl delete -f microservices.yaml
kubectl apply -f microservices.yaml
```

Da die `imagePullPolicy: Always` ist, werden dabei immer die Images gepullt, unabhängig davon, ob noch welche gecached sind.

# Istio Konfiguration

## Istio einrichten

Folge den Anleitungen auf [dieser Seite](https://istio.io/latest/docs/setup/getting-started/#download), um Istio herunterzuladen und installiere das Demo-Profil:

```
istioctl install --set profile=demo -y
```

Um die erforderlichen Dashboards und Visualisierungen erstellen und anschauen zu können, müssen folgende Installationen/Deployments vorgenommen werden:

```
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.21/samples/addons/grafana.yaml
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.21/samples/addons/prometheus.yaml
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.21/samples/addons/kiali.yaml
```

Weise dem default namespace anschließend folgende Konfiguration zu und überprüfe die zugewiesenen Labels:

```
kubectl label namespace default istio-injection=enabled
kubectl describe namespace default
```

Deploye alles neu, indem du folgende Befehle ausführst:

```
kubectl delete -f microservices.yaml
kubectl apply -f microservices.yaml
```

Um nun zu prüfen, dass der Istio Sidecar Injector korrekt läuft, führe folgenden Befehl aus:

```
kubectl get pods -n istio-system
```

Dort sollten dann sowohl die istio-Pods, als auch die Pods für Grafana, Prometheus und Kiali aufgelistet werden.

### Prometheus & Grafana Dashboard

Um das Prometheus & Grafana Dashboard zu öffnen, führe folgenden Befehl aus:

```
istioctl dashboard grafana
```

Oder wenn du istioctl als Executable heruntergeladen hast:

```
./istioctl.exe dashboard grafana
```

Navigiere dort zu den Dashboards und wähle `Istio Service Dashboard`. Setze ein paar Requests an die Microservices ab. Du kannst den Microservice unter dem `Service`-Reiter auswählen und die Workloads der Microservices dann dort betrachten und analysieren.

### Kiali Dashboard

Um das Kiali Dashboard zu öffnen, führe folgenden Befehl aus:

```
istioctl dashboard kiali
```

Oder wenn du istioctl als Executable heruntergeladen hast:

```
./istioctl.exe dashboard kiali
```

Navigiere dort zu dem Reiter `Graph`. Dort kannst du, nachdem du Requests an die Microservices abgesetzt hast und diese registriert wurden, die Topologie des Netzes, beziehungsweise das Service Mesh sehen und analysieren.
