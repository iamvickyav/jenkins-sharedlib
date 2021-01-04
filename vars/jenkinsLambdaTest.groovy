def call(String repoUrl) {
    pipeline{
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
                    git branch: 'main',
                    url: "${repoUrl}"
                }
            }
            stage("Running Testcase") {
                steps {
                    sh "mvn -Dusername=$LT_USERNAME -DaccessKey=$LT_ACCESS_KEY test"
                }
            }
        }
    }
}