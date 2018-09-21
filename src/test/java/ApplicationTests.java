import com.tingfeng.test.SpringCacheTestApp;
import com.tingfeng.test.bean.TestBean;
import com.tingfeng.test.service.TestService;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringCacheTestApp.class})
public class ApplicationTests {

	@Autowired
	private TestService testService;
	
	@Autowired
	private CacheChannel cacheChannel;

	@Test
	public void testCache() {
		testService.reset();
		testService.evict();
		testService.getNum();
		Integer n = testService.getNum();
		Assert.isTrue(n == 1, "缓存未生效！");
	}
	
	@Test
	public void clearCache() {
		testService.reset();
		testService.getNum();
		testService.reset();
		testService.evict();
		Integer a = testService.getNum();
		Assert.isTrue(a == 1, "清除缓存未生效！");
	}
	
	@Test
	public void beanCache() {
		testService.reset();
		testService.evict();
		testService.testBean();
		TestBean b = testService.testBean();
		Integer a = b.getNum();
		Assert.isTrue(a == 1, "对象缓存未生效！");
	}
	
	@Test
	public void test() {
		cacheChannel.set("test", "123", "321");
		CacheObject a = cacheChannel.get("test", "123");
		Assert.isTrue(a.getValue().equals("321"), "失败！");
	}
}
