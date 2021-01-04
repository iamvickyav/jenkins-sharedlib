def call(String repoUrl) {
    pipeline{
        agent any
        tools { 
            maven 'Maven 3.5.0' 
            jdk 'jdk8' 
        }
        environment {
            LAMBDA_TEST_CRED = credentials('lambda-test-secrets')
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
            stage("Running in LambdaTest Cloud") {
                steps {
                    sh "mvn -Dusername=${LAMBDA_TEST_CRED_USR} -DaccessKey=${LAMBDA_TEST_CRED_PSW} test"
                }
            }
        }
    }
}