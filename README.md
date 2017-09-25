
# QUARTZ – Job-Schedule
**What is this?**

Quartz is a _Job Schedule_ open source that can be integrated with Java applications. 

**Configuration**

1. **Jar dependency**
All dependency can be downloaded [here](http://www.quartz-scheduler.org/downloads). Or projects managed by Maven look the code in the file _pom.xml_.

2. **CronTrigger Format**
Cron expression is a String of 6 or 7 fields separated by spaces, where each field corresponds in this order (Second Minute Hour DayOfTheMonth Month DayOfTheWeek Year).
Ex: _'– 0 0 12 * * ?'_ This expression means: execute 12 PM every days. Documentations about cron expression [here](http://www.quartz-scheduler.org/documentation/quartz-1.x/tutorials/crontrigger).

**How it works?**

```
// Define the job and tie it to our HelloJob class
JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1").build()
```

Job creation, the task that I want to be executed.

```
// Compute a time that is on the next round minute
Date runTime = DateBuilder.evenMinuteDate(new Date());
```

Definition of the schedule time 

```
// Trigger the job to run on the next round minute
Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1").startAt(runTime).build();
```
Define the trigger that this job will be executed, this case the time is the next minute.

```
// Create a instance using the SchedulerFactory
SchedulerFactory schFactory = new StdSchedulerFactory();
Scheduler sched = schFactory.getScheduler();
```

```
// Tell quartz to schedule the job using our trigger
sched.scheduleJob(job, trigger);
sched.start();
```

```
// Stop the quartz service
sched.shutdown(true);
```

**System output**

```
Hello World! - Sat Jan 31 16:25:00 BRST 2015
```

reference:
[quartz-scheduler.org](http://www.quartz-scheduler.org/generated/2.2.1/html/qs-all/#page/Quartz_Scheduler_Documentation_Set%2Fto-quartz_scheduler_online_documentation_library.html%23)
