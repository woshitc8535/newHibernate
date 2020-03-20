package Tests;

import Entity.Course;
import Entity.Instructor;
import Entity.InstructorDetail;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.HashSet;

public class HibernateDemoDay2 {
    @Test
    public void testAdd() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Instructor instructor = new Instructor();
            instructor.setFirstName("Shu");
            instructor.setLastName("Yang");
            instructor.setEmail("Yang@itlize.com");

            Course course = new Course();
            course.setName("Spring");

            InstructorDetail instructorDetail = new InstructorDetail();
            instructorDetail.setHobby("Basketball");

            instructor.getCourses().add(course);
            course.setInstructor(instructor);
            instructor.setInstructorDetail(instructorDetail);
            instructorDetail.setInstructor(instructor);

            session.save(instructor);


            tx.commit();
        }catch (Exception e) {

        }finally {
            factory.close();
        }

    }

    @Test
    public void testDelete() {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        try {
            Transaction tx = session.beginTransaction();

            Instructor instructor = (Instructor) session.get(Instructor.class, 3);
            session.delete(instructor);


            tx.commit();
        }catch (Exception e) {

        }finally {
            factory.close();
        }

    }
}
