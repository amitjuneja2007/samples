package com.test.aj.collections;

import com.company.test.java.model.BlogPost;
import com.company.test.java.model.BlogPostType;
import com.google.common.collect.ImmutableMap;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.stream.Collectors;

//https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-16/src/test/java/com/baeldung/java_16_features/groupingby/JavaGroupingByCollectorUnitTest.java
public class GroupingTest {
    private static final List<BlogPost> posts = Arrays.asList(
            new BlogPost("News item 1", "Author 1", BlogPostType.NEWS, 15),
            new BlogPost("Tech review 1", "Author 2", BlogPostType.REVIEW, 5),
            new BlogPost("Programming guide", "Author 1", BlogPostType.GUIDE, 20),
            new BlogPost("News item 2", "Author 2", BlogPostType.NEWS, 35),
            new BlogPost("Tech review 2", "Author 1", BlogPostType.REVIEW, 15));

    private static final List<Map<String, Object>> records = Arrays.asList(
            ImmutableMap.of("code", "SSB", "acctNumber", "12345", "entityId", "0001"),
            ImmutableMap.of("code", "SSB", "acctNumber", "12345", "entityId", "0002"),
            ImmutableMap.of("code", "BBH", "acctNumber", "99901", "entityId", "0003"),
            ImmutableMap.of("code", "CITI", "acctNumber", "88801", "entityId", "0004"));


    /**
     * Say we have list of maps.
     * Group list by map property.
     *
     */
    @Test
    public void testGroupingMapOnMapKey() {

    }

    @Test
    public void testGrouping() {

        Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType));

        Assertions.assertEquals(2, postsPerType.get(BlogPostType.NEWS)
                .size());
        Assertions.assertEquals(1, postsPerType.get(BlogPostType.GUIDE)
                .size());
        Assertions.assertEquals(2, postsPerType.get(BlogPostType.REVIEW)
                .size());

    }

    @Test
    public void givenAListOfPosts_whenGroupedByTypeAndTheirTitlesAreJoinedInAString_thenGetAMapBetweenTypeAndCsvTitles() {
        Map<BlogPostType, String> postsPerType = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType, Collectors.mapping(BlogPost::getTitle, Collectors.joining(", ", "Post titles: [", "]"))));

        Assertions.assertEquals("Post titles: [News item 1, News item 2]", postsPerType.get(BlogPostType.NEWS));
        Assertions.assertEquals("Post titles: [Programming guide]", postsPerType.get(BlogPostType.GUIDE));
        Assertions.assertEquals("Post titles: [Tech review 1, Tech review 2]", postsPerType.get(BlogPostType.REVIEW));
        System.out.println(postsPerType);
    }

    @Test
    public void givenAListOfPosts_whenGroupedByTypeAndTheirTitlesAreJoinedInAString_thenGetAMapBetweenT() {
        Map<BlogPostType, String> postsPerType = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType, Collectors.mapping(BlogPost::getTitle, Collectors.joining(", ", "Post titles: [", "]"))));
        System.out.println(postsPerType);
    }

}
