# shoppingMall

쇼핑몰 입니다. 
aws s3, rds , 쿠버네티스, 젠킨스 연결 완료 했습니다.
이제 jwttoken, redis, elasticache를 만들겠습니다.
<br>
<br>

쿠버네티스 사용한 확인 URL : 다 만들고 올리겠습니다.

<br>
docker build -t sunggun1/kubernetes-spring-mysql-demo .
<br>
docker push sunggun1/kubernetes-spring-mysql-demo
<br>
kubectl delete -f .\app-deployment.yaml
<br>
kubectl apply -f .\app-deployment.yaml
<br>
kubectl get svc를 통해 url 확인 가능합니다.

kubernetes dashboard 확인 방법
kubectl proxy 
aws eks get-token --cluster-name kub-dep-demo2 | jq -r '.status.token'
<br><br>
jenkins multibranch file 만들기
jenkins 파일 docker 읽을 수 있게 만들기
<br>



docker pipeline, kubernetes, kubernetes-credentials 설치

<br>
https://velog.io/@chang626/docker-container%EC%97%90%EC%84%9C-docker-image-%EB%B9%8C%EB%93%9C-%EC%A7%84%ED%96%89-%EA%B3%BC%EC%A0%95-jenkins-host-docker.sock%EC%9D%84-%EC%97%B0%EA%B2%B0-2
<br>
https://sweetcode.io/how-to-deploy-an-application-to-kubernetes-cluster-using-jenkins-ci-cd-pipeline/
<br>
https://waspro.tistory.com/707
<br>
https://kanoos-stu.tistory.com/53
<br>


젠킨스 cluster role 생성
<br>
kubectl create serviceaccount jenkins-deployer
<br>
kubectl create clusterrolebinding jenkins-deployer-role --clusterrole==cluster-admin --serviceaccount=default:jenkins-deployer
<br>

이제 젠킨스 elasticache를 붙일 예정입니다.
