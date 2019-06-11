package com.mcg.hibernate.demo;

import com.mcg.hibernate.demo.entity.Course;
import com.mcg.hibernate.demo.entity.Instructor;
import com.mcg.hibernate.demo.entity.InstructorDetail;
import com.mcg.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            Course course = new Course("Hibernate Kursu");

            course.addReview(new Review("Great course"));
            course.addReview(new Review("Nice course, good job!"));
            course.addReview(new Review("Great."));

            System.out.println("Saving the course");
            System.out.println(course);
            System.out.println(course.getReviews());

            session.save(course);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {

            session.close();

            factory.close();
        }
    }
}
