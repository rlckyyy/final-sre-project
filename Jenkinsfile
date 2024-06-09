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
//         stage('deploy k8s') {
//             steps {
//                 sh 'helm repo update metrics-server'
//                 sh 'helm repo add metrics-server https://kubernetes-sigs.github.io/metrics-server/'
//                 sh 'helm install metrics-server metrics-server/metrics-server -f ~/metrics-values.yml'
//                 sh 'kubectl apply -f k8s'
//             }
//         }
    }
}