.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

installDist:
	./gradlew clean installDist

run-dist:
	./build/install/app/bin/app ./src/test/resources/file1.json ./src/test/resources/file2.json

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions

build-run: build run

.PHONY: build