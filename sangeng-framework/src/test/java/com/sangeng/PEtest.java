package com.sangeng;

import com.sangeng.enums.AppHttpCodeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PEtest {

    @Test
    public void testPasswordEncoder() {
         System.out.println(AppHttpCodeEnum.SUCCESS);
    }

}
