pipeline {
    agent any
   tools {
    nodejs 'nodejs-20'
   }
   environment {
    IMAGE_NAME = 'node-demo-app'
    DOCKER_REPO = 'waghvedant/node-demo-sample'
    CONTAINER_NAME = 'node-demo-container'
   }
   stages {
    stage('Checkout') {
        steps {
            git branch: 'main', url: 'https://github.com/waghvedant1990/node-js.git'
        }
    }
    stage('verify environments') {
        steps {
            sh '''
            echo "Node.js version"  
            node -v 
            echo "NPM version"
            npm -v
        
            echo "docker version"
            docker --version
            '''

}
    }
    stage('install dependencies') {
        steps {
            sh 'npm install'
        }
    }
    stage('run tests') {
        steps {
            sh 'npm test'
        }
    }
    stage('build docker image') {
        steps {
            sh "docker build -t ${IMAGE_NAME} ."
        }
    }
    stage ('Docker login') {
        steps {
            withCredentials([
                usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')
            ]) {
                sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
            }
        }
    }
    stage('push docker image') {
        steps {
            docker push "${DOCKER_REPO}:${BUILD_NUMBER}"
        }
    }
    stage('deploy container') {
        steps {
            sh '''
            docker pull ${DOCKER_REPO}:${BUILD_NUMBER}
            docker stop ${CONTAINER_NAME} || true
            docker rm ${CONTAINER_NAME} || true
            docker run -d --name ${CONTAINER_NAME} -p 3000:3000 ${DOCKER_REPO}:${BUILD_NUMBER}
            '''
        }
    }
   }
}