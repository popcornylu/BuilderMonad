# Intrdocution
Java8 introduces a lot of new features, incluidng the most exciting one, lambda. In this project, i try to implement a generic builder by Monad pattern. However, after implementing, i found monad pattern is not quite suitable for builder. Anyway, I gave up the map and flatMap in monad while i use nested initiation instead.


# Creating an Object With Initiation block

	Course course = Builder.of(new Course(), (c) -> {
        c.setName("Functional Language");
        c.setRating(95);
    }).get();

# Building an Object With Fluent Style.

	Course course = Builder.of(new Course())
        .set(Course::setName, "Functional Language")
        .set(Course::setRating, 95)
        .get();
	
# Nested Initiation 

	Course course = Builder.of(new Course())
        .set(Course::setName, "Functional Language")
        .set(Course::setRating, 95)
        .set(Course::setTeacher, new User(), (teacher, builder)-> {
            teacher.setUserId(1);
            teacher.setName("pop");
        })
        .get();

# Initiating a List Member

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
        
# Initiating a Map Member

	Course course = Builder.of(new Course())
        .set(Course::setName, "Functional Language")
        .setMap(Course::setMeta, new HashMap<>(), (meta, mapBuilder) -> {
            mapBuilder
            .put("foo", "bar")
            .put("abc", "xyz");
        })
        .get();
        
