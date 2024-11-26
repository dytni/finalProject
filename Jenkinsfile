pipeline {
    agent any

    environment {
        JAR_NAME = 'finalShop-0.0.2-SNAPSHOT.jar'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/dytni/finalProject'
            }
        }

        stage('Compile') {
            steps {
                script {
                    sh 'ant compile'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'ant test'
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    sh 'ant package'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'ant deploy'
                }
            }
        }
    }

    post {
        success {
            echo 'Build and deploy succeeded!'
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}
