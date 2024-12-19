pipeline {
    agent any

    tools {
        groovy 'groovy'  
    }

    environment {
        // If you are using SonarQube
        SONAR_HOST_URL = 'http://localhost:9000'  // Adjust with your SonarQube URL
        SONAR_PROJECT_KEY = 'groovy-scripts'      // Change it according to your project
        SONAR_TOKEN = credentials('sonarqube-token')  // Use the correct SonarQube token stored in Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from GitHub repository
                git url: 'https://github.com/Amaan00101/groovy-scripts.git', branch: 'main'  // Replace with your GitHub repository URL
            }
        }

        stage('Run Groovy Script') {
            steps {
                script {
                    sh 'groovy car.groovy'
                }
            }
        }

        stage('Install SonarQube Scanner') {
            steps {
                script {
                    // Check if SonarQube Scanner is installed
                    def sonarScanner = sh(script: 'which sonar-scanner', returnStatus: true)
                    if (sonarScanner != 0) {
                        echo "SonarQube Scanner not found, installing it."
                        // Install SonarQube Scanner in the workspace
                        sh '''
                            # Install SonarQube Scanner
                            wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.7.0.2747-linux.zip
                            unzip sonar-scanner-cli-4.7.0.2747-linux.zip
                            mv sonar-scanner-4.7.0.2747-linux sonar-scanner
                            export PATH=$PWD/sonar-scanner/bin:$PATH
                        '''
                    } else {
                        echo "SonarQube Scanner already installed."
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {  // Use 'sonarqube' if it's configured in Jenkins
                        sh '''
                            sonar-scanner \
                                -Dsonar.projectKey=DevOps1 \
                                -Dsonar.sources=. \
                                -Dsonar.host.url=http://localhost:9000 \
                                -Dsonar.login=${SONAR_TOKEN}
                            // sonar-scanner \
                            //     -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                            //     -Dsonar.sources=. \
                            //     -Dsonar.host.url=${SONAR_HOST_URL} \
                            //     -Dsonar.login=${SONAR_TOKEN}
                        '''
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline completed."
        }
        success {
            echo "Build and analysis completed successfully."
        }
        failure {
            echo "Something went wrong. Please check the logs."
        }
    }
}

