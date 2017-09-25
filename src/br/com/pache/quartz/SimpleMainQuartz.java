/**
 * 
 */
package br.com.pache.quartz;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author lpache
 * 
 */
public class SimpleMainQuartz {
	private static final Logger LOG = Logger.getLogger(SimpleMainQuartz.class);

	public static void main(String[] args) {
		try {
			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(HelloJob.class)
					.withIdentity("job1").build();

			/*/ compute a time that is on the next round minute
			Date runTime = DateBuilder.evenMinuteDate(new Date());

			// Trigger the job to run on the next round minute
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1").startAt(runTime).build();*/
			
			// Trigger the job to run every minute
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
			
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sched = schFactory.getScheduler();

			// Tell quartz to schedule the job using our trigger
			sched.scheduleJob(job, trigger);
			sched.start();
			Thread.sleep(900L * 1000L);

			sched.shutdown(true);

		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
		}

	}
}
