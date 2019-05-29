package com.mcg.hibernate.demo;

import com.mcg.hibernate.demo.entity.Course;
import com.mcg.hibernate.demo.entity.Instructor;
import com.mcg.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                        + "JOIN FETCH i.courses "
                                        + "where i.id=:instructorId",
                            Instructor.class);

            query.setParameter("instructorId", theId);

            Instructor tempInstructor = query.getSingleResult();

            System.out.println("Instructor: " + tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {

            session.close();

            factory.close();
        }
    }
}
