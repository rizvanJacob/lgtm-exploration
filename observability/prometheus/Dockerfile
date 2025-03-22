FROM prom/prometheus:latest
ADD prometheus.yml /etc/prometheus/
ENV SERVER_HOST="spring-micrometer-app" \
    SERVER_PORT="8081"

#
#CMD ["--config.file=/etc/prometheus/prometheus.yml",
#    "--storage.tsdb.path=/prometheus",
#    "--web.console.libraries=/usr/share/prometheus/console_libraries",
#    "--web.console.templates=/usr/share/prometheus/consoles",
#    "--storage.tsdb.retention.size=2GB"]