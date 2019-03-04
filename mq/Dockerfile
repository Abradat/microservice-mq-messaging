FROM ibmcom/mq

RUN useradd shayan -G mqm && echo shayan:passw0rd | chpasswd
COPY config.mqsc /etc/mqm/