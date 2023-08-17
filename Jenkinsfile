pipeline {
  environment {
    dockerimagename = "sunggun1/kubernetes-spring-mysql-demo"
    dockerImage = ""
    EKS_API = "https://91A7A2184E247ACEC79B0767D3F203B1.yl4.ap-northeast-2.eks.amazonaws.com"
    EKS_JENKINS_CREDENTIAL_ID = 'kubectl-deploy-credentials'
    EKS_CLUSTER_NAME = "kub-dep-demo2"
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
        withKubeConfig([credentialsId: "{EKS_JENKINS_CREDENTIAL_ID}",
                              serverUrl: "${EKS_API}",
                              clusterName: "${EKS_CLUSTER_NAME}"])
        {
          sh "kubectl apply -f spring-deployment.yaml"
        }
      }
    }
  }
}