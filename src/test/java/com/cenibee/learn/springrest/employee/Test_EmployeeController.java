package com.cenibee.learn.springrest.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class Test_EmployeeController {

    @Autowired
    EmployeeController controller;

    @Autowired
    EmployeeRepository employees;

    @Test
    @DisplayName("CRUD 테스트")
    void CRUD_test() {
        // given: 테스트 시작 전 사이즈와 저장되지 않은 Employee employee 가 주어졌을 떄
        Long preloadedSize = employees.count();
        Employee employee = new Employee("Bab", "mid lane");
        assertThat(employees.exists(Example.of(employee))).isFalse();

        // when: employee 를 저장하면 (Create)
        employee = controller.newEmployee(employee);
        final long id = employee.getId();

        // then: employee 와 employee 의 id 로 검색한 결과가 동일하다. (Read)
        assertThat(employees.count()).isEqualTo(preloadedSize + 1);
        assertThat(controller.one(id)).isEqualTo(employee);

        // when: role 을 변경하고 저장하면 (Update)
        employee.setRole("supporter");
        employee = controller.replaceEmployee(employee, id);

        // then: employee 의 id 로 검색한 결과의 role 이 변경된 값과 같다.
        assertThat(employees.count()).isEqualTo(preloadedSize + 1);
        assertThat(controller.one(id)).isEqualTo(employee);
        assertThat(controller.one(id).getRole()).isEqualTo("supporter");

        // when: employee 를 삭제하면 (Delete)
        employees.deleteById(id);

        // then: employee 의 id 로 검색한 결과를 찾을 수 없다.
        assertThat(employees.count()).isEqualTo(preloadedSize);
        assertThrows(EmployeeNotFoundException.class, () -> controller.one(id));
    }
}
