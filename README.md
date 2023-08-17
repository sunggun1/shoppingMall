# shoppingMall

쇼핑몰 입니다. 
aws s3, rds , 쿠버네티스 연결 완료 했습니다.

코드 변경 후 아래와 같이 수행하기.

쿠버네티스 사용한 확인 URL
http://ab08df92535924a6e99bb8fd0c68aefa-1205337726.ap-northeast-2.elb.amazonaws.com:8080/
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
jenkins multi branch file 만들기
jenkins 파일 docker 읽을 수 있게 만들기
<br>
docker build -t jenkins-dood:1.0 -f jenkins-dockerfile .
<br>
docker run -dit -p 8084:8080 --name jenkinsServer2 -v /var/run/docker.sock:/var/run/docker.sock jenkins-dood:1.0
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

이제 젠킨스 elasticache를 붙일 예정입니다.
