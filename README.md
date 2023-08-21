# shoppingMall

백엔드 : spring boot, gradle, jpa, querydsl, aws s3 <br>
프론트 : thymeleaf, bootstrap <br>
데브옵스 : kubernetes(aws eks), jenkins, docker <br>
데이터베이스 : aws RDS <br>

<br>
쿠버네티스를 이용해 만든 스프링부트 쇼핑몰 입니다. 
현재 aws s3, rds , 쿠버네티스, 젠킨스 연결 완료 했습니다.
<br>
이제 aws elasticache(redis 세션)을 사용하여 쿠버네티스 load balancing을 위해 세션 클러스터링을 작업하겠습니다.
<br>
<br>

쿠버네티스 확인 가능 URL : af41fff9b136f46eebde72b0e2dda2cf-1953978268.ap-northeast-2.elb.amazonaws.com:8080

1. 로그인 페이지 
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/b70cd36e-e087-47c8-bade-4b3e3e659f4f)


<br>
2.회원가입 페이지 
<br>

![image](https://github.com/sunggun1/shoppingMall/assets/17981550/293036da-39c4-4b69-b2b6-7109c7d5f3da)


<br>
3. 관리자 상품 등록 페이지
<br>

![image](https://github.com/sunggun1/shoppingMall/assets/17981550/97bc9425-50ab-4a71-8565-4fd95810b29b)
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/7e238107-04da-478a-854f-a88085d9ad30)

<br>
4. 관리자 메인 화면
<br>

![image](https://github.com/sunggun1/shoppingMall/assets/17981550/6023459a-cb83-4048-a360-03279704452c)
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/5a8fec9e-8ddc-4177-ae0a-078000ca3911)

<br>
5. 상품 상세 페이지
<br>

![image](https://github.com/sunggun1/shoppingMall/assets/17981550/843f9d01-1314-4de5-92d9-70f18ca334c7)
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/a44b7879-953f-40d0-8436-dbe39c8518c0)

<br>

6. 상품 관리 페이지 <br>
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/39a389f5-2571-4c2d-822f-e190065eec5b)

7. 상품 수정 페이지 <br>
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/0d647a58-b61d-4fcb-9b1b-014f880739bc)


8. 상품 주문 및 구매 이력 페이지 <br>
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/82b3e142-f25d-4122-bb7a-64f224cba193)
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/ba2a604a-9361-4599-aecf-c2b32c04ddc1)

10. 상품 주문 취소 클릭 시 <br>
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/7bbdbb90-342c-4acd-a455-1e05d3c7a8fb)
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/808f6994-1ac7-4db9-b78b-638d0557703f)

11. 일반 유저 로그인 메인 페이지 <br>
![image](https://github.com/sunggun1/shoppingMall/assets/17981550/8ccca914-a0d2-4aa5-9069-1dba4f7d1c0a)



<br>
<br>
<br>
<br>
<br>
kubernetes dashboard 확인 방법
kubectl proxy
aws eks get-token --cluster-name kub-dep-demo2 | jq -r '.status.token'
<br><br>

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
