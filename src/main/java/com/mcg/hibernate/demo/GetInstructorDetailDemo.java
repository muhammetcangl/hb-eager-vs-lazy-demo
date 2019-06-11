package com.mcg.hibernate.demo;

import com.mcg.hibernate.demo.entity.Instructor;
import com.mcg.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

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
            // create a student object
            session.beginTransaction();

            int theId = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

            System.out.println("instructorDetail: " + instructorDetail);

            System.out.println("the associated instructor: " + instructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception ex){

            ex.printStackTrace();
        } finally {

            session.close();
            factory.close();
        }
    }
}
