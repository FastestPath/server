FROM java:8
ADD build/libs/fastestpath-1.0-all.jar fastestpath.jar
ADD production.yml production.yml
CMD java -jar fastestpath.jar server production.yml
EXPOSE 9000