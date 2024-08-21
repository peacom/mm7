# Peacom MM7

 - /lib/systemd/system
 - Folder: /usr/project/mm7
 - Configure: /usr/project/mm7/application.properties
 - Run File: /usr/project/mm7/mm7.jar
 - journalctl --follow -u mm7.service
 - sudo systemctl daemon-reload
 - sudo systemctl restart mm7.service
 - sudo keytool -import -alias mm7 -file /usr/projects/ssl/peacom.crt -keystore mycerts -storepass changeit

# Reference

https://docs.spring.io/spring-boot/docs/1.4.0.RC1/reference/html/deployment-install.html

https://sourabhbajaj.com/mac-setup/Java/sdkman.html