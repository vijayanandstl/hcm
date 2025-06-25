# HCM Service Access Architecture

## Overview
All HCM services have been updated to prevent external access and ensure they are only accessible through the hcm-core service using nginx ingress controller.

## Architecture Changes

### 1. Service Access Pattern
- **External Access**: Only through `hcm-core.local` via nginx ingress controller
- **Internal Access**: All services use ClusterIP (no NodePort exposure)
- **Routing**: hcm-core acts as the API gateway for all services

### 2. Updated Services
All services now use `ClusterIP` instead of `NodePort`:

- **candidate-service**: Port 8099 (ClusterIP only)
- **organization-service**: Port 8100 (ClusterIP only)  
- **tenant-service**: Port 8098 (ClusterIP only)
- **hcm-core**: Port 8085 (ClusterIP only)

### 3. Ingress Configuration
The hcm-core service includes an ingress configuration that routes traffic to all services:

```yaml
Host: hcm-core.local
Routes:
- / -> hcm-core:8085
- /api/candidates -> candidate-service:8099
- /api/organizations -> organization-service:8100
- /api/tenants -> tenant-service:8098
```

### 4. Network Access
- **External**: `http://hcm-core.local` (via nginx ingress on port 80)
- **Internal**: Services communicate via Kubernetes service names within the cluster
- **No Direct External Access**: Services cannot be accessed directly from outside the cluster

### 5. Deployment Process
1. **Individual Services**: Deploy with ClusterIP services (no ingress)
2. **hcm-core**: Deploys with ingress configuration for external access
3. **All traffic**: Routes through hcm-core to appropriate services

### 6. Security Benefits
- **Reduced Attack Surface**: Only one external entry point
- **Centralized Access Control**: All requests go through hcm-core
- **Internal Communication**: Services communicate securely within cluster
- **No Direct Service Exposure**: Individual services are not accessible externally

### 7. DNS Configuration
The host `hcm-core.local` is configured in `/etc/hosts`:
```
10.19.5.50 hcm-core.local
```

### 8. Nginx Ingress Controller
- **Namespace**: nginx
- **Service**: nginx-service (NodePort 30010)
- **Ingress Class**: nginx
- **External Access**: Port 80 via nginx ingress

## Usage Examples

### External API Access
```bash
# Access candidate service through hcm-core
curl http://hcm-core.local/api/candidates

# Access organization service through hcm-core  
curl http://hcm-core.local/api/organizations

# Access tenant service through hcm-core
curl http://hcm-core.local/api/tenants

# Access hcm-core directly
curl http://hcm-core.local/
```

### Internal Service Communication
Services can communicate with each other using Kubernetes service names:
- `candidate-service:8099`
- `organization-service:8100`
- `tenant-service:8098`
- `hcm-core:8085`

## Deployment Notes
- Run hcm-core pipeline first to establish ingress
- Individual service pipelines deploy only internal services
- All services must be in the same namespace (`hcm`)
- Ingress is managed only by hcm-core deployment 