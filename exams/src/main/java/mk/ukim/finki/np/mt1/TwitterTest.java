package mk.ukim.finki.np.mt1;

import java.util.List;

/**
 * TwitterTest 2015/11/14 first midterm
 */
public class TwitterTest {

}

class Twitter {

    List<Tweet> tweets;
    List<Likeable> likes;
    List<Retweetable> retweets;

    public void tweet(long id, String message) {

    }

    public void reply(long id, String message) {

    }

    public void like(long id) {

    }

    public void retweet(long id) {

    }
}

interface Likeable {
    void like();
}

interface Retweetable {
    void retweet();
}

class Tweet {
    long id;
    String message;
    Tweet parent;
}
