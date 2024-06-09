pipeline {
    agent any
    tools{
        gradle 'gradle'
    }
    stages{
        stage('Build Gradle'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rlckyyy/final-sre-project']])
                sh 'gradle clean build'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t reluckyyy/srefinal .'
                }
            }
        }
        stage('push docker') {
            steps {
                withCredentials([string(credentialsId: 'DOCKER_PASS', variable: 'DOCKER_PASS')]) {
                    sh 'docker login -u reluckyyy -p ${DOCKER_PASS}'
                    sh 'docker push reluckyyy/srefinal'
                }
            }
        }
        stage('deploy k8s') {
            steps {
                sh 'kubectl delete deployment spring-boot-app'
                sh 'kubectl apply -f k8s/kube.yaml'
                sh 'kubectl apply -f k8s/postgres.yaml'
                sh 'kubectl apply -f k8s/spring-app.yaml'
            }
        }
    }
}