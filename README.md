# shoppingMall

쇼핑몰 입니다. 
aws s3, rds , 쿠버네티스 연결 완료 했습니다.

코드 변경 후 아래와 같이 수행하기.

쿠버네티스 사용한 확인 URL
http://ab08df92535924a6e99bb8fd0c68aefa-1205337726.ap-northeast-2.elb.amazonaws.com:8080/

docker build -t sunggun1/kubernetes-spring-mysql-demo .
docker push sunggun1/kubernetes-spring-mysql-demo

kubectl delete -f .\app-deployment.yaml
kubectl apply -f .\app-deployment.yaml

kubectl get svc를 통해 url 확인 가능합니다.

kubernetes dashboard 확인 방법

kubectl proxy 
aws eks get-token --cluster-name kub-dep-demo2 | jq -r '.status.token'

이제 젠킨스 elasticache를 붙일 예정입니다.
