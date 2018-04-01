
pipeline {
    agent {
        docker {
            image 'maven:3.5-jdk-8-alpine'
            image 'openjdk:8-jre-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                     junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                sh 'java -jar /target/master-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}