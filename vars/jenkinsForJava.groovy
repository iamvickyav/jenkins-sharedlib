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
                        url: "${repoUrl}"
                }
            }
            stage("Cleaning workspace") {
                steps {
                    sh "mvn clean"
                }
            }
            stage("Running Testcase") {
                when {
                    expression {
                        env.JOB_NAME.contains("test")
                    }
                }
                steps {
                    sh "mvn test"
                }
            }
            stage("Packing Application") {
                when {
                    expression {
                        env.JOB_NAME.contains("deploy")
                    }
                }
                steps {
                    sh "mvn package -DskipTests"
                    sh "echo Build Successful"
                }
            }
        }
    }
}