package idv.popcorny.sample;

import idv.popcorny.builder.Builder;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by popcorny on 2014/8/4.
 */
public class Sample {
    public static void main(String[] args) {
        example1();
        example2();
        example3();
        example4();
        example5();
    }

    public static void example1() {
        Course course = Builder.of(new Course(), (c) -> {
            c.setName("Funtional Language");
            c.setRating(95);
        }).get();

        dump("example1", course);
    }

    public static void example2() {
        Course course = Builder.of(new Course())
        .set(Course::setName, "Functionl Language")
        .set(Course::setRating, 95)
        .get();

        dump("example2", course);
    }

    public static void example3() {
        Course course = Builder.of(new Course())
        .set(Course::setName, "Functionl Language")
        .set(Course::setRating, 95)
        .set(Course::setTeacher, new User(), (teacher, builder)-> {
            teacher.setUserId(1);
            teacher.setName("pop");
        })
        .get();

        dump("example3", course);
    }

    public static void example4() {
        Course course = Builder.of(new Course())
        .set(Course::setName, "Functionl Language")
        .set(Course::setRating, 95)
        .setList(Course::setStudents, new ArrayList<>(), (students, listBuilder) -> {
            listBuilder
            .add(new User(), (student, b) -> {
                student.setUserId(2);
                student.setName("foo");
            })
            .add(new User(), (student, b) -> {
                student.setUserId(3);
                student.setName("bar");
            });
        })
        .get();

        dump("example4", course);
    }

    public static void example5() {
        Course course = Builder.of(new Course())
        .set(Course::setName, "Functionl Language")
        .setMap(Course::setMeta, new HashMap<>(), (meta, mapBuilder) -> {
            mapBuilder
            .put("foo", "bar")
            .put("abc", "xyz");
        })
        .get();

        dump("example5", course);
    }

    public static void dump(String example, Course course) {
        System.out.printf("%s -> %s\n", example, course.toString());
    }
}

class Course {
    private String name;
    private User teacher;
    private int rating;
    private List<User> students;
    private Map<String, String> meta;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", teacher=" + teacher +
                ", rating=" + rating +
                ", students=" + students +
                ", meta=" + meta +
                '}';
    }
}

class User {
    private int userId;
    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}