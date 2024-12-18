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
                    // Run the Groovy script using the 'groovy' command
                    sh '''
                        groovy car.groovy
                    '''
                }
            }
        }

        stage('SonarQube Analysis') {
            when {
                branch 'main'  // Optionally restrict to a specific branch
            }
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {  // Use 'sonarqube' if it's configured in Jenkins
                        sh '''
                            mvn clean verify sonar:sonar \
                                -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                                -Dsonar.host.url=${SONAR_HOST_URL} \
                                -Dsonar.login=${SONAR_TOKEN}
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

