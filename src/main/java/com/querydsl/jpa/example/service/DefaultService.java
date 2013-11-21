package com.querydsl.jpa.example.service;

import static com.querydsl.jpa.example.model.QTweet.tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.google.inject.persist.Transactional;
import com.querydsl.jpa.example.data.LocationData;
import com.querydsl.jpa.example.data.TweetData;
import com.querydsl.jpa.example.data.UserData;
import com.querydsl.jpa.example.model.Location;
import com.querydsl.jpa.example.model.Tweet;
import com.querydsl.jpa.example.model.User;
import com.querydsl.jpa.example.repository.TweetRepository;
import com.querydsl.jpa.example.repository.UserRepository;

@Transactional
public class DefaultService {
    public final UserRepository userRepository;

    public final TweetRepository tweetRepository;

    @Inject
    public DefaultService(UserRepository userRepository,
            TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public TweetData tweet(UserData poster, String content) {
        return tweet(poster, content, Collections.<UserData>emptyList(), null);
    }

    private static User toUser(UserData data) {
        return new User(data.getId(), data.getUsername());
    }

    private static Location toLocation(LocationData data) {
        if (data == null) {
            return null;
        }
        return new Location(data.getLongitude(), data.getLatitude());
    }

    private static LocationData toLocationData(Location location) {
        if (location == null) {
            return null;
        }
        return new LocationData(location.getLongitude(), location.getLatitude());
    }

    private static UserData toUserData(User user) {
        return new UserData(user.getId(), user.getUsername());
    }

    private static TweetData toTweetData(Tweet tweet) {
        return new TweetData(tweet.getId(), toUserData(tweet.getPoster()),
                tweet.getContent(), toUserDatas(tweet.getMentions()),
                toLocationData(tweet.getLocation()));
    }

    private static List<User> toUsers(List<UserData> data) {
        List<User> rv = new ArrayList<User>();
        for (UserData x : data) {
            rv.add(toUser(x));
        }
        return rv;
    }

    private static List<UserData> toUserDatas(List<User> users) {
        List<UserData> rv = new ArrayList<UserData>();
        for (User user : users) {
            rv.add(toUserData(user));
        }
        return rv;
    }

    private static List<TweetData> toTweetDatas(List<Tweet> tweets) {
        List<TweetData> rv = new ArrayList<TweetData>();
        for (Tweet tweet : tweets) {
            rv.add(toTweetData(tweet));
        }
        return rv;
    }

    public UserData save(UserData data) {
        return toUserData(userRepository.save(toUser(data)));
    }

    public TweetData tweet(UserData poster, String content,
            List<UserData> mentions, LocationData location) {
        User user = userRepository.byId(poster.getId());
        Tweet tweet = new Tweet(user, content, toUsers(mentions),
                toLocation(location));
        return toTweetData(tweetRepository.save(tweet));
    }

    public List<TweetData> tweetsByPoster(String username) {
        return toTweetDatas(tweetRepository.byPredicate(tweet.poster.username.eq(username)));
    }

    public UserData userById(Long id) {
        return toUserData(userRepository.byId(id));
    }
}
