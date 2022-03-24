FROM sonarqube

RUN sysctl -w vm.max_map_count=262144
RUN echo 'vm.max_map_count=262144' >> /etc/sysctl.conf
CMD echo 'Hello world'