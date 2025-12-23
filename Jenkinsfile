pipeline {
    agent any

    environment {
        IMAGE_NAME = "springboot-app"
        CONTAINER_NAME = "springboot-app"
        APP_PORT = "9090"
        GIT_CREDENTIALS = "github-creds" // your Jenkins Git credentials ID
        GIT_REPO = "https://github.com/kaif-abbas123/Demo-Project.git"
        GIT_BRANCH = "main"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: "${GIT_BRANCH}",
                    url: "${GIT_REPO}",
                    credentialsId: "${GIT_CREDENTIALS}"
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME} .'
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                # Stop and remove any existing container
                if [ $(docker ps -aq -f name=${CONTAINER_NAME}) ]; then
                    docker stop ${CONTAINER_NAME}
                    docker rm ${CONTAINER_NAME}
                fi

                # Run new container
                docker run -d -p ${APP_PORT}:9090 --name ${CONTAINER_NAME} ${IMAGE_NAME}
                '''
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully! Your app is running on port ${APP_PORT}"
        }
        failure {
            echo "Pipeline failed. Check logs for details."
        }
    }
}
