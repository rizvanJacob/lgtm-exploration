FROM grafana/grafana:latest
ADD provisioning /etc/grafana/provisioning

ARG PROMETHEUS_DS_NAME="Spring Micrometer App"

COPY dashboards/*.json /tmp/dashboards/
RUN mkdir -p /var/lib/grafana/dashboards && \
    for f in /tmp/dashboards/*.json; do \
      sed "s/\\\${DS_PROMETHEUS}/${PROMETHEUS_DS_NAME}/g" "$f" > /var/lib/grafana/dashboards/$(basename "$f"); \
    done

ENV GF_AUTH_ANONYMOUS_ENABLED="true" \
      GF_AUTH_ANONYMOUS_ORG_ROLE="Admin" \
      GF_USERS_ALLOW_SIGN_UP="false" \
      GF_SECURITY_DISABLE_GRAVATAR="true" \
      GF_PATHS_PROVISIONING="/etc/grafana/provisioning" \
      PROMETHEUS_DS_NAME=${PROMETHEUS_DS_NAME} \
      PROMETHEUS_HOST="prometheus" \
      PROMETHEUS_PORT="9090"