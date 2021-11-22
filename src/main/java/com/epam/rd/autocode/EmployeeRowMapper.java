package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRowMapper implements RowMapper<Employee> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final byte COLUMN_ID = 1;
    private static final byte COLUMN_FIRST_NAME = 2;
    private static final byte COLUMN_LAST_NAME = 3;
    private static final byte COLUMN_MIDDLE_NAME = 4;
    private static final byte COLUMN_POSITION = 5;
    private static final byte COLUMN_HIREDATE = 7;
    private static final byte COLUMN_SALARY = 8;

    @Override
    public Employee mapRow(ResultSet resultSet) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            BigInteger id = new BigInteger(String.valueOf(resultSet.getLong(COLUMN_ID)));
            FullName fullName = new FullName(
                resultSet.getString(COLUMN_FIRST_NAME),
                resultSet.getString(COLUMN_LAST_NAME),
                resultSet.getString(COLUMN_MIDDLE_NAME));
            Position position = Position.valueOf(resultSet.getString(COLUMN_POSITION));
            LocalDate hired = resultSet.getDate(COLUMN_HIREDATE).toLocalDate();
            BigDecimal salary = resultSet.getBigDecimal(COLUMN_SALARY);
            employeeList.add(new Employee(id, fullName, position, hired, salary));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return employeeList.get(0);
    }
}
