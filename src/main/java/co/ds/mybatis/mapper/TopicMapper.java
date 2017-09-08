package co.ds.mybatis.mapper;

import co.ds.bean.Topic;

import java.util.List;

public interface TopicMapper {

	// Retrieves all topics
	List<Topic> list();
	// updates a single topic
	void update(Topic topic);
	// Retrieves a single topic
	Topic fetch(Integer id);

}
