package com.tyrael.sell.repository;

import com.tyrael.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetaiRepositoryTest {

    @Autowired
    private OrderDetaiRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456987");
        orderDetail.setOrderId("11111111");
        orderDetail.setProductIcon("http://yyyx.jpg");
        orderDetail.setProductId("1111123412");
        orderDetail.setProductName("虾仁咸骨粥");
        orderDetail.setProductPrice(new BigDecimal(10.3));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception{

        List<OrderDetail> orderDetailList = repository.findByOrderId("11111111");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}
