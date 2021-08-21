pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
                bat "rmdir  /s /q hello-world-sample-project-lolc"
                bat "git clone https://github.com/isurupathumherath/hello-world-sample-project-lolc.git"
                bat "mvn clean -f hello-world-sample-project-lolc"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f hello-world-sample-project-lolc"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f hello-world-sample-project-lolc"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f hello-world-sample-project-lolc"
            }
        }
    }
}