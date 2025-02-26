pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        DOCKER_IMAGE_NAME = 'scientific_cal'
        GITHUB_REPO_URL = 'https://github.com/prabal2011singh/Calculator_java.git'
    }

    stages {
        stage('Checkout from GitHub') {
            steps {
                script {
                    // clone the code from the GitHub repository
                    git branch: 'main', url: "${GITHUB_REPO_URL}"
                }
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    sh 'mvn clean package' // Build the Java project
                    sh 'mvn test' // Run unit tests
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def imageExists = sh(script: "docker images -q ${DOCKER_IMAGE_NAME}", returnStdout: true).trim()
                    if (imageExists) {
                        echo "Docker image already exists. Skipping build."
                    } else {
                        echo "Building new Docker image..."
                        docker.build("${DOCKER_IMAGE_NAME}", '.')
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCredentials') {
                        sh 'docker tag scientific_cal prabal2011singh/scientific_cal:latest'
                        sh 'docker push prabal2011singh/scientific_cal'
                    }
                }
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                script {
                    withEnv(["ANSIBLE_HOST_KEY_CHECKING=False"]) {
                        ansiblePlaybook(
                            playbook: 'deploy.yml',
                            inventory: 'inventory.ini'
                        )
                    }
                }
            }
        }
    }

    post {
        success {
            mail to: 'prabal.singh@iiitb.ac.in',
                 subject: "Application Deployment SUCCESS: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The build was successful!"
        }
        failure {
            mail to: 'prabal.singh@iiitb.ac.in',
                 subject: "Application Deployment FAILURE: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The build failed."
        }
        always {
            cleanWs()
        }
    }
}