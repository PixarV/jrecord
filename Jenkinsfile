pipeline {
    agent any
    tools {
        jdk 'Java11'
    }
    environment {
        JAVA_HOME = "${jdk}"
    }
    stages {
        stage("Pre-build") {
            steps {
                checkout scm
                sh './gradlew clean'
            }
        }
//        stage("Compile project") {
//            steps {
//                sh './gradlew compileJava'
//            }
//        }
//        stage("Test fast") {
//            steps {
//                sh './gradlew test'
//            }
//        }
        stage("Test slow") {
            when {
                branch 'master'
            }
            steps {
                sh './gradlew slowTest'
            }
        }
        stage("Assemble") {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Result') {
            steps {
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
                sh './gradlew publish'
//                junit '**/build/test-results/test/TEST-*.xml'
            }
        }
//        stage("clean artifacts") {
//            steps {
//                sh 'git branch -D artifacts'
//                sh 'git checkout -b artifacts origin/artifacts'
//                sh 'git pull origin artifacts'
//                sh 'git log'
//
//            }
//        }
        stage('Push artifacts') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'uliana_github',
                        passwordVariable: 'GIT_PASSWORD',
                        usernameVariable: 'GIT_USERNAME')]) {

//                    sh 'git config credential.${origin}.email "${GIT_USERNAME}@gmail.com"'
//                    sh 'git config credential.${origin}.username "{GIT_USERNAME}"'
//                    sh 'git config credential.${origin}.password "{GIT_PASSWORD}"'

                    sh 'rm -rf tmp/'
                    sh 'mv repos/ tmp/'
                    sh 'ls -R tmp'
                    sh 'git checkout artifacts'
                    sh 'git pull origin artifacts'
                    sh 'git log'
                    sh 'ls -R  tmp'
//                    sh 'ls -R  repos'
                    sh 'rm -rf repos/'
                    sh 'mv tmp/ repos/'
                    sh 'ls -R  repos'
                    sh 'rm -rf tmp/'
                    sh 'git add repos/'
                    sh 'git commit -m "Jenkins ${BUILD_ID}"'
//                    sh 'mv repos tmp'
//                    sh '(git branch -D artifacts && git checkout -b artifacts origin/artifacts) || git checkout -b artifacts origin/artifacts'
//                    sh 'git pull'
//                    sh 'git branch -a'
//                    sh 'mv tmp repos'
                    sh 'git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/PixarV/jrecord.git artifacts'
                }

            }
        }
    }
}