package edu.gatech.streamingwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class StreamingwarsApplication {

	// private final WebClient.Builder loadBalancedWebClientBuilder;
	// private final ReactorLoadBalancerExchangeFilterFunction lbFunction;
	//
	// public StreamingwarsApplication(WebClient.Builder webClientBuilder,
	// 					   ReactorLoadBalancerExchangeFilterFunction lbFunction) {
	// 	this.loadBalancedWebClientBuilder = webClientBuilder;
	// 	this.lbFunction = lbFunction;
	// }

	public static void main(String[] args) {
	    SpringApplication.run(StreamingwarsApplication.class, args);
	}

	// @RequestMapping("/")
	// public Mono<String> hi(@RequestParam(value = "name", defaultValue = "DefaultUser") String name) {
	// 	return loadBalancedWebClientBuilder.build().get().uri("http://localhost:8080")
	// 			.retrieve().bodyToMono(String.class)
	// 			.map(greeting -> String.format("%s, %s!", greeting, name));
	// }
	//
	//
	// @RequestMapping("/")
	// public Mono<String> hello(@RequestParam(value = "name", defaultValue = "DefaultUser") String name) {
	// 	return WebClient.builder()
	// 			.filter(lbFunction)
	// 			.build().get().uri("http://localhost:8080")
	// 			.retrieve().bodyToMono(String.class)
	// 			.map(greeting -> String.format("%s, %s!", greeting, name));
	// }

}
