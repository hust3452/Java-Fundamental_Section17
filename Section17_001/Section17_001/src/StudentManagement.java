import Database.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    public static void list_student() {
        List<Student> studentList = new ArrayList<>();

        try (Connection connection = Database.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call get_all_student}");
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));

                studentList.add(student);
            }
        } catch (Exception e) {
            System.out.println("Lay du lieu khong thanh cong !!!");
        }
        if (studentList.isEmpty()) {
            System.out.println("Danh sach trong!!!");
        } else {
            System.out.println("Danh sach hoc sinh hien co : ");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }


    }

    public static void add_student(List<Student> students) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                for (Student student : students) {
                    callableStatement = connection.prepareCall("{CALL add_student(?, ?)}");
                    callableStatement.setString(1, student.getName());
                    callableStatement.setInt(2, student.getAge());
                    callableStatement.executeUpdate();
                }
                connection.commit();
                System.out.println("them sinh vien thanh cong!!!");
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Da xay ra loi !!!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update_student(Student student) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);

                callableStatement = connection.prepareCall("{CALL update_student(?,?,?)}");
                callableStatement.setInt(1, student.getId());
                callableStatement.setString(2, student.getName());
                callableStatement.setInt(3, student.getAge());
                callableStatement.executeUpdate();

                connection.commit();
                System.out.println("them sinh vien thanh cong!!!");
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Da xay ra loi !!!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void delete_student(Scanner sc) {
        System.out.println("Nhap tuoi vao de thu hien xoa");
        int age = Integer.parseInt(sc.nextLine());
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);

                callableStatement = connection.prepareCall("{CALL delete_student(?)}");
                callableStatement.setInt(1, age);

                int rs = callableStatement.executeUpdate();
                connection.commit();

                if (rs == 0) {
                    System.out.println("Không tim thấy học sinh nào có tuổi nhỏ hơn : " + age);
                } else {
                    System.out.println("Xóa thành công " + rs + " học sinh có tuổi nhỏ hơn : " + age);
                }
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Da xay ra loi !!!");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}



//public static void delete_student(Scanner sc) {
//
//}
//
//public Student findById(int findId) {
//    return null;
//}
//
//}