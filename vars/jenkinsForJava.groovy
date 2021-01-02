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
            if(env.JOB_NAME.contains("deploy")){
                packageArtifact()
            } else if(env.JOB_NAME.contains("test")) {
                buildAndTest()
            }
        }
    }
}
def packageArtifact(){
    stage("Packing Application") {
        steps {
            sh "mvn package -DskipTests"
            sh "echo Build Successful"
        }
    }
}

def buildAndTest(){
     stage("Running Testcase") {
        steps {
            sh "mvn test"
        }
    }
}