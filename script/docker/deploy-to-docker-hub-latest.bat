REM Package application
call mvn clean package -f ../../pom.xml

REM stop running containers
call docker-compose -f docker-compose.yml down

REM remove old images
call docker rmi cykcorp/poulsscolaireapi

REM build new image
call docker build -f ../../src/main/docker/Dockerfile.jvm --tag "cykcorp/poulsscolaireapi:latest" ../../
call docker tag poulsscolaireapi cykcorp/poulsscolaireapi

call push-to-docker-hub-latest.bat