/**
 * 
 */
package br.com.pache.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Class responsible for execute a function that it will be scheduled.
 * @author lpache
 * 
 */
public class HelloJob implements Job {

	private static final Logger LOG = Logger.getLogger(HelloJob.class);

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// Say Hello to the World and display the date/time
		LOG.debug("Hello World! - " + new Date());
	}
}
