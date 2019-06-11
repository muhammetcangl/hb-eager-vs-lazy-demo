package com.mcg.hibernate.demo;

import com.mcg.hibernate.demo.entity.Instructor;
import com.mcg.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.StreamUtils;

public class CreateDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            Instructor tempInstructor =
                    new Instructor("Muhammetcan","Gul","muhammetcangl@gmail.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail("deneme","Kod yazmak");

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // create a student object
            session.beginTransaction();


            // save the instructor
            //
            // Note: this will ALSO save the details object
            //because of CascadeType.ALL
            //
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
