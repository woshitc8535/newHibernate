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

            Course course1 = new Course();
            course1.setName("Spring");
            Course course2 = new Course();
            course2.setName("Hibernate");

            InstructorDetail instructorDetail = new InstructorDetail();
            instructorDetail.setHobby("Basketball");



            instructor.getCourses().add(course1);
            instructor.getCourses().add(course2);
            course1.setInstructor(instructor);
            course2.setInstructor(instructor);
            instructor.setInstructorDetail(instructorDetail);
            instructorDetail.setInstructor(instructor);

            session.save(instructor);


            tx.commit();
        }catch (Exception e) {

        }finally {
            session.close();
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

//            Instructor instructor = (Instructor) session.get(Instructor.class, 1);
//            session.delete(instructor);

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, 1);

            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);


            tx.commit();
        }catch (Exception e) {

        }finally {
            session.close();
            factory.close();
        }

    }
}
