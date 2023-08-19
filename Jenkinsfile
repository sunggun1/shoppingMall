pipeline {
  environment {
    dockerimagename = "sunggun1/kubernetes-spring-mysql-demo"
    dockerImage = ""
  }
  agent any
  stages {
    stage('Checkout Source') {
      steps {
           git branch: 'main', credentialsId: 'github-credentials', url: 'https://github.com/sunggun1/shoppingMall.git'
      }
    }
    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }
    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhub-credentials'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("latest")
          }
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
         sh "docker rmi registry.hub.docker.com/$dockerimagename:latest"
      }
    }
    stage('Deploying SpringBoot container to Kubernetes') {
      steps {

        withAWS([credentials: 'aws-credentials']) {
            sh 'aws eks update-kubeconfig --region ap-northeast-2 --name kub-dep-demo2'
            sh 'chown -R jenkins: ~/.kube/'
            sh 'kubectl get pods'
            sh "kubectl apply -f spring-deployment.yaml"
        }
      }
    }
  }
}