FROM maven:3.8.2-openjdk-11

ARG BUILD_DATE
ARG VCS_REF
ARG VERSION
LABEL maintainer="Jose Badeau <jose.badeau@gmail.com>" \
      build-date=$BUILD_DATE \
      name="Gaugekit" \
      description="An alpine based maven image with Gaugekit installed for running BDD style gauge-java test suites." \
      vcs-ref=$VCS_REF \
      vcs-url="https://github.com/jbadeau/gaugekit.git" \
      version=$VERSION \
      schema-version="0.1.0"

# Install gauge
ARG GAUGE_VERSION=1.4.1
ENV GAUGE_VERSION $GAUGE_VERSION
ENV GAUGE_ARCHIVE gauge-$GAUGE_VERSION-linux.x86_64.zip
ENV GAUGE_TELEMETRY_ENABLED false
RUN set -ex && \
    wget -qnc "https://github.com/getgauge/gauge/releases/download/v$GAUGE_VERSION/$GAUGE_ARCHIVE" && \
    # example release url https://github.com/getgauge/gauge/releases/download/v1.0.0/gauge-1.0.0-linux.x86_64.zip
    unzip $GAUGE_ARCHIVE -d /usr/local/bin && \
    rm -rf $GAUGE_ARCHIVE && \
    # do not check for updates
    gauge config check_updates false && \
    # install gauge plugins
    gauge install java && \
    gauge install html-report && \
    gauge install xml-report && \
    gauge install screenshot && \
    gauge install reportportal && \
    gauge install xray-report

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]