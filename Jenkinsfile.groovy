#!/usr/bin/env groovy

pipeline {
    agent any

    tools {
        maven 'maven 3.6.3'
        jdk 'openJdk8'
    }

    stages {
        stage('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
        }

        stage('Reports') {
            steps {
                allure([
                        includeProperties: false,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'target/allure-results']]
                ])
            }
        }

        stage('Notification') {
            steps {
                zoomSend([
                        authToken : 'JuAlhTSVHx63U09d3K4dqvBnNF2faEArpXmk0SrCaba7PSTuAw8',
                        webhookUrl: 'https://applications.zoom.us/addon/v2/jenkins/webhooks/yAdSATNRR8uBxH_EqSjQKw',
                        message   : 'TestsRefDataSelfmanager started by user']
                )
            }
        }
    }
}
