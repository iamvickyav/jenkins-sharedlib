def call(String repoUrl, boolean publishArtifact=false) {
    pipeline {
        agent any
        tools { 
            maven 'Maven 3.5.0' 
            jdk 'jdk8' 
        }
        stages {
            stage("Tools initialization") {
                steps {
                    sh "mvn --version"
                    sh "java -version"
                }
            }
            stage("Checkout Code") {
                steps {
                    git branch: 'master',
                        url: "${gitUrl}"
                }
            }
            stage("Cleaning workspace") {
                steps {
                    sh "mvn clean"
                }
            }
            stage("Running Testcase") {
                steps {
                    sh "mvn test"
                }
            }
            stage("Packing Application") {
                steps {
                    sh "mvn package"
                    sh "echo Build Successful"
                }
            }
        }
    }
}