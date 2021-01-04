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
                    sh "echo $env.LT_USERNAME"
                    sh "mvn -Dusername=${env.LT_USERNAME} -DaccessKey=${env.LT_ACCESS_KEY} test"
                }
            }
        }
    }
}