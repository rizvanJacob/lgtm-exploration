FROM grafana/grafana:latest
ADD provisioning /etc/grafana/provisioning
ADD dashboards/*.json /var/lib/grafana/dashboards

ENV GF_AUTH_ANONYMOUS_ENABLED="true" \
      GF_AUTH_ANONYMOUS_ORG_ROLE="Admin" \
      GF_USERS_ALLOW_SIGN_UP="false" \
      GF_SECURITY_DISABLE_GRAVATAR="true" \
      GF_PATHS_PROVISIONING="/etc/grafana/provisioning" \
      PROMETHEUS_HOST="prometheus" \
      PROMETHEUS_PORT="9090"