# user-live-search
User live-search using Spring Boot, MongoDB and Angular

## Docker

### Spring
``mvn clean install
``
``docker build -t spring_docker_image
``
``docker images  | grep "spring"
``
### MongoDB

``docker pull mongo
``
``docker run -d --name mongo-on-docker -p 27017:27017 mongo
``
- -d run the container in the background so that we are free to use the current terminal instance.
- --name mongo-on-docker defines a friendly name for the container.
- -p 27017:27017 declares that the local port 27017 is mapped to the container internal 27017 port.
mongo this signifies the name of the image to run

### General

``docker container ls -a``

``docker logs <ContainerID>``