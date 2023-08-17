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
    stage('Deploying SpringBoot container to Kubernetes') {
      steps {
        sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.28.0/bin/linux/amd64/kubectl"'
        sh 'chmod u+x ./kubectl'
        sh 'cp ~/.kube/config ~jenkins/.kube/'
        sh 'chown -R jenkins: ~jenkins/.kube/'
        sh './kubectl apply -f spring-deployment.yaml'
      }
    }
  }
}