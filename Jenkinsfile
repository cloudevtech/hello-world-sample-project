pipeline {

    agent {
      kubernetes {
        defaultContainer 'maven'
        // yaml libraryResource('supportPod.yaml')
        yamlFile 'supportPod.yaml'
      }
    }
    
    stages {
        stage('git repo') {
            steps {
                sh "ls -a"
                sh "rm -rf hello-world-sample-project-lolc"
                sh "git clone https://github.com/isurupathumherath/hello-world-sample-project-lolc.git"
            }
        }

        // stage('clean') {
        //     steps {
        //         sh "mvn clean -f hello-world-sample-project-lolc"
        //     }
        // }

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

        stage('Build Docker Image with Kaniko') {
            steps {
                container('kaniko') {
                    script {

                        // Run Kaniko Build Command
                        sh "executor --context=. --dockerfile=./Dockerfile --no-push"
                    }
                }
            }
        }
    }
}
