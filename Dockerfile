
FROM tomcat
WORKDIR /usr/local/tomcat
COPY *.war  webapps
CMD catalina.sh run
