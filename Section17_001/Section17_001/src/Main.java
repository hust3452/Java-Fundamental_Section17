import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Scanner.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    StudentManagement studentManagement = new StudentManagement();

    do {System.out.println( "1.Thêm mới Student.\n" +
                            "2.Liệt Kê Student.\n" +
                            "3.Sửa Student.\n" +
                            "4.Xóa Student.\n" +
                            "5.Thoát .");
        System.out.println("Nhập vào sự lựa chọn của bạn");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                List<Student> students = Arrays.asList(
                        new Student(0, "Nguyễn Văn A", 20),
                        new Student(0, "Trần Thị B", 22),
                        new Student(0, "Lê Văn C", 19)
                );
                studentManagement.add_student(students);
                break;
            case 2:
                StudentManagement.list_student();
                break;
            case 3:
                Student student = new Student(1, "Nguyễn Văn A", 21);
                studentManagement.update_student(student);
                break;
            case 4:
                StudentManagement student1 = new StudentManagement();
                student1.delete_student(sc);
                break;
            case 5:
                System.out.println("Cam on ban da su dung ");
                System.exit(0);
                break;
            default:
                System.out.println("Vui long nhap su lua chon tu 1-6");
        }
    }while (true);
    }
}