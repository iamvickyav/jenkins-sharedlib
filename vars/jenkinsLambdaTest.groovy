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
                    sh "mvn -Dusername=$USER_NAME -DaccessKey=$ACCESS_KEY test"
                }
            }
        }
    }
}