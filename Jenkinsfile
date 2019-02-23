pipeline {
    agent any

    stages {
        stage('BuildAndTest') {
            steps {
                echo 'Building and Running JunitTests..'
                sh 'mvn clean install'
            }
        }
        stage('Deploy') {
            when  {
                expression {currentBuild.result == null || currentBuild.result == 'SUCCESS' }
                  }
            steps {
                echo 'Deploying....'

                sh 'scp -oStrictHostKeyChecking=no -i /home/bitnami/jenkins.pem target/football-league-standings-1.0.0.jar ubuntu@18.217.63.227:football-league-standings-1.0.0.jar'
                sh 'ssh -oStrictHostKeyChecking=no -i /home/bitnami/jenkins.pem ubuntu@18.217.63.227 "java -jar football-league-standings-1.0.0.jar"'
            }
        }
    }
}
