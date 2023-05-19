import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.csharpScript
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.10"

project {
    description = "Test description"

    vcsRoot(HttpsGithubComPettailegendsDevlearningsRefsHeadsMaster)

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComPettailegendsDevlearningsRefsHeadsMaster)
    }

    steps {
        script {
            name = "Hello world - i am building myself"
            scriptContent = """echo " Hello world """"
        }
        script {
            name = "More steps the merrier"
            scriptContent = """echo " More steps the merrier """"
        }
        script {
            name = "adding build steps by shan"
            scriptContent = """echo " shan third build step""""
        }
        csharpScript {
            name = "See Sharp Step"
            content = "#"
            tool = "%teamcity.tool.TeamCity.csi.1.0.3%"
        }
    }

    triggers {
        vcs {
        }
    }

    failureConditions {
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "error"
            failureMessage = "i found error"
            reverse = false
        }
    }

    features {
        perfmon {
        }
    }
})

object HttpsGithubComPettailegendsDevlearningsRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/pettailegends/devlearnings#refs/heads/master"
    url = "https://github.com/pettailegends/devlearnings"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "narainelearns"
        password = "credentialsJSON:6c83443c-c9be-4b9e-b308-d55c9362f4bd"
    }
    param("oauthProviderId", "tc-cloud-github-connection")
})
