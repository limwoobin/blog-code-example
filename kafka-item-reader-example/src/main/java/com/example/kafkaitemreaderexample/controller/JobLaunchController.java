package com.example.kafkaitemreaderexample.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/jobs")
public class JobLaunchController {
  private final JobLauncher jobLauncher;
  private final JobRegistry jobRegistry;

  public JobLaunchController(JobLauncher jobLauncher, JobRegistry jobRegistry) {
    this.jobLauncher = jobLauncher;
    this.jobRegistry = jobRegistry;
  }

  @GetMapping(value = "/{jobName}")
  public ResponseEntity<String> jobLaunch(@PathVariable String jobName) throws Exception {
    JobParameters jobParameters = new JobParametersBuilder()
      .addString("name", jobName)
      .addLong("timestamp", System.currentTimeMillis())
      .toJobParameters();

    Job job = jobRegistry.getJob(jobName);
    jobLauncher.run(job, jobParameters);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
}
