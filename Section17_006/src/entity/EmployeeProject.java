package entity;

import EmployeeDatabase.EmployeeDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeProject {
    public static void assignEmployeeToProject(int emp_id, int pj_id) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = EmployeeDatabase.openConnection();

            callSt = conn.prepareCall("{call assign_employee_to_project(?,?,?)}");
            callSt.setInt(1, emp_id);
            callSt.setInt(2, pj_id);
            callSt.registerOutParameter(3, Types.VARCHAR);

            callSt.execute();
            String result = callSt.getString(3);
            System.out.println(result);
            conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            EmployeeDatabase.closeConnection(conn, callSt);
        }
    }
    }


