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
                        authToken : 'NIwzvCS4Ihi0ClNGzdB327UNLT-FeL6ujarJ-o0kxPlyLnMU0Ls',
                        webhookUrl: 'https://applications.zoom.us/addon/v2/jenkins/webhooks/k-rjQysTQaiYi65449s3Qg',
                        message   : '']
                )
            }
        }
    }
}
