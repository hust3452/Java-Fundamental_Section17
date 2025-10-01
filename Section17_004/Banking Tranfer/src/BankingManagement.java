import Database_Banking.bankingDatabase;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;


public class BankingManagement {
    public static void transfer(int id_from, int id_to, Double amount) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = bankingDatabase.getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                callableStatement = connection.prepareCall("{CALL transfer_funds(?,?,?,?)}");
                callableStatement.setInt(1, id_from);
                callableStatement.setInt(2, id_to);
                callableStatement.setDouble(3, amount);
                callableStatement.registerOutParameter(4, Types.VARCHAR);
                callableStatement.executeUpdate();
                connection.commit();
                String rs = callableStatement.getString(4);
                System.out.println(rs);
            }
        } catch (Exception e) {
            try {
                if(connection!=null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                throw new RuntimeException(rollbackEx);
            }
            System.out.println("Chuyen tien that bai");
            ;
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
