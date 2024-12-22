FROM ubuntu:latest
LABEL authors="poom"

ENTRYPOINT ["top", "-b"]