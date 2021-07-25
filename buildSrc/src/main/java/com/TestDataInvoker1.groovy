package com

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class TestDataInvoker1 extends DefaultTask {

    @TaskAction
    void doTest() {
        def sysProps = System.properties

        sysProps += ['Moscow: DAMN!']
    }

}
