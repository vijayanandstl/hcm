pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build Parent Project') {
            steps {
                dir('hcm-parent') {
                    script {
                        // This will trigger the hcm-parent pipeline
                        build job: 'hcm-parent-pipeline', parameters: [
                            string(name: 'BRANCH_NAME', value: env.BRANCH_NAME)
                        ]
                    }
                }
            }
        }
        
        stage('Deploy Services') {
            parallel {
                stage('Deploy Candidate Service') {
                    steps {
                        dir('candidate-service') {
                            script {
                                build job: 'candidate-service-pipeline', parameters: [
                                    string(name: 'BRANCH_NAME', value: env.BRANCH_NAME)
                                ]
                            }
                        }
                    }
                }
                
                stage('Deploy Organization Service') {
                    steps {
                        dir('organization-service') {
                            script {
                                build job: 'organization-service-pipeline', parameters: [
                                    string(name: 'BRANCH_NAME', value: env.BRANCH_NAME)
                                ]
                            }
                        }
                    }
                }
                
                stage('Deploy Tenant Service') {
                    steps {
                        dir('tenant-service') {
                            script {
                                build job: 'tenant-service-pipeline', parameters: [
                                    string(name: 'BRANCH_NAME', value: env.BRANCH_NAME)
                                ]
                            }
                        }
                    }
                }
                
                stage('Deploy HCM Core') {
                    steps {
                        dir('hcm-core') {
                            script {
                                build job: 'hcm-core-pipeline', parameters: [
                                    string(name: 'BRANCH_NAME', value: env.BRANCH_NAME)
                                ]
                            }
                        }
                    }
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'All services deployed successfully!'
        }
        failure {
            echo 'One or more services failed to deploy!'
        }
    }
} 