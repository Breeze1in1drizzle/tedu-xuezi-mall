package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {

	@Autowired
	public AddressMapper mapper;
	
	@Test
	public void insert() {
		Address address = new Address();
		address.setUid(8);
		address.setName("小李同学");
		Integer rows = mapper.insert(address);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void countByUid() {
		Integer uid = 8;
		Integer count = mapper.countByUid(uid);
		System.err.println("count=" + count);
	}
	
}






