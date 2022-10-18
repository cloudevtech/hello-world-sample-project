pipeline {
    agent any
    stages {
        stage('git repo') {
            steps {
                bat "rm -rf hello-world-sample-project-lolc"
                bat "git clone https://github.com/isurupathumherath/hello-world-sample-project-lolc.git"
            }
        }
        stage('clean') {
            steps {
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
