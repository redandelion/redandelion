package cn.redandelion.seeha;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "cn.redandelion.seeha.**.mapper")

public class SeehaApplication {

	public static void main(String[] args) {

		SpringApplication.run(SeehaApplication.class, args);

	}
}
