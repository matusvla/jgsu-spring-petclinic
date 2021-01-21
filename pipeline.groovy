pipeline {
    agent any

    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/matusvla/jgsu-spring-petclinic'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
