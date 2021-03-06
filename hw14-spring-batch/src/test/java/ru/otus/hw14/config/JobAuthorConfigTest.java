package ru.otus.hw14.config;

import com.github.cloudyrock.spring.v5.EnableMongock;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.hw14.repository.crud.AuthorCrudRepository;
import ru.otus.hw14.repository.mongo.AuthorMongoRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;

@EnableMongock
@SpringBatchTest
@ActiveProfiles("test")
@EnableAutoConfiguration
@EnableConfigurationProperties
@EntityScan("ru.otus.hw14.model.entity")
@EnableJpaRepositories(basePackageClasses = {AuthorCrudRepository.class})
@EnableMongoRepositories(basePackageClasses = {AuthorMongoRepository.class})
@SpringBootTest(classes = {JobAuthorConfig.class, BatchConfig.class, MongoConfig.class})
class JobAuthorConfigTest {

  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Autowired
  private JobRepositoryTestUtils jobRepositoryTestUtils;

  @BeforeEach
  void clearMetaData() {
    jobRepositoryTestUtils.removeJobExecutions();
  }

  @Test
  void testShouldReturnExpectedJobName() {
    Job job = jobLauncherTestUtils.getJob();
    assertNotNull(job);
    assertEquals("migrateAuthorJob", job.getName());
  }

  @Test
  @SneakyThrows
  @DirtiesContext
  void givenJobExecuted_thenSuccess() {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    JobInstance actualJobInstance = jobExecution.getJobInstance();
    ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

    assertThat(actualJobInstance.getJobName(), is("migrateAuthorJob"));
    assertThat(actualJobExitStatus.getExitCode(), is("COMPLETED"));
  }

  @Test
  @DirtiesContext
  void givenStepExecuted_thenReaWrite_3() {
    JobExecution jobExecution = jobLauncherTestUtils.launchStep("migrateAuthorStep");
    Collection<StepExecution> actualStepExecutions = jobExecution.getStepExecutions();

    assertThat(actualStepExecutions.size(), is(1));

    actualStepExecutions.forEach(stepExecution -> assertThat(stepExecution.getWriteCount(), is(3)));
    actualStepExecutions.forEach(stepExecution -> assertThat(stepExecution.getReadCount(), is(3)));
  }
}
