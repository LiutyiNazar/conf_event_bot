package internship.task.tasker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages="internship.task.tasker")
@PropertySources(
		{@PropertySource(value = "classpath:application.properties"),
		@PropertySource(value = "classpath:text.properties")})
public class TaskerApplication {

	public static void main(String[] args) {

		SpringApplication.run(TaskerApplication.class, args);


	}
}
