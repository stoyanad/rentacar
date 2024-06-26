# RentACar Service

This repository contains the RentACar service, a microservice for managing car rentals.

## Prerequisites

Before running the RentACar service on Kubernetes, make sure you have the following installed:

- Kubernetes cluster (e.g., Minikube, Docker Desktop with Kubernetes enabled, or a cloud provider like GKE, AKS, or EKS)
- `kubectl` command-line tool installed and configured to communicate with your Kubernetes cluster

# Run RentACar application locally

``
mvn clean install
``
``
mvn spring-boot:run
``

## See Swagger documentation
http://localhost:8080/swagger-ui.html

## Login and obtain Bearer token
``
curl -X POST "http://localhost:8080/users/login" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"username\": \"admin\", \"username\": \"password\"}"
``
Include the token in Authorization header to make subsequent requests.

# Setup RentACar application with MySql Database

Install Docker For Mac/Windows/Linux
#### Setup
Set JAVA_HOME
Set M2_HOME
Add M2_HOME/bin to the execution path
mvn package -DskipTests
#### Docker Commands
##### Start MySql Container (downloads image if not found)
``
docker run  --detach   --name rc-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=rentacar -e MYSQL_USER=<user> -e MYSQL_PASSWORD=<pass> -d mysql
``

##### view all images
``
docker images
``

##### view all containers (running or not)
``
docker ps -a
``
##### Interact with Database (link to rc-mysql container) with mysql client
``
docker run -it --link rc-mysql:mysql --rm mysql sh -c 'exrc mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``
##### Stop rc-mysql container
``
docker stop rc-mysql
``
##### (ReStart) rc-mysql container
``
docker start rc-mysql
``
##### Remove rc-mysql container (must stop it first)
``
docker rm rc-mysql
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest
``
#### Startup with Profile settings
##### Default profile, H2 database
``
mvn spring-boot:run
``

or

``
java  -jar target/rentacar-1.0-SNAPSHOT.jar
``
##### mysql profile, MySql database (requires running container rc-mysql)
``
mvn spring-boot:run -Dspring.profiles.active=mysql 
``

or

``
java  -Dspring.profiles.active=mysql -jar target/rentacar-1.0-SNAPSHOT.jar
``
#### How to run the app on Docker
##### Build jar, image, set default profile
``
mvn package -DskipTests docker:build 
``
###### container with default property set in Dockerfile
``
docker run --name rc-app-default -p 8080:8080  -d rentacar-default
``
##### Build jar, image, set mysql profile
``
mvn package -DskipTests docker:build -Drc-profile=mysql
``
##### Run Docker container with mysql profile
``
docker run    --name rc-app-mysql -p 8181:8080  --link rc-mysql:mysql -d rentacar-mysql
``
##### Build jar, image, set docker profile
``
mvn package -DskipTests docker:build -Drc-profile=docker
``
##### Run Docker container with docker profile set in Dockerfile and migration scripts on host
``
docker run --name rc-app-docker -p 8282:8080 -v ~/db/migration:/var/migration -e server=rc-mysql -e port=3306 -e dbuser=<user> -e dbpassword=<password> --link rc-mysql:mysql -d rentacar-docker
``
##### Enter Docker container
``
docker exrc -t -i rc-app /bin/bash
``
#####
##### Push image to Docker hub
###### Login to Docker hub locally
``docker login``
###### Upload image
``
docker tag <image id> <docker hub repository>/rentacar-default:latest
``
###### Download image
``
docker pull <docker hub repository>/rentacar-default
``
##### Run Container from docker hub image
``
docker run --name rc-app-default -p 8080:8080  -d <docker hub repository>/rentacar-default``

## Running on Kubernetes

Follow these steps to deploy the RentACar service on Kubernetes:

1. Clone this repository:

    ```bash
    git clone https://github.com/your-username/rentacar.git
    ```

2. Navigate to the repository directory:

    ```bash
    cd rentacar
    ```

3. Build the Docker image for the RentACar service:

    ```bash
    docker build -t rentacar:latest .
    ```

4. Tag the Docker image with your Docker repository URL (if applicable):

    ```bash
    docker tag rentacar:latest your-docker-repo/rentacar:latest
    ```

5. Push the Docker image to your Docker repository (if applicable):

    ```bash
    docker push your-docker-repo/rentacar:latest
    ```

6. Apply the Kubernetes deployment and service manifests:

    ```bash
    kubectl apply -f kubernetes/
    ```

7. Verify that the RentACar pods are running:

    ```bash
    kubectl get pods
    ```

8. Once the pods are running, you can access the RentACar service using the cluster IP or node port (depending on your Kubernetes setup).

## Accessing the Service

To access the RentACar service, you need to find out the service's URL. Follow these steps:

1. Get the external IP address of the service (for services of type `LoadBalancer`):

    ```bash
    kubectl get svc rentacar-service
    ```

2. If the service is of type `NodePort` or `LoadBalancer`, you can access it using the external IP address and port.

3. If the service is of type `ClusterIP`, you can access it using port forwarding:

    ```bash
    kubectl port-forward service/rentacar-service 8080:8080
    ```

   Then, access the service at `http://localhost:8080`.

## Cleaning Up

To clean up the RentACar service from your Kubernetes cluster, run the following command:

   ```bash
kubectl delete -f kubernetes/
   ```


