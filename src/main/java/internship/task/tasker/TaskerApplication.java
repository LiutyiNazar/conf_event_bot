package internship.task.tasker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages="internship.task.tasker")
@PropertySource(value = "classpath:application.properties")
@PropertySource(value = "classpath:text.properties")
public class TaskerApplication { //NOSONAR

	public static void main(String[] args) {


		SpringApplication.run(TaskerApplication.class, args);//NOSONAR


	}
}
