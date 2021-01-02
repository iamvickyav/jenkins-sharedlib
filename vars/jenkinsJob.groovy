def call(String gitUrl) {
    pipeline {
        agent any
        tools { 
            maven 'Maven 3.5.0' 
            jdk 'jdk8' 
        }
        stages {
            stage ('Check Maven & Java') {
                steps {
                    sh 'mvn --version'
                    sh 'java -version'
                }
            }
            stage ('Download Project') {
                stage('Checkout external proj') {
                steps {
                    git branch: 'master',
                        url: 'https://github.com/iamvickyav/spring-boot-data-H2-embedded.git'

                    sh "ls -lat"
                }
            }
        }
    }
}