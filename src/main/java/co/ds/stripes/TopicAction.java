package co.ds.stripes;

import co.ds.bean.Topic;
import co.ds.mybatis.mapper.TopicMapper;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import java.util.List;

@UrlBinding("/topic")
public class TopicAction extends BaseAction {

    private static String LIST_FORWARD = "/WEB-INF/jsp/topic/list.jsp";
    private static final String FORM_FORWARD = "/WEB-INF/jsp/topic/form.jsp";

    private TopicMapper topicMapper;

    @ValidateNestedProperties({
            @Validate(on = "save", field = "name", required = true),

    })
    private Topic topic;
    private List<Topic> topics;


    @Inject
    public TopicAction(final TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    /* ACTIONS */

    @DefaultHandler
    public Resolution list() {
        topics = topicMapper.list();
        return new ForwardResolution(LIST_FORWARD);
    }


    public Resolution edit() {
        topic = topicMapper.fetch(topic.getId());
        return form();
    }

    public Resolution save() {
        topicMapper.update(topic);
        return new RedirectResolution(TopicAction.class);
    }

    public Resolution form() {
        topics = topicMapper.list();
        return new ForwardResolution(FORM_FORWARD);
    }

    public Resolution cancelForm() {
        return new RedirectResolution(TopicAction.class);
    }

    public Resolution cancelList() {
        return new RedirectResolution(HomeAction.class);
    }



    /* GETTERS AND SETTERS */
    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
