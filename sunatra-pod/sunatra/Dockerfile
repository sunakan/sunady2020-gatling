ARG DOCKER_RUBY_TAG
FROM ruby:${DOCKER_RUBY_TAG}

WORKDIR /app

COPY Gemfile* ./

RUN bundle install

COPY app.rb ./
