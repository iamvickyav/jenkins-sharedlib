def call(String repoUrl) {
    pipeline{
        agent any
        tools { 
            maven 'Maven 3.5.0' 
            jdk 'jdk8' 
        }
        environment {
            USER_NAME  = 'vickyavw.10'
            ACCESS_KEY = 'ic341dZtl3tMPEa4pToJEfMUnet4cbs4Gpdd9VF7HqCCf7rYtb'
            USER = credentials('lambda-test-secrets')
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
                    git branch: 'main',
                    url: "${repoUrl}"
                }
            }
            stage("Running Testcase") {
                steps {
                    sh "echo $USER"
                    sh "mvn -Dusername=${USER_USR} -DaccessKey=${USER_PSW} test"
                }
            }
        }
    }
}