package com.querydsl.jpa.example.repository;

import static com.querydsl.jpa.example.model.QTweet.tweet;

import java.util.List;

import com.google.inject.persist.Transactional;
import com.mysema.query.types.expr.BooleanExpression;
import com.querydsl.jpa.example.model.Tweet;

@Transactional
public class TweetRepository extends AbstractRepository<Tweet> {
    public Tweet save(Tweet tweet) {
        return persistOrMerge(tweet);
    }

    @Override
    public Tweet byId(Long id) {
        return find(Tweet.class, id);
    }

    public List<Tweet> byPredicate(BooleanExpression expr) {
        return from(tweet).where(expr).list(tweet);
    }
}
