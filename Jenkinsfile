pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/dytni/finalProject.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'target/finalShop-0.0.2-SNAPSHOT.jar user@server:/deploy/path'
            }
        }
    }
}
