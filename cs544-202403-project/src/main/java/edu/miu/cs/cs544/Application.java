package edu.miu.cs.cs544;

import edu.miu.cs.cs544.domain.RoleType;
import edu.miu.cs.cs544.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.miu.cs.cs544.service.MemberService;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"edu.miu.common", "edu.miu.cs.cs544"})
public class Application implements CommandLineRunner {

	@Autowired
	private MemberService service;

	@Autowired
	private RoleService roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		service.findAll().forEach(System.out::println);
		roleService.create(RoleType.Student);
		roleService.create(RoleType.Faculty);
	}

}
