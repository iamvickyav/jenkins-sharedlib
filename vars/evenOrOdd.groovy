def call(int buildNumber){

    if(buildNumber % 2 == 0) {
        pipeline {
            agent any
            stages {
                stage ('build even stage') {
                    steps {
                        sh "echo ${buildNumber}"
                        script {
                            log.info 'Starting build from Even Shared Lib'
                        }
                    }
                }
            }
        }
    } else {
        pipeline {
            agent any
            stages {
                stage ('build odd stage') {
                    steps {
                        sh "echo ${buildNumber}"
                        script {
                            log.info 'Starting build from Odd Shared Lib'
                        }
                    }
                }
            }
        }
    }
}