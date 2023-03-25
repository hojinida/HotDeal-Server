pipeline {
    agent any

    environment {
        CREDENTIALS_ID = '1a9202fb-5fb4-4e98-9239-7b12cee14f04'
        REMOTE_HOST = '106.10.59.205'
        REMOTE_PORT = '8080'
        WORKSPACE_DIR = '/var/lib/jenkins/workspace/hot-deal'
    }

    stages {
        stage('Deploy') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: CREDENTIALS_ID, keyFileVariable: 'SSH_KEY')]) {
                    sh """
                        eval \$(ssh-agent -s)
                        echo ${SSH_KEY} | ssh-add -
                        ssh -T -o StrictHostKeyChecking=no -p ${env.REMOTE_PORT} root@${env.REMOTE_HOST} <<EOF
                            rm -rf /home/docker-image/deploy/*
                            exit
                        EOF
                        scp -r -P ${env.REMOTE_PORT} ${env.WORKSPACE_DIR}/* root@${env.REMOTE_HOST}:/home/docker-image/deploy/
                        ssh -T -o StrictHostKeyChecking=no -p ${env.REMOTE_PORT} root@${env.REMOTE_HOST} <<EOF
                            cd /home/docker-image
                            docker image build -t hotdeal .
                            ./deploy.sh
                            exit
                        EOF
                        eval \$(ssh-agent -k)
                    """
                }
            }
        }
    }
}