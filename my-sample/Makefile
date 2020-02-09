export DOCKER_GATLING_TAG=3.2.1
export MY_NETWORK=my-network

ash:
	mkdir -p gatling-results
	docker run \
    --rm \
    --interactive \
    --tty \
    --entrypoint "" \
    --net ${MY_NETWORK} \
    --mount type=bind,source=${PWD}/gatling-results,target=/opt/gatling/results \
    --mount type=bind,source=${PWD}/gatling/user-files,target=/opt/gatling/user-files \
    denvazh/gatling:${DOCKER_GATLING_TAG} \
    ash
#--mount type=bind,source=${PWD}/gatling/user-files,target=/opt/gatling/user-files \

app: shared-network
	docker run \
    --rm \
    --interactive \
    --tty \
    --publish 4567:4567 \
    --name sunatra \
    --net ${MY_NETWORK} \
    sunatra:latest \
    bundle exec ruby ./app.rb -o 0.0.0.0

nginx: shared-network
	mkdir -p gatling-results
	docker run \
    --rm \
    --interactive \
    --tty \
    --publish 80:80 \
    --name nginx \
    --net ${MY_NETWORK} \
    --env LANG=C.UTF-8 \
    --mount type=bind,source=${PWD}/nginx/nginx.conf,target=/etc/nginx/conf.d/default.conf \
    --mount type=bind,source=${PWD}/gatling-results,target=/usr/local/bin/gatling/results \
    nginx:stable

shared-network:
	docker network ls | grep ${MY_NETWORK} || docker network create ${MY_NETWORK}

clean:
	docker container prune
	docker network prune
	rm -rf ./gatling-results/*
