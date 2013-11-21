package com.querydsl.jpa.example.repository;
import static com.querydsl.jpa.example.model.QTweet.tweet;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import javax.inject.Inject;

import org.junit.Test;

import com.querydsl.jpa.example.guice.AbstractPersistenceTest;
import com.querydsl.jpa.example.model.Tweet;
import com.querydsl.jpa.example.model.User;

public class TweetRepositoryTest extends AbstractPersistenceTest {
    @Inject
    private TweetRepository repository;

    @Inject
    private UserRepository userRepository;

    @Test
    public void save_and_get_by_id() {
        User poster = new User("dr_frank");
        userRepository.save(poster);

        String content = "I am alive! #YOLO";
        Tweet tweet = new Tweet(poster, content,
                Collections.<User>emptyList(), null);
        repository.save(tweet);
        assertEquals(content, repository.byId(tweet.getId()).getContent());
    }

    @Test
    public void get_list_by_predicate() {
        User poster = new User("dr_frank");
        userRepository.save(poster);

        repository.save(new Tweet(poster, "It is a alive! #YOLO", Collections.<User>emptyList(), null));
        repository.save(new Tweet(poster, "Oh the humanity!", Collections.<User>emptyList(), null));
        repository.save(new Tweet(poster, "#EpicFail", Collections.<User>emptyList(), null));
        assertEquals(1, repository.byPredicate(tweet.content.contains("#YOLO")).size());
    }
}
