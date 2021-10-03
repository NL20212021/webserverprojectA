pipeline {
    agent any
    stages {
        stage("SCM checkout") {
            steps {
                git url: "https://github.com/NL20212021/webserverprojectA.git"
            }
        }
        stage("Archive Artifacts") {
            steps {
                archiveArtifacts '**/*.html'
            }
        }
        stage ("Transfer Artifacts") {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'webserver', transfers: [sshTransfer(excludes: '', execCommand: '', execTimeout: 120000, flatten: true, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/var/www/html', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '**/*.html')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)])
            }
        }
    }
}
