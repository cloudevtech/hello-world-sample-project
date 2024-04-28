pipeline {
    agent any
    tools {
        maven "maven"
    }

    environment {
        KANIKO_IMAGE = 'gcr.io/kaniko-project/executor:debug'
    }
    
    stages {
        stage('git repo') {
            steps {
                sh "ls -a"
                sh "rm -rf hello-world-sample-project-lolc"
                sh "git clone https://github.com/isurupathumherath/hello-world-sample-project-lolc.git"
            }
        }
        stage('clean') {
            steps {
                sh "mvn clean -f hello-world-sample-project-lolc"
            }
        }
        stage('install') {
            steps {
                sh "mvn install -f hello-world-sample-project-lolc"
            }
        }
        // stage('test') {
        //     steps {
        //         sh "mvn test -f hello-world-sample-project-lolc"
        //     }
        // }
        // stage('package') {
        //     steps {
        //         sh "mvn package -f hello-world-sample-project-lolc"
        //     }
        // }
        stage('Build Docker Image') {
            steps {
                script {
                    // Define Dockerfile path
                    def dockerfile = './Dockerfile'
                    
                    // Run Kaniko to build the Docker image
                    sh "${KANIKO_IMAGE} --dockerfile=${dockerfile} --no-push"
                }
            }
        }
    }
}
