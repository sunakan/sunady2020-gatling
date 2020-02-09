export DOCKER_GATLING_TAG=2.3.1
export MY_NETWORK=my-network

ash:
	docker run \
    --rm \
    --interactive \
    --tty \
    --entrypoint "" \
    --mount type=bind,source=${PWD}/gatling-results,target=/opt/gatling/results \
    denvazh/gatling:${DOCKER_GATLING_TAG} \
    ash

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
	docker run \
    --rm \
    --interactive \
    --tty \
    --publish 80:80 \
    --name nginx \
    --net ${MY_NETWORK} \
    --env LANG=C.UTF-8 \
    --mount type=bind,source=${PWD}/nginx.conf,target=/etc/nginx/conf.d/default.conf \
    --mount type=bind,source=${PWD}/gatling-results,target=/usr/local/bin/gatling/results \
    nginx:stable

shared-network:
	docker network ls | grep ${MY_NETWORK} || docker network create ${MY_NETWORK}

clean:
	docker container prune
	docker network prune
	rm -rf ./gatling-results/*
