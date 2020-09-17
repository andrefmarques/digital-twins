pipeline {
    agent any
    environment {
        CI = 'true'
    }
    stages {
        stage('Clean Images Before') {
            steps {
                sh 'docker ps'
                sh 'docker ps -a'
                sh 'docker images'

                script {
                    try {
                        sh """#!/bin/bash
                         docker images | grep esp41
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh """#!/bin/bash
                         docker images | grep "<none>"
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh """#!/bin/bash
                         docker rmi -f `docker images | grep esp41 | awk {'print \$3'}`
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh """#!/bin/bash
                         docker images | grep esp41
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }
            }
        }



        stage('Testing Average Speed') {
            agent {
                dockerfile {
                    filename 'Dockerfile-Deploy'
                    args '-v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2 -w /usr/src/mymaven'
                }
            }
            when {
                branch 'master'
            }
            steps {
                dir ('digital-twins-average-speed') {
                    sh 'mvn clean test'
                }
            }
        }




        stage('Deploy to JFrog') {
            agent {
                dockerfile {
                    filename 'Dockerfile-Deploy'
                    args '-v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2 -w /usr/src/mymaven'
                }
            }
            when {
                branch 'release'
            }
            steps {
                sh 'ls -la /root/.m2'
                sh 'ls -la'
                dir ('digital-twins-frontend') {
                    sh 'mvn deploy -Dmaven.test.skip=true'
                }
            }
        }




        stage('Deploy to Development') {
            parallel {
                stage('Deploy Frontend') {
                    when {
                        branch 'master'
                    }
                    steps {
                        sh 'pwd'
                        sh 'ls -la'
                        script{
                            docker.image('maven:3-jdk-8').inside('-v "$HOME/.m2":/root/.m2') {
                                sh 'ls -la'
                                dir ('digital-twins-frontend') {
                                    sh 'mvn -B -s ../settings.xml package'

                                }
                            }
                        }
                        sshagent(credentials: ['esp41']){
                            sh 'ls -la digital-twins-frontend/target/'
                            sh 'scp digital-twins-frontend/target/digital-twins-frontend.jar esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-frontend.jar'
                        }
                    }
                }
                stage('Deploy Average Speed') {
                    when {
                        branch 'master'
                    }
                    steps {
                        sh 'pwd'
                        sh 'ls -la'
                        script{
                            docker.image('maven:3-jdk-8').inside('-v "$HOME/.m2":/root/.m2') {
                                sh 'ls -la'
                                dir ('digital-twins-average-speed') {
                                    sh 'mvn -B -s ../settings.xml package'

                                }
                            }
                        }
                        sshagent(credentials: ['esp41']){
                            sh 'ls -la digital-twins-average-speed/target/'
                            sh 'scp digital-twins-average-speed/target/digital-twins-average-speed.jar esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-average-speed.jar'
                        }
                    }
                }
                stage('Deploy Location History') {
                    when {
                        branch 'master'
                    }
                    steps {
                        sh 'pwd'
                        sh 'ls -la'
                        script{
                            docker.image('maven:3-jdk-8').inside('-v "$HOME/.m2":/root/.m2') {
                                sh 'ls -la'
                                dir ('digital-twins-location-history') {
                                    sh 'mvn -B -s ../settings.xml package'

                                }
                            }
                        }
                        sshagent(credentials: ['esp41']){
                            sh 'ls -la digital-twins-location-history/target/'
                            sh 'scp digital-twins-location-history/target/digital-twins-location-history.jar esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-location-history.jar'
                        }
                    }
                }
                stage('Deploy Location RT') {
                    when {
                        branch 'master'
                    }
                    steps {
                        sh 'pwd'
                        sh 'ls -la'
                        script{
                            docker.image('maven:3-jdk-8').inside('-v "$HOME/.m2":/root/.m2') {
                                sh 'ls -la'
                                dir ('digital-twins-location-rt') {
                                    sh 'mvn -B -s ../settings.xml package'

                                }
                            }
                        }
                        sshagent(credentials: ['esp41']){
                            sh 'ls -la digital-twins-location-rt/target/'
                            sh 'scp digital-twins-location-rt/target/digital-twins-location-rt.jar esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-location-rt.jar'
                        }
                    }
                }
                stage('Deploy Bus Routes') {
                    when {
                        branch 'master'
                    }
                    steps {
                        sh 'pwd'
                        sh 'ls -la'
                        script{
                            docker.image('maven:3-jdk-8').inside('-v "$HOME/.m2":/root/.m2') {
                                sh 'ls -la'
                                dir ('digital-twins-bus-routes') {
                                    sh 'mvn -B -s ../settings.xml package'

                                }
                            }
                        }
                        sshagent(credentials: ['esp41']){
                            sh 'ls -la digital-twins-bus-routes/target/'
                            sh 'scp digital-twins-bus-routes/target/digital-twins-bus-routes.jar esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-bus-routes.jar'
                        }
                    }
                }
            }
        }


        stage('Build Docker Images') {
            parallel {
                stage('Build Frontend Image - Master') {
                    when {
                        branch 'master'
                    }
                    steps{
                        sshagent(credentials: ['esp41']){
                            sh 'scp esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-frontend.jar Dockerfiles/digital-twins-frontend.jar'
                        }
                            sh 'ls -la'
                            sh 'pwd'
                            sh 'ls /'
                            script {
                                dockerFrontend = docker.build("esp41-digital-twins-frontend"+ ":$BUILD_NUMBER", "-f Dockerfiles/Dockerfile.frontend ./Dockerfiles")
                            }
                    }
                }
                stage('Build average-speed Image - Master') {
                    when {
                        branch 'master'
                    }
                    steps{
                        sshagent(credentials: ['esp41']){
                            sh 'scp esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-average-speed.jar Dockerfiles/digital-twins-average-speed.jar'
                        }
                            sh 'ls -la'
                            sh 'pwd'
                            sh 'ls /'
                            script {
                                dockerAverageSpeed = docker.build("esp41-digital-twins-average-speed"+ ":$BUILD_NUMBER", "-f Dockerfiles/Dockerfile.average-speed ./Dockerfiles")
                            }
                    }
                }
                stage('Build location-history Image - Master') {
                    when {
                        branch 'master'
                    }
                    steps{
                        sshagent(credentials: ['esp41']){
                            sh 'scp esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-location-history.jar Dockerfiles/digital-twins-location-history.jar'
                        }
                            sh 'ls -la'
                            sh 'pwd'
                            sh 'ls /'
                            script {
                                dockerLocationHistory = docker.build("esp41-digital-twins-location-history"+ ":$BUILD_NUMBER", "-f Dockerfiles/Dockerfile.location-history ./Dockerfiles")
                            }
                    }
                }
                stage('Build location-rt Image - Master') {
                    when {
                        branch 'master'
                    }
                    steps{
                        sshagent(credentials: ['esp41']){
                            sh 'scp esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-location-rt.jar Dockerfiles/digital-twins-location-rt.jar'
                        }
                            sh 'ls -la'
                            sh 'pwd'
                            sh 'ls /'
                            script {
                                dockerLocationRT = docker.build("esp41-digital-twins-location-rt"+ ":$BUILD_NUMBER", "-f Dockerfiles/Dockerfile.location-rt ./Dockerfiles")
                            }
                    }
                }
                stage('Build bus-routes Image - Master') {
                    when {
                        branch 'master'
                    }
                    steps{
                        sshagent(credentials: ['esp41']){
                            sh 'scp esp41@192.168.160.103:/home/esp41/jenkins_deploy/digital-twins-bus-routes.jar Dockerfiles/digital-twins-bus-routes.jar'
                        }
                            sh 'ls -la'
                            sh 'pwd'
                            sh 'ls /'
                            script {
                                dockerBusRoutes = docker.build("esp41-digital-twins-bus-routes"+ ":$BUILD_NUMBER", "-f Dockerfiles/Dockerfile.bus-routes ./Dockerfiles")
                            }
                    }
                }
                stage('Build Frontend Image - Release') {
                    when {
                        branch 'release'
                    }
                    steps{
                        sh 'ls -la'
                        sh 'pwd'
                        sh 'ls /'
                        script {
                            sh 'wget -O digital-twins.jar http://192.168.160.99:8081/artifactory/libs-release/pt/ua/deti/es/41/digital-twins/1.0.39/digital-twins-1.0.39.jar'
                            dockerFrontend = docker.build("esp41-digital-twins-frontend"+ ":$BUILD_NUMBER")
                        }
                    }
                }
                stage('Build Bus Producer Image') {
                    steps{
                        script {
                            dockerPython = docker.build("esp41-digital-twins-bus-producer"+ ":$BUILD_NUMBER", "./digital-twins-bus-producer")
                        }
                    }
                }
            }
        }



        stage('Deploy Docker Images') {
            parallel {
                stage('Deploy Frontend Image') {
                    steps{
                        sh 'ls -la'
                        script {
                            docker.withRegistry( 'http://192.168.160.99:5000') {
                                dockerFrontend.push("latest")
                            }
                        }
                    }
                }
                stage('Deploy average-speed Image') {
                    steps{
                        sh 'ls -la'
                        script {
                            docker.withRegistry( 'http://192.168.160.99:5000') {
                                dockerAverageSpeed.push("latest")
                            }
                        }
                    }
                }
                stage('Deploy location-history Image') {
                    steps{
                        sh 'ls -la'
                        script {
                            docker.withRegistry( 'http://192.168.160.99:5000') {
                                dockerLocationHistory.push("latest")
                            }
                        }
                    }
                }
                stage('Deploy location-rt Image') {
                    steps{
                        sh 'ls -la'
                        script {
                            docker.withRegistry( 'http://192.168.160.99:5000') {
                                dockerLocationRT.push("latest")
                            }
                        }
                    }
                }
                stage('Deploy bus-routes Image') {
                    steps{
                        sh 'ls -la'
                        script {
                            docker.withRegistry( 'http://192.168.160.99:5000') {
                                dockerBusRoutes.push("latest")
                            }
                        }
                    }
                }
                stage('Deploy Bus Producer Image') {
                    steps{
                        script {
                            docker.withRegistry( 'http://192.168.160.99:5000') {
                                dockerPython.push("latest")
                            }
                        }
                    }
                }
            }
        }


        stage('Deploy docker-compose'){
            steps {
                sshagent(credentials: ['esp41']){
                    sh 'ssh -o StrictHostKeyChecking=no -l esp41 192.168.160.103 docker-compose -f /home/esp41/esp41-compose/docker-compose.yml down'
                    sh 'scp docker-compose-deploy.yml esp41@192.168.160.103:/home/esp41/esp41-compose/docker-compose.yml'
                }
            }
        }



        stage('Compose UP'){
            steps {
                sshagent(credentials: ['esp41']){
                    sh 'ssh -o StrictHostKeyChecking=no -l esp41 192.168.160.103 docker-compose -f /home/esp41/esp41-compose/docker-compose.yml pull'
                    sh 'ssh -o StrictHostKeyChecking=no -l esp41 192.168.160.103 docker-compose -f /home/esp41/esp41-compose/docker-compose.yml --compatibility up -d'
                    catchError {
                        sh 'ssh -o StrictHostKeyChecking=no -l esp41 192.168.160.103 sh /home/esp41/clean.sh'
                    }
                }
            }
        }



        stage('Clean Images After') {
            steps {
                sh 'docker ps'
                sh 'docker ps -a'
                sh 'docker images'

                script {
                    try {
                        sh """#!/bin/bash
                         docker images | grep esp41
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh """#!/bin/bash
                         docker images | grep "<none>"
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh """#!/bin/bash
                         docker rmi -f `docker images | grep esp41 | awk {'print \$3'}`
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh """#!/bin/bash
                         docker images | grep esp41
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh """#!/bin/bash
                         docker images
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh """#!/bin/bash
                         docker ps -a
                        """
                    } catch (err) {
                        echo err.getMessage()
                    }
                }
            }
        }
    }
}