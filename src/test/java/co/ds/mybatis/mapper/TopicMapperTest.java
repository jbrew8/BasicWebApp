package co.ds.mybatis.mapper;

import co.ds.bean.Topic;
import com.google.inject.Inject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TopicMapperTest extends MapperTestBase {

    public static final String NEW_TOPIC_NAME = "New Topic Name";

    @Inject
    TopicMapper topicMapper;


    @Test
    public void should_list() {
        final List<Topic> topicList = topicMapper.list();
        assertEquals("Topic list should contain 4 records", 4, topicList.size());
    }

    @Test
    public void should_fetch() {
        final List<Topic> topicList = topicMapper.list();
        assertEquals("Topic list should contain 4 records", 4, topicList.size());

        final Topic originalTopic = topicList.get(0);
        final Topic topic = topicMapper.fetch(originalTopic.getId());

        assertEquals("ID should be the same", originalTopic.getId(), topic.getId());
        assertEquals("Name should be the same", originalTopic.getName(), topic.getName());
    }

    @Test
    public void should_update() {

        final List<Topic> originalTopicList = topicMapper.list();
        final Topic originalTopic = originalTopicList.get(0);

        originalTopic.setName(NEW_TOPIC_NAME);
        topicMapper.update(originalTopic);

        final Topic updatedTopic = topicMapper.fetch(originalTopic.getId());
        assertEquals("Original and Updated topic should have the same ID", originalTopic.getId(), updatedTopic.getId());
        assertEquals("Unexpected name value for updated topic", NEW_TOPIC_NAME, updatedTopic.getName());

    }

}
