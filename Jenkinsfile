pipeline {
    agent any

    environment {
        IMAGE_NAME = "springboot-app"
        CONTAINER_NAME = "springboot-app"
        APP_PORT = "9090"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/kaif-abbas123/Demo-Project.git',
                    credentialsId: 'github-creds'
            }
        }

        stage('Build with Maven') {
            agent {
                docker {
                    image 'maven:3.9.9-eclipse-temurin-17'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                docker run -d -p $APP_PORT:9090 --name $CONTAINER_NAME $IMAGE_NAME
                '''
            }
        }
    }
}
