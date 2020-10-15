Instruções para configurar o Kafka local:

Utilizar o comando "docker-compose up" na raiz do projeto'

Fazer Download do CLI do Kafka no link abaixo:
    https://www.apache.org/dyn/closer.cgi?path=/kafka/2.6.0/kafka_2.13-2.6.0.tgz

Descompactar o CLI do Kafka (Atentar para a versão do Kafka CLI) :    
    tar -xzf kafka_2.12-2.6.0.tgz

Mudar o Diretório (Atentar para a versão do Kafka CLI)cloudforma:
    cd kafka_2.12-2.6.0  

Criar o tópico:
    bin/kafka-topics.sh --create --topic api-spring-topic --bootstrap-server localhost:9092  

Abrir o Consumer:
    bin/kafka-console-consumer.sh --topic api-spring-topic --from-beginning --bootstrap-server localhost:9092   

Rodar a Aplicação.    