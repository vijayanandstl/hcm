# Kafka Cluster Setup Guide

This guide explains how to set up and run the Kafka cluster both locally using Docker Compose and in production using Kubernetes.

## Local Development Setup (Docker Compose)

The local setup includes:
- 3 Kafka brokers
- 1 ZooKeeper instance
- Kafka UI for cluster management

### Prerequisites
- Docker
- Docker Compose

### Starting the Cluster

1. Navigate to the docker directory:
```bash
cd docker
```

2. Start the cluster:
```bash
docker-compose up -d
```

3. Verify the cluster is running:
```bash
docker-compose ps
```

4. Access Kafka UI:
- Open http://localhost:8080 in your browser

### Connection Details

- ZooKeeper: localhost:2181
- Kafka Brokers:
  - Broker 1: localhost:9092
  - Broker 2: localhost:9093
  - Broker 3: localhost:9094

### Stopping the Cluster

```bash
docker-compose down
```

## Production Setup (Kubernetes)

The Kubernetes setup includes:
- 3 Kafka brokers (StatefulSet)
- 1 ZooKeeper instance (StatefulSet)
- Kafka UI (Deployment)
- Persistent storage for both Kafka and ZooKeeper

### Prerequisites
- Kubernetes cluster
- kubectl configured
- Storage class for persistent volumes

### Deployment Steps

1. Create namespace (optional):
```bash
kubectl create namespace kafka
kubectl config set-context --current --namespace=kafka
```

2. Deploy ZooKeeper:
```bash
kubectl apply -f k8s/zookeeper.yaml
```

3. Deploy Kafka:
```bash
kubectl apply -f k8s/kafka.yaml
```

4. Deploy Kafka UI:
```bash
kubectl apply -f k8s/kafka-ui.yaml
```

### Verify Deployment

1. Check all pods are running:
```bash
kubectl get pods
```

2. Check services:
```bash
kubectl get svc
```

3. Access Kafka UI:
```bash
kubectl get svc kafka-ui
```
Use the external IP address displayed to access the UI.

### Connection Details

- ZooKeeper: zookeeper:2181
- Kafka Brokers (internal):
  - kafka-0.kafka-headless:9092
  - kafka-1.kafka-headless:9092
  - kafka-2.kafka-headless:9092

### Configuration

The Kafka cluster is configured with:
- Replication factor: 3
- Min In-Sync Replicas: 2
- Default number of partitions: 3

### Monitoring and Management

1. Using Kafka UI:
- Topic management
- Consumer group monitoring
- Message browsing
- Cluster configuration

2. Using kubectl:
```bash
# Get logs from a broker
kubectl logs kafka-0

# Describe a kafka pod
kubectl describe pod kafka-0

# Get ZooKeeper status
kubectl exec -it zookeeper-0 -- zkServer.sh status
```

### Scaling

To scale the number of Kafka brokers:
```bash
kubectl scale statefulset kafka --replicas=5
```

### Troubleshooting

1. If pods are pending:
- Check PVC status: `kubectl get pvc`
- Check storage class: `kubectl get sc`

2. If pods are crashing:
- Check logs: `kubectl logs <pod-name>`
- Check events: `kubectl get events`

3. Connection issues:
- Verify services: `kubectl get svc`
- Check endpoints: `kubectl get endpoints`

### Best Practices

1. Storage:
- Use SSD storage for better performance
- Allocate enough storage based on retention period and message volume

2. Monitoring:
- Set up monitoring for broker metrics
- Monitor disk usage
- Set up alerts for broker down scenarios

3. Backup:
- Regularly backup ZooKeeper data
- Consider using Kafka's mirroring features for disaster recovery

4. Security:
- Enable authentication and authorization
- Use TLS for inter-broker and client communication
- Implement network policies

### Using with Kafka Wrapper Library

Update the configuration in your application:

```java
KafkaConfig config = KafkaConfig.defaultBuilder()
    .bootstrapServers("kafka-0.kafka-headless:9092,kafka-1.kafka-headless:9092,kafka-2.kafka-headless:9092")
    .groupId("my-consumer-group")
    .clientId("my-client")
    .build();
``` 