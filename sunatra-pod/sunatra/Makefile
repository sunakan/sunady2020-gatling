export DOCKER_RUBY_TAG=2.7-slim

build:
	docker build ./ \
    --tag sunatra:latest \
    --build-arg DOCKER_RUBY_TAG=${DOCKER_RUBY_TAG}

bash:
	docker run \
    --rm \
    --interactive \
    --tty \
    --publish 5678:5678 \
    --mount type=bind,source=${PWD}/,target=/app \
    sunatra:latest \
    bash

up:
	docker run \
    --rm \
    --interactive \
    --tty \
    --publish 4567:4567 \
    --name sunatra \
    sunatra:latest \
    bundle exec ruby ./app.rb -o 0.0.0.0
