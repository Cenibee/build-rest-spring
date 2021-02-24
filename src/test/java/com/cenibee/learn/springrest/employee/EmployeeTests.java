package com.cenibee.learn.springrest.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class EmployeeTests {

    @ParameterizedTest
    @MethodSource
    @DisplayName("Builder 정상 동작 테스트")
    void builderTest(long id, String name, String role) {
        Employee employee = Employee.builder()
                .id(id)
                .name(name)
                .role(role)
                .build();

        assertThat(employee).isNotNull();
    }

    static Stream<Arguments> builderTest() {
        return Stream.of(
                arguments(3, "Test Name", "Test Role"),
                arguments(-99, "", null),
                arguments(0, "       ", "this is the Role!!")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("id 값에 따라 hash code 가 같고 equals 하다")
    void equalsAndHashCode(long id1, String name1, long id2, String name2, boolean isEquals) {
        Employee employee1 = Employee.builder()
                .id(id1)
                .name(name1)
                .build();

        Employee employee2 = Employee.builder()
                .id(id2)
                .name(name2)
                .build();

        assertThat(employee1.hashCode() == employee2.hashCode())
                .isEqualTo(isEquals);
        assertThat(employee1.equals(employee2))
                .isEqualTo(isEquals);
    }

    static Stream<Arguments> equalsAndHashCode() {
        return Stream.of(
                arguments(1, "name", 2, "name", false),
                arguments(0, "name1", 0, "nam2", true),
                arguments(3, "   ", 3, null, true)
        );
    }

}
