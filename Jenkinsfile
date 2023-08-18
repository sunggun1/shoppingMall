pipeline {
  environment {
    dockerimagename = "sunggun1/kubernetes-spring-mysql-demo"
    dockerImage = ""
  }
  agent any
  stages {
    stage('Checkout Source') {
      steps {
           echo 'pulling...' + env.BRANCH_NAME
           git branch: 'main', credentialsId: 'github-credentials', url: 'https://github.com/sunggun1/shoppingMall.git'
//         git 'https://github.com/sunggun1/shoppingMall.git'
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
            sh 'ls -al'
            sh 'ls -al ~/.kube'
            sh 'aws eks update-kubeconfig --region ap-northeast-2 --name kub-dep-demo2'
            sh 'chown -R jenkins: ~jenkins/.kube/'

            sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'
            sh 'chmod u+x ./kubectl'
            sh './kubectl get pods'
            sh "./kubectl apply -f spring-deployment.yaml"
        }
      }
    }
  }
}