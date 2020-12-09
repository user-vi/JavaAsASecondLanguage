# Deploying sample application to Google Cloud
It's easier than you might think!

1. Create your service and push to GitHub repository
:) 

2. Make Dockerfile for you application

3. Build Docker image and push to project’s image repository
git clone https://github.com/JavaAsASecondLanguage/JavaAsASecondLanguage.git
git checkout lecture10
cd cloud

## Build project
./gradlew clean assemble

## Build image. Note that gcr.io/made2020/ inside tag indicates that image will be pushed to project’s image repository  
docker build . --tag gcr.io/made2020/cloud-sample:1.0

## Push image to local repository
docker push gcr.io/made2020/cloud-sample:1.0

4. Deploy workload with given repository
https://console.cloud.google.com/kubernetes/workload?cloudshell=true&project=made2020  
workloads


5. Expose 8080 port on static ip using LoadBalancer service in Kubernetes
https://console.cloud.google.com/kubernetes/workload?cloudshell=true&project=made2020  
services and Ingress

6. Check you service on public API