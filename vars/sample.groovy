#!/usr/bin/env groovy

def call(body) {
    echo "Start sample script"

    new Deployer(script:this).run()

    echo "Completed"
    currentBuild.result = 'SUCCESS' //FAILURE to fail

    return this
}
