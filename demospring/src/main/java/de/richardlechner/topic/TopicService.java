package de.richardlechner.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	List <Topic> topics = Arrays.asList(
			new Topic("1","eins", "desc eins"),
			new Topic("2","zwei", "desc zwei"),
			new Topic("3","drei", "desc drei")
			);
	
	public List<Topic> getallTopics() {
		return topics;
	}
}	

