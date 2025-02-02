job('ejemplo2-job-DSL') {
	description('Job DSL de ejemplo para el curso de Jenkins')
  	scm {
      		git('https://github.com/alitaavila/jenkins.job.parametrizado.git', 'main') { node ->
        		node / gitConfigName('alitaavila')
        		node / gitConfigEmail('alejandra.avilaperez@gmail.com')
      		}
    	} 
  	parameters {
   		stringParam('nombre', defaultValue = 'Alejandra', description = 'Parametro de cadena para el Job Booleano')
      		choiceParam('planeta', ['Mercurio', 'Venus', 'Tierrra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
      		booleanParam('agente', false)
    	}
  	triggers {
    		cron('H/8 * * * *')
			githubPush()
    	}
  	steps {
    		shell("bash jobscript.sh")
    	}
  	publishers {
      		mailer('alejandra.avilaperez@gmail.com', true, true)
      		slackNotifier {
			 notifyAborted(true)
			 notifyEveryFailure(true)
			 notifyNotBuilt(false)
			 notifyUnstable(false)
			 notifyBackToNormal(true)
			 notifySuccess(true)
			 notifyRepeatedFailure(false)
			 startNotification(false)
			 includeTestSummary(false)
			 includeCustomMessage(false)
			 customMessage(null)
			 sendAs(null)
			 commitInfoChoice('NONE')
			 teamDomain(null)
			 authToken(null)
        	}
    	}
}
