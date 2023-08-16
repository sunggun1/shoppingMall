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
           git branch: 'main', credentialsId: 'git-credentials', url: 'https://github.com/sunggun1/shoppingMall.git'
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
        script {
          kubernetesDeploy(configs: "spring-deployment.yaml")
        }
      }
    }
  }
}