#!/usr/bin/env groovy

def call(body) {
    echo "Start Sample"

    new Deployer(script:this).run()

    echo "Completed"
    currentBuild.result = 'SUCCESS' //FAILURE to fail

    return this
}
