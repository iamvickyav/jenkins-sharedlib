def call(String gitUrl='https://github.com/iamvickyav/spring-boot-data-H2-embedded.git') {
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
                steps {
                    git branch: 'master',
                        url: "${gitUrl}"

                    sh "ls -lat"
                }
            }
        }
    }
}