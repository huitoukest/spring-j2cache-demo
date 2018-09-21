import com.tingfeng.test.SpringCacheTestApp;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringCacheTestApp.class})
public class TestJ2Cache {

    @Autowired
    private CacheChannel cacheChannel;

    @Test
    public void testWrite() {
        CacheObject v = null;
        v = cacheChannel.get("userCache", "123", true);
        System.out.println(v.toString());
        System.out.println(v.asString());
        System.out.println();
        cacheChannel.set("userCache", "123", "user123");
        v = cacheChannel.get("userCache", "123", true);
        System.out.println(v.toString());
        System.out.println(v.asString());
    }

}
